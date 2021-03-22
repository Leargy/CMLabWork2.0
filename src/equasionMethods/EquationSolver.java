package equasionMethods;

import equasionMethods.strategies.interfaces.EquationSolvableStrategy;
import equasions.interfaces.EquationCollectable;
import matrixMethods.customExceptions.*;

public class EquationSolver {
    EquationSolvableStrategy equationSolvableStrategy;

    public void setEquasionSolveStrategy(EquationSolvableStrategy equationSolvableStrategy) {
        this.equationSolvableStrategy = equationSolvableStrategy;
    }

    public Double executeStrategy(EquationCollectable equation) throws NonCompliaceException {
        return equationSolvableStrategy.execute(equation);
    }

}
