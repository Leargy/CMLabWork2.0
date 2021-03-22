package matrixReaders.data;

import matrixReaders.data.interfaces.Matrixable;

import java.util.ArrayList;
import java.util.Iterator;

public class Matrix implements Matrixable {
    ArrayList<ArrayList<Double>> matrix;
    ArrayList<Double> freElementsVector;

    public Matrix(ArrayList<ArrayList<Double>> matrix) {
        this.matrix = matrix;
        freElementsVector = null;
    }

    public ArrayList<ArrayList<Double>> getArrayMatrix() { return matrix; }
    public void setMatrix(ArrayList<ArrayList<Double>> matrix) { this.matrix = matrix; }

    @Override
    public int sizeI() {
        if(matrix.isEmpty()) return 0;
        return matrix.get(0).size();
    }

    @Override
    public int sizeJ() {
        return matrix.size();
    }

    @Override
    public void addRow(ArrayList<Double> row) {
        matrix.add(row);
    }

    @Override
    public ArrayList<Double> getFreElementsVector() {
        if (freElementsVector != null) return freElementsVector;
        return extractVector(matrix.get(0).size() - 1);
    }

    @Override
    public ArrayList<Double> extractVector(int columnId) {
        ArrayList<Double> resultVector = new ArrayList<>();
        Iterator<ArrayList<Double>> iter= matrix.iterator();
        ArrayList<Double> tempVector;

        while (iter.hasNext()) {
            tempVector = iter.next();

            resultVector.add(tempVector.get(columnId));
        }

        return resultVector;
    }
}
