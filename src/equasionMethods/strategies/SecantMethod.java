package equasionMethods.strategies;

import equasionMethods.strategies.interfaces.EquationSolvableStrategy;
import equasions.interfaces.Calculatable;
import equasions.interfaces.EquationCollectable;
import equationRW.writers.interfaces.EquationsWritable;
import matrixMethods.customExceptions.*;

public class SecantMethod implements EquationSolvableStrategy {
    private EquationsWritable consolePrinter;


    @Override
    public Double execute(EquationCollectable equationData) throws NonCompliaceException {
        Double[] bounds = equationData.getBounds();

        return calculate(bounds[0], bounds[1], equationData.getFirstApprox(), equationData.getAccuracity(), equationData.getEquation());
    }

    @Override
    public void setWriter(EquationsWritable printable) {
        consolePrinter = printable;
    }

    private Double calculate(Double a, Double b, Double x1, Double e, Calculatable fe) {
        consolePrinter.write("Уравнение: " + fe.toString());

        double prev;

        double cur = x1;

        int iteration = 1;

        if (fe.calculateFunc(b)*fe.calculateDeriv(b) > 0){
            prev = b;
        }else prev = a;

        double modul = Math.abs(cur - prev);

        consolePrinter.write("№", "X_(k-1)", "f(X_(k-1))", "X_(k)", "f(X_(k))", "X_(k+1)", "f(X_(k+1))", "|X_k - X_(k+1)|");
        while((Math.abs(fe.calculateFunc(cur)) > e) || (modul > e)){

            double next = cur - ((cur - prev)/(fe.calculateFunc(cur) - fe.calculateFunc(prev)))* fe.calculateFunc(cur);
            consolePrinter.write(
                    String.valueOf(iteration),
                    String.valueOf(Rounder.round(3, prev)),
                    String.valueOf(Rounder.round(3, fe.calculateFunc(prev))),
                    String.valueOf(Rounder.round(3, cur)),
                    String.valueOf(Rounder.round(3, fe.calculateFunc(cur))),
                    String.valueOf(Rounder.round(3, next)),
                    String.valueOf(Rounder.round(3,fe.calculateFunc(next))),
                    String.valueOf(Rounder.round(3, modul))
            );
            prev = cur;
            cur = next;
            modul = Math.abs(cur - prev);
            iteration++;
        }

        String answMessage = "Ответ: " + cur;

        consolePrinter.write(answMessage);

        return cur;
    }
}
