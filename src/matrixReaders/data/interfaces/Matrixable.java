package matrixReaders.data.interfaces;

import java.util.ArrayList;

public interface Matrixable {
    void addRow(ArrayList<Double> row);
    ArrayList<Double> getFreElementsVector();
    ArrayList<Double> extractVector(int columnId);
    ArrayList<ArrayList<Double>> getArrayMatrix();
    void setMatrix(ArrayList<ArrayList<Double>> matrix);

    int sizeI();
    int sizeJ();


}
