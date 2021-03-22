package equationRW.readers;

import equasions.EquationData;
import equasions.EquationLibrary;
import equasions.interfaces.Calculatable;
import equasions.interfaces.EquationCollectable;
import equationRW.readers.interfaces.Constructable;
import equationRW.readers.interfaces.EqutionsReadable;
import matrixReaders.UPCommunitation;

import java.util.Scanner;

public class ConsoleReader implements EqutionsReadable {

    protected Scanner scanner = new Scanner(System.in);

    @Override
    public EquationCollectable read() {

        Double[] bounds = askForBounds();
        Double e = UPCommunitation.askForDouble(x -> (x < 1) && (x > 0), "Укажите точность e.", scanner);
//        Calculatable equation = askForEquation();

        EquationCollectable equationCollectable = new EquationData();
        equationCollectable.setAccuracity(e);
        equationCollectable.setBounds(bounds);
//        equationCollectable.setEquation(equation);

        return equationCollectable;
    }

    @Override
    public void setConstructable(Constructable constructable) {
        //TODO: переписать на обработку через класс
    }

    protected Double[] askForBounds() {
        Double[] bounds = new Double[2];
        boolean check = true;

        do {
            System.out.print("Введите границы a и b.");
            for(int i = 0; i < 2; i++) {
                bounds[i] = UPCommunitation.askForDouble(x -> true, "" , scanner);
            }

            if (bounds[0] < bounds[1]) {
                check = false;
            }else {
                System.out.println("Значение границы A должна быть меньше значения границы B!");
            }
        }while (check);

        return bounds;
    }

//    protected Calculatable askForEquation() {
//        String message = "Выберете уравнение \n" + equationLibrary.getListOfEquations();
//        Integer equationId;
//
//        equationId = UPCommunitation.askForInteger(x -> (x > 0) && (x <= equationLibrary.getNumbOfEquations()), message, scanner);
//
//        return equationLibrary.getEquationByKey(equationId);
//    }
}
