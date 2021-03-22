package matrixReaders.data;

import matrixReaders.data.interfaces.Matrixable;

public class MatrixData {
    private Matrixable matrix = null;
    private int matrixRowSize;
    private double errorValue;
    private int maxIterationNumber;

    public void setMatrix(Matrixable matrix) { this.matrix = matrix; }
    public void setErrorValue(double errorValue) { this.errorValue = errorValue; }
    public void setMatrixRowSize(int matrixRowSize) { this.matrixRowSize = matrixRowSize; }
    public void setMaxIterationNumber(int maxIterationNumber) { this.maxIterationNumber = maxIterationNumber; }

    public Matrixable getMatrix() { return matrix; }
    public double getErrorValue() { return errorValue; }
    public int getMatrixRowSize() { return matrixRowSize; }
    public int getMaxIterationNumber() { return maxIterationNumber; }

    public boolean isValid() {
        if(matrix != null && matrixRowSize > 0 && errorValue >= 0 && errorValue <= 1 && maxIterationNumber > 0) return true;
        return false;
    }

}
