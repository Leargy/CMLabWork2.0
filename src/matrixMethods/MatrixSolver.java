package matrixMethods;

import matrixMethods.strategies.MatrixSolvableStrategy;
import matrixMethods.customExceptions.*;
import matrixReaders.data.MatrixData;
import matrixReaders.data.interfaces.Matrixable;

import java.util.ArrayList;
import java.util.Iterator;

public class MatrixSolver {
    private MatrixSolvableStrategy solveStrategy;

    public void setSolveStrategy(MatrixSolvableStrategy solveStrategy) {
        this.solveStrategy = solveStrategy;
    }

    public Matrixable executeStrategy(MatrixData matrixData) throws NonCompliaceException{
        if (checkForDegeneracy(matrixData.getMatrix()) == -1.0) throw new NonCompliaceException("Детерминант мантрицы равен 0.");
        return solveStrategy.execute(matrixData);
    }

    public Double checkForDegeneracy(Matrixable matrix) {
        Iterator<ArrayList<Double>> iter = matrix.getArrayMatrix().iterator();
        int size = matrix.sizeJ();

        Double[][] tmpMatrix = new Double[size][size];
        int i = 0;
        while (iter.hasNext()) {
            ArrayList<Double> tmpArr = iter.next();
            for(int j = 0; j < size; j++) {
                tmpMatrix[i][j] = tmpArr.get(j);
            }
            i++;
        }

        double result = calculateMinor(tmpMatrix);
        if(result == 0.0) return -1.0;

        return result;
    }

    private double calculateMinor(Double[][] matrix) {
        int n = matrix.length;
        if(n == 1) return matrix[0][0];

        double result = 0;
        Double minor[][] = new Double[n-1][n-1];

        int factor = 1;
        for(int i = 0; i < n; i++) {
            int x = 0, y = 0;
            for(int j = 1; j < n; j++) {
                for(int k = 0; k < n; k++) {

                    if(i == k) {
                        continue;
                    }

                    minor[y][x] = matrix[j][k];

                    x +=1;
                    if(x == n - 1) {
                        x = 0;
                        y +=1;
                    }
                }
            }
            result += factor * matrix[0][i] * calculateMinor(minor);
            factor *= (-1);
        }

        return result;
    }
}
