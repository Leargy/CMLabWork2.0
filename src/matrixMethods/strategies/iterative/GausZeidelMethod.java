package matrixMethods.strategies.iterative;

import matrixMethods.strategies.MatrixSolvableStrategy;
import matrixMethods.customExceptions.*;
import matrixReaders.data.MatrixData;
import matrixReaders.data.Matrix;
import matrixReaders.data.interfaces.Matrixable;
import matrixReaders.UPCommunitation;

import java.util.ArrayList;
import java.util.Iterator;

public class GausZeidelMethod implements MatrixSolvableStrategy {
    Matrixable matrix;
    int iterNum;

    @Override
    public Matrixable execute(MatrixData data) throws NonCompliaceException{
        this.matrix = data.getMatrix();
        this.iterNum = data.getMaxIterationNumber();
        Matrixable resultData;

        try {
            diagonalization();
            Matrixable oddsMatrix = adduction(data.getErrorValue());
            isConverges(oddsMatrix);
            resultData = calculate(oddsMatrix, data.getMaxIterationNumber(), oddsMatrix.getFreElementsVector(), data.getErrorValue());
            resultData.getArrayMatrix().trimToSize();
        }catch (NonCompliaceException ex) {
            throw new NonCompliaceException(ex.getMessage());
        }
        return resultData;
    }

    private void diagonalization() {
        ArrayList<ArrayList<Double>> tmp = matrix.getArrayMatrix();

        Matrixable matrix = new Matrix(tmp);

        int size = matrix.sizeJ();

        for(int i = 0; i < size; i++) {
            ArrayList<Double> vector = matrix.extractVector(i);
            double max = 0.0;
            int swapId = 0;

            for(int j = i; j < size; j++) {
                if(max < vector.get(j)) {
                    max = vector.get(j);
                    swapId = j;
                }
            }

            if(swapId != 0) {
                ArrayList<Double> tmpRow = tmp.get(swapId);
                ArrayList<Double> oldRow = tmp.get(i);
                tmp.set(i, tmpRow);
                tmp.set(swapId, oldRow);
            }
            UPCommunitation.printMatrix(matrix, 0);
        }
    }

    private Matrixable adduction(Double e) {
        ArrayList<ArrayList<Double>> newMatrix = new ArrayList<>();
        Iterator<ArrayList<Double>> iter = matrix.getArrayMatrix().iterator();
        Matrixable adductetMatrix = new Matrix(newMatrix);
        double res;

        int a = String.valueOf(e).length() - 1;

        Double scale = Math.pow(10, a);

        int i = -1;

        while (iter.hasNext()) {
            ArrayList<Double> newTmpRow = new ArrayList<>();
            ArrayList<Double> tmpRow = iter.next();

            i++;
            int size = tmpRow.size();
            double divider = tmpRow.get(i);

            for(int j = 0; j < size; j++) {
                Double tmpValue;
                String stringRow = "";
                if(j == i) newTmpRow.add(0.0);
                else {
                    if(j == (size - 1)) {
                        res = tmpRow.get(j) / divider;
                    }else res = -1 * (tmpRow.get(j) / divider);
                    tmpValue = Math.ceil(res * scale) / scale;
                    newTmpRow.add(tmpValue);
                }
            }
            newMatrix.add(newTmpRow);
        }

        System.out.println("Преобразование к виду x = Cx + D");
        UPCommunitation.printMatrix(adductetMatrix, e);

        return adductetMatrix;
    }

    private void isConverges(Matrixable oddsMatrix) throws NonCompliaceException {
        ArrayList<Double> vector = new ArrayList<>();

        Iterator<ArrayList<Double>> iter = oddsMatrix.getArrayMatrix().iterator();

        while (iter.hasNext()) {
            ArrayList<Double> tmpRow = iter.next();
            int size = tmpRow.size() - 1;
            double rowSum = 0;
            for(int i = 0; i < size; i++) {
                rowSum += tmpRow.get(i);
            }
            rowSum = Math.abs(rowSum);

            vector.add(rowSum);
        }

        vector.sort(Double::compareTo);

        for(int i = 0; i < vector.size(); i++) {
            System.out.printf("%.2f \n", vector.get(i));
        }
        System.out.println();

        if(vector.get(vector.size() - 1) > 1) throw new NonCompliaceException("Условие сходимости не выполняется: " + vector.get(vector.size() - 1) +" > 1");
    }

    private Matrixable calculate(Matrixable oddsMatrix, int iterationNum, ArrayList<Double> approxVector, Double e) throws NonCompliaceException{

        if(iterationNum == 0) throw new NonCompliaceException("Число итераций превышено!");

        ArrayList<Double> tmpOdds = approxVector;
        ArrayList<Double> copyTmpOdds = (ArrayList<Double>) approxVector.clone();
        ArrayList<Double> freeCoefficientRow = oddsMatrix.getFreElementsVector();

        Iterator<ArrayList<Double>> iter = oddsMatrix.getArrayMatrix().iterator();

        int k = 0;
        while (iter.hasNext()) {
            double x = 0;
            ArrayList<Double> tmpRow = iter.next();
            for(int i = 0; i < oddsMatrix.sizeJ(); i++) {
                x += tmpRow.get(i) * approxVector.get(i);
            }
            x += freeCoefficientRow.get(k);
            tmpOdds.set(k, x);
            k++;
        }

        int size = oddsMatrix.sizeJ();

        for(int i = 0; i < size; i++) {
            copyTmpOdds.set(i,Math.abs(copyTmpOdds.get(i) - tmpOdds.get(i)));
        }
        copyTmpOdds.sort(Double::compareTo);

        Matrixable matrixData = null;
        if(copyTmpOdds.get(size - 1) > e) {
            matrixData = calculate(oddsMatrix, iterationNum - 1, (ArrayList<Double>) tmpOdds.clone(), e);
        }


        ArrayList<ArrayList<Double>> resultArr;
        tmpOdds.add(copyTmpOdds.get(size - 1));

        if(matrixData == null) {
             resultArr = new ArrayList<>();
             for (int i = 0; i <= iterNum - iterationNum + 1; i++){
                 resultArr.add(new ArrayList<>());
             }

             resultArr.set(0,freeCoefficientRow);
             resultArr.get(0).add(0.0);

             matrixData = new Matrix(resultArr);
        }else {
            resultArr = matrixData.getArrayMatrix();
        }

        resultArr.get(iterNum - iterationNum + 1).addAll(tmpOdds);

        return matrixData;
    }
}
