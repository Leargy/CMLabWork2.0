package equasionMethods.strategies;

import equasionMethods.strategies.interfaces.EquationSolvableStrategy;
import equasions.interfaces.Calculatable;
import equasions.interfaces.EquationCollectable;
import equationRW.writers.interfaces.EquationsWritable;
import matrixMethods.customExceptions.*;

public class BinMethod implements EquationSolvableStrategy {
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



    private int sign(double x) {
        if (x > 0)
            return 1;
        else if (x < 0)
            return -1;
        return 0;
    }


    private Double calculate(double a, double b, double e, Calculatable legacyFunc) {
        consolePrinter.write("Уравнение: " + legacyFunc.toString());

        double answer = -1;

        double modul;
        int iterationNum = 1;

        consolePrinter.write("№", "a", "b", "x", "f(a)", "f(b)", "f(x)", "|a - b|");
        while (a <= b) {
            modul = Math.abs(a - b);

            double x = (a + b) / 2;

            double test = Math.abs(legacyFunc.calculateFunc(x));

            if (test <= e) {
                answer = x;
                break;
            }

            consolePrinter.write(String.valueOf(iterationNum),
                    String.valueOf(Rounder.round(3, a)),
                    String.valueOf(Rounder.round(3, b)),
                    String.valueOf(Rounder.round(3, x)),
                    String.valueOf(Rounder.round(3, legacyFunc.calculateFunc(a))),
                    String.valueOf(Rounder.round(3, legacyFunc.calculateFunc(b))),
                    String.valueOf(Rounder.round(3, legacyFunc.calculateFunc(x))),
                    String.valueOf(Rounder.round(3, modul))
            );

            if (sign(legacyFunc.calculateFunc(a)) != sign(legacyFunc.calculateFunc(x))) {
                b = x;
            } else if (sign(legacyFunc.calculateFunc(x)) != sign(legacyFunc.calculateFunc(b))) {
                a = x;
            }
            iterationNum++;
        }

        String answMessage = "Ответ: " + answer;

        consolePrinter.write(answMessage);

        return answer;
    }
}
