package equasions.interfaces;

public interface EquationCollectable {

    Double[] getBounds();
    Calculatable getEquation();
    Double getAccuracity();
    Double getFirstApprox();

    void setBounds(Double[] bounds);
    void setEquation(Calculatable equation);
    void setAccuracity(Double accuracity);
    void setFirstClosnes(Double firstClosnes);
}
