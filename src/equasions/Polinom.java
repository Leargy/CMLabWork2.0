package equasions;

import equasions.interfaces.Calculatable;

public class Polinom implements Calculatable {

    @Override
    public Double calculateFunc(Double x) {
        return Math.pow(x, 3) - x + 4;
    }

    @Override
    public Double calculateDeriv(Double x) {
        return 3 * Math.pow(x, 2) - 1;
    }

    @Override
    public Double calculateSecondDeriv(Double x) {
        return 6 * x;
    }

    @Override
    public String toString() {
        return "x^3 - x + 4";
    }
}
