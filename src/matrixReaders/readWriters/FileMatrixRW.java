package matrixReaders.readWriters;

import matrixMethods.customExceptions.*;
import matrixReaders.data.MatrixData;
import matrixReaders.data.Matrix;
import matrixReaders.data.interfaces.Matrixable;
import matrixReaders.UPCommunitation;

import java.io.*;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Scanner;

public class FileMatrixRW implements matrixReaders.readWriters.interfaces.MatrixRWable {

    @Override
    public MatrixData read() throws NonCompliaceException {
        String fileName = askForFileName();
        MatrixData matrixData = new MatrixData();
        try(InputStreamReader reader = new FileReader(fileName)) {

            readHeader(reader, matrixData);
            matrixData.setMatrix(readMatrix(reader, matrixData));

        }catch (IOException | InvalidHeaderException | NonCompliaceException ex) {
            throw new NonCompliaceException(ex.getMessage());
        }
        return matrixData;
    }

    @Override
    public int write(Matrixable matrix) {
        return 0;
    }

    private String askForFileName() {
        Scanner scanner = new Scanner(System.in);
        return UPCommunitation.askForString(x -> x.contains(".txt"),"Введите название файла вида: name.txt", scanner);
    }

    private Matrixable createMatrix(ArrayList<ArrayList<Double>> rowMatrix) {
        return new Matrix(rowMatrix);
    }

    private void readHeader(InputStreamReader reader, MatrixData matrixData) throws InvalidHeaderException, IOException {
        char chr;
        int n = 0;
        double e = -1.0;
        int m = -1;
        String str;

        for (int i = 0; i != 3; i++) {
            str = "";
            while ((chr = (char) reader.read()) != ' ' && chr != '\r') {
                str = str.concat(String.valueOf(chr));
            }
            switch (i) {
                case 0:
                    n = Integer.valueOf(str);
                    break;
                case 1:
                    e = Double.valueOf(str);
                    break;
                case 2:
                    m = Integer.valueOf(str);
                    break;
            }
            if(chr == '\r') reader.skip(1);
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

    private Matrixable readMatrix(InputStreamReader reader, MatrixData matrixData) throws IOException, NonCompliaceException{
        ArrayList<ArrayList<Double>> rowMatrix = new ArrayList<>();
        ArrayList<Double> newRow = new ArrayList<>();
        long n = matrixData.getMatrixRowSize();
        String num = "";
        char c;

        char[] buf = new char[256];
        CharBuffer buffer = CharBuffer.allocate(256 * 2);
        reader.read(buffer);

        buffer.flip();

        while (buffer.hasRemaining()) {
            c = buffer.get();

            if(c != ' ' && c != '\r' && c != '\n') {
                num = num.concat(String.valueOf(c));
                continue;
            }

            if(num == "" || num == "\n")throw new NonCompliaceException("Данные в файле не соответствуют шаблону.");
            else if(num.matches("\\D+")) throw new NonCompliaceException("Файл содержет поврежденные данные. Проверьте ваш файл.");
            newRow.add(Double.valueOf(num));
            if (c == ' ') num = "";

            if(c == '\r') {
                num = "";
                buffer.get();

                if((newRow.size() - 1) != n) throw new NonCompliaceException("Число переменных не соответствует указанному");
                rowMatrix.add(newRow);
                newRow = new ArrayList<>();

                continue;
            }

        }
        if(num != "") newRow.add(Double.valueOf(num));
        rowMatrix.add(newRow);

        if(rowMatrix.size() != n) throw new NonCompliaceException("Матрица не является квадратной");

        return createMatrix(rowMatrix);
    }
}
