package matrixReaders.readWriters;

import matrixMethods.customExceptions.*;
import matrixReaders.data.Matrix;
import matrixReaders.data.MatrixData;
import matrixReaders.data.interfaces.Matrixable;
import matrixReaders.readWriters.interfaces.MatrixRWable;
import matrixReaders.UPCommunitation;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class StdMatrixRW implements MatrixRWable {


    @Override
    public MatrixData read() throws NonCompliaceException {

        MatrixData matrixData = new MatrixData();
        try(InputStreamReader inputStreamReader = new InputStreamReader(System.in)) {

            readHeader(inputStreamReader, matrixData);
            matrixData.setMatrix(readMatrix(inputStreamReader, matrixData));

        }catch (IOException | InvalidHeaderException | NonCompliaceException ex) {
            throw new NonCompliaceException(ex.getMessage());
        }
        return matrixData;
    }

    @Override
    public int write(Matrixable matrix) {
        return 0;
    }
    private void readHeader(InputStreamReader reader, MatrixData matrixData) throws InvalidHeaderException, IOException {
        int n = 0;
        double e = -1.0;
        int m = -1;
        String regex ="^(\\d+\\s\\d+.\\d+\\s\\d+)$";

        Scanner scanner = new Scanner(reader);

        String row = UPCommunitation.askForString(x -> x.trim().matches(regex), "Введите последовательно - \"n e M\"", scanner);

        String[] cafRow = row.split(" ");

        for(int i = 0; i < 3; i++) {
            switch (i) {
                case 0:
                    n = Integer.valueOf(cafRow[i]);
                    break;
                case 1:
                    e = Double.valueOf(cafRow[i]);
                    break;
                case 2:
                    m = Integer.valueOf(cafRow[i]);
                    break;
            }
        }

        if(n <= 0 || n > 20) {
            throw new InvalidHeaderException("Порядок матрицы не должен быть меньше 0 или больше 20.");
        }else if(e > 1 || e < 0) {
            throw new InvalidHeaderException("Погрешность вычислений должна быть в диапозоне от 0 до 1.");
        }else if( m <= 0) {
            throw new InvalidHeaderException("Чило интераций должно быть положительным.");
        }

        matrixData.setMatrixRowSize(n);
        matrixData.setErrorValue(e);
        matrixData.setMaxIterationNumber(m);
    }

    private Matrixable readMatrix(InputStreamReader reader, MatrixData matrixData) throws IOException, NonCompliaceException {

        System.out.println("Заполняем матрицу");

        int n = matrixData.getMatrixRowSize();
        ArrayList<ArrayList<Double>> rawMatrix = new ArrayList<>();


        Scanner scanner = new Scanner(reader);

        String regex = "^((-?\\d+(.\\d+)?\\s){"+(n)+"}-?\\d+(.\\d+)?)$";

        for(int i = 0; i < n; i++) {
            ArrayList<Double> newRow = new ArrayList<>();
            String row = UPCommunitation.askForString(x -> x.trim().matches(regex), String.valueOf(i + 1), scanner);
            String[] tmpRow = row.split(" ");
            for(int j = 0; j <= n; j++) {
                newRow.add( Double.valueOf(tmpRow[j]));
            }
            rawMatrix.add(newRow);
        }


        Matrixable matrixable = new Matrix(rawMatrix);

        return matrixable;
    }
}
