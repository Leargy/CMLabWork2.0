package equasionMethods.strategies;

import equasionMethods.strategies.interfaces.EquationSolvableStrategy;
import equasions.interfaces.Calculatable;
import equasions.interfaces.EquationCollectable;
import equationRW.writers.interfaces.EquationsWritable;
import matrixMethods.customExceptions.*;

public class IterationMethod implements EquationSolvableStrategy {
    private EquationsWritable consolePrinter;

    @Override
    public void setWriter(EquationsWritable printable) {
        consolePrinter = printable;
    }


    @Override
    public Double execute(EquationCollectable equationData) throws NonCompliaceException {
        Double[] bounds = equationData.getBounds();

        return calculate(bounds[0], bounds[1], equationData.getAccuracity(), equationData.getEquation());
    }


    //legacyFunc - is the given function by conditions
    private Double calculate(double a, double b, double e, Calculatable legacyFunc) {
        double lambda = calculateLambda(a, b, legacyFunc);

        return iterate(a, b, e, lambda, legacyFunc);
    }

    private double countQ(double a, double b, Calculatable fe, double lambda){
        return Math.max(Math.abs(countFirstDivFi(fe,lambda,a)),Math.abs(countFirstDivFi(fe,lambda,b)));
    }

    public Double countFi(Double x, Double l, Calculatable func) {
        return x + l*func.calculateFunc(x);
    }

    private double countFirstDivFi(Calculatable legacyFunc, double lambda, double x){
        return 1 + lambda*legacyFunc.calculateDeriv(x);
    }

    private Double calculateLambda(double a, double b, Calculatable func) {
        double fa = func.calculateDeriv(a);
        double fb = func.calculateDeriv(b);
        double l = -1/Math.max(fa, fb);

        return l;
    }

    //iterative method
    private Double iterate(double a, double b, double e, double lambda,  Calculatable legacyFunc) {

        consolePrinter.write("Уравнение: " + legacyFunc.toString());

        double res = a;
        double q = countQ(a, b, legacyFunc, lambda);
        double prev;

        int iterationNum = 1;
        double modul;

//        if ((q>=1)||(Math.abs(countFirstDivFi(legacyFunc,lambda,a))>q)||(Math.abs(countFirstDivFi(legacyFunc,lambda,b))>q)||(Math.abs(countFirstDivFi(legacyFunc,lambda,(a+b)/2))>q)) {
//            consolePrinter.write("Достаточное условие сходимости метода не соблюдается");
//            return -1.0;
//        }

        consolePrinter.write("№", "X_k", "f(X_k)", "X_(k+1)", "fi(X_k)", "|X_k - X_(k+1)|");

        while (true) {

            prev = res;
            if (!(Math.abs(countFirstDivFi(legacyFunc, lambda, prev)) < 1)) {
                consolePrinter.write("Расходится");
                break;
            }
            res = countFi(prev, lambda, legacyFunc);


            if (res > b || res < a) break;

            modul = Math.abs(res - prev);

            consolePrinter.write(
                    String.valueOf(iterationNum),
                    String.valueOf(Rounder.round(3, prev)),
                    String.valueOf(Rounder.round(3, legacyFunc.calculateFunc(prev))),
                    String.valueOf(Rounder.round(3, res)),
                    String.valueOf(Rounder.round(3, res)),
                    String.valueOf(Rounder.round(3, modul))
            );

            if (0 < q && q <= 0.5) {
                if (modul <= e) {
                    break;
                }
            } else if (0.5 < q && q < 1) {
                if (modul < ((1 - q) / q) * e) {
                    break;
                }
            }

            iterationNum++;
        }
        String answMessage = "Ответ: " + Rounder.round(3, res);

        consolePrinter.write(answMessage);

        return res;
    }
}
