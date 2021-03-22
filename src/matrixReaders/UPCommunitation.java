package matrixReaders;

import matrixReaders.data.interfaces.Matrixable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.function.Predicate;
import matrixMethods.customExceptions.*;

public abstract class UPCommunitation {

    public static String askForString(Predicate<String> request, String message, Scanner scanner) {
        String line = null;
        do {
            try {
                System.out.print(message);
                System.out.print("\n:");
                line = scanner.nextLine();
                line = line.trim();
                if (!request.test(line)) {
                    throw new NonCompliaceException("\nВведенные данные не соответствуют шаблону.");
                }
            }catch (NonCompliaceException ex) {
                System.err.println(ex.getMessage());
            }
        }while (!request.test(line));
        return line;
    }

    public static void printMatrix(Matrixable matrix, double approx) {
        int sizeI = matrix.sizeI();
        ArrayList<ArrayList<Double>> tmpMatrix = matrix.getArrayMatrix();
        Iterator<ArrayList<Double>> iter = tmpMatrix.iterator();

        int i = -1;

        int tmpSize = tmpMatrix.get(tmpMatrix.size() - 1).size();
        String lastVal = String.valueOf(tmpMatrix.get(tmpMatrix.size() - 1).get(tmpSize - 1));


        int charID;
        if(approx == 0) charID = 1;
        else  charID = String.valueOf(approx).length() - 1;

        Double tmpValue;
        Double scale = Math.pow(10, charID);


        while (iter.hasNext()) {

            ArrayList<Double> tmpRow = iter.next();
            i++;
            String stringRow = String.valueOf(i);
            stringRow = stringRow.concat("|");

            for (int j = 0; j < sizeI; j++) {
                tmpValue = Math.ceil(tmpRow.get(j) * scale) / scale;
                stringRow = stringRow.concat(String.valueOf(tmpValue));
                stringRow = stringRow.concat("|");
            }
            System.out.println(stringRow);
        }
        System.out.println();
    }

    public static Double askForDouble(Predicate<Double> request, String message, Scanner scanner) {
        Double answer = -999999999999999.0;
        String line;

        do {
            try {
                System.out.print(message);
                System.out.print("\n:");
                line = scanner.nextLine();
                line = line.trim();

                answer = Double.valueOf(line);

                if (!request.test(answer)) {
                    throw new NonCompliaceException("\nВведенные данные не удовлетворяют условию.");
                }
            }catch (NumberFormatException | NonCompliaceException ex) {
                System.err.println(ex.getMessage());
            }
        }while (!request.test(answer));

        return answer;
    }

    public static Integer askForInteger(Predicate<Integer> request, String message, Scanner scanner) {
        Integer answ = 99999999;
        String line;
        do {
            try {
                System.out.print(message);
                System.out.print("\n:");
                line = scanner.nextLine();
                line = line.trim();

                answ = Integer.valueOf(line);

                if (!request.test(answ)) {
                    throw new NonCompliaceException("\nВведенные данные не удовлетворяют условию.");
                }
            }catch (NumberFormatException | NonCompliaceException ex) {
                System.err.println(ex.getMessage());
            }
        }while (!request.test(answ));

        return answ;
    }

}
