package equasions;

import equasions.interfaces.Calculatable;
import equasions.interfaces.EquationCollectable;

public class EquationData implements EquationCollectable {
    private Double[] bounds;
    private Double firstCloseness;
    private Double accuracity;
    private Calculatable equation;


    @Override
    public Double[] getBounds() {
        return bounds;
    }

    @Override
    public Calculatable getEquation() {
        return equation;
    }

    @Override
    public Double getAccuracity() {
        return accuracity;
    }

    @Override
    public Double getFirstApprox() {
        return firstCloseness;
    }

    @Override
    public void setBounds(Double[] bounds) {
        this.bounds = bounds;
    }

    @Override
    public void setEquation(Calculatable equation) {
        this.equation = equation;
    }

    @Override
    public void setAccuracity(Double accuracity) {
        this.accuracity = accuracity;
    }

    @Override
    public void setFirstClosnes(Double firstClosnes) {
        this.firstCloseness = firstClosnes;
    }
}
