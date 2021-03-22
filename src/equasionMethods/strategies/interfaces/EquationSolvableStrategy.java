package equasionMethods.strategies.interfaces;

import equasions.interfaces.EquationCollectable;
import equationRW.writers.interfaces.EquationsWritable;
import matrixMethods.customExceptions.*;

public interface EquationSolvableStrategy {
    Double execute(EquationCollectable data) throws NonCompliaceException;
    void setWriter(EquationsWritable printable);
}
