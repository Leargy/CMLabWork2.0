package service;

import equasionMethods.EquationSolver;
import equasionMethods.strategies.BinMethod;
import equasionMethods.strategies.DrawChart;
import equasionMethods.strategies.IterationMethod;
import equasionMethods.strategies.SecantMethod;
import equasionMethods.strategies.interfaces.EquationSolvableStrategy;
import equasions.EquationLibrary;
import equasions.interfaces.Calculatable;
import equasions.interfaces.EquationCollectable;
import equationRW.factories.ReadersFactory;
import equationRW.factories.WritersFactory;
import equationRW.factories.interfaces.ReadersFactroriable;
import equationRW.factories.interfaces.WritersFactoriable;
import equationRW.readers.customExceptions.DataValidationException;
import equationRW.readers.dataConstructors.EquationDataConstructor;
import equationRW.readers.dataConstructors.EquationDataConstructorVClos;
import equationRW.readers.interfaces.EqutionsReadable;
import matrixMethods.MatrixSolver;
import matrixMethods.customExceptions.*;
import matrixMethods.strategies.iterative.GausZeidelMethod;
import matrixReaders.UPCommunitation;

import matrixReaders.data.MatrixData;
import matrixReaders.data.interfaces.Matrixable;
import matrixReaders.factrories.MatrixRWFactory;
import matrixReaders.factrories.interfaces.ReaderFactory;
import matrixReaders.readWriters.interfaces.MatrixRWable;

import java.io.IOException;
import java.util.Scanner;

public class TotalCommander {
    protected EquationLibrary equationLibrary = new EquationLibrary();
    private final EquationSolver equationSolver;
    private final MatrixSolver matrixSolver;
    private final ReaderFactory matrixReadFactory;
    private final WritersFactoriable equationWriteFactory;
    private final ReadersFactroriable equationReaderFactory;
    private final DrawChart drawChart;
//    private ProducerConsumer<EquationCollectable> producerConsumer;

    public TotalCommander() {
        equationSolver = new EquationSolver();
        matrixSolver = new MatrixSolver();
        matrixReadFactory = new MatrixRWFactory();
        equationWriteFactory = new WritersFactory();
        equationReaderFactory = new ReadersFactory();
        drawChart = new DrawChart();
    }

    public void readMatrixFromFile() {
        MatrixRWable matrixFileRW = matrixReadFactory.createMatrixFileRW();

        try {
            MatrixData data = matrixFileRW.read();
            Matrixable result = gausZendelCount(data);

            UPCommunitation.printMatrix(result, data.getErrorValue());

        }catch (NonCompliaceException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void readMatrixFromConsole() {
        MatrixRWable matrixStdRW = matrixReadFactory.createMatrixStdRW();
        try {
            MatrixData data = matrixStdRW.read();
            Matrixable result = gausZendelCount(data);

            UPCommunitation.printMatrix(result, data.getErrorValue() );

        }catch (NonCompliaceException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private Matrixable gausZendelCount(MatrixData matrixData) throws NonCompliaceException{
        matrixSolver.setSolveStrategy(new GausZeidelMethod());

        Matrixable result = null;
        result = matrixSolver.executeStrategy(matrixData);

        return result;
    }


//    public void readEquationFromFile() {
//
//    }
//
//    public void readEquationFromConsole() {
//        EqutionsReadable equationsReader = equationReaderFactory.createStdReader();
//
//        EquationCollectable equationCollectable = equationsReader.read();
//
//        try {
//            producerConsumer.produce(equationCollectable);
//            System.out.println("Уравнение успешно добавлено в буффер.");
//        }catch (NonCompliaceException ex) {
//            System.err.println(ex.getMessage());
//        }
//    }

    public void iterativeMethodExecute() {
        EquationSolvableStrategy strategy = new IterationMethod();

        equationSolver.setEquasionSolveStrategy(strategy);

        EqutionsReadable reader = null;

        int strat = UPCommunitation.askForInteger(x -> (x > 0) && (x <= 2),
                "Читать данные из файла или из консоли? \n -[1] Файл \n -[2] Консоль", new Scanner(System.in));

        switch (strat) {
            case 1:
                reader = equationReaderFactory.createFileReader();
                reader.setConstructable(new EquationDataConstructor());
                break;
            default:
                reader = equationReaderFactory.createStdReader();
                break;
        }

        int wrtstrat = UPCommunitation.askForInteger(x -> (x > 0) && (x <= 2),
                "Писать в файл или в консоль? \n -[1] Файл \n -[2] Консоль", new Scanner(System.in));

        switch (wrtstrat) {
            case 1:
                strategy.setWriter(equationWriteFactory.createFileWriter());
                break;
            default:
                strategy.setWriter(equationWriteFactory.createStdWriter());
                break;
        }

        EquationCollectable equationCollectable;

        try {
            equationCollectable = reader.read();
            equationCollectable.setEquation(askForEquation());
            equationSolver.executeStrategy(equationCollectable);
            drawChart.draw(equationCollectable.getBounds()[0], equationCollectable.getBounds()[1], equationCollectable.getEquation());
        }catch (DataValidationException | IOException | NonCompliaceException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void secantMethodExecute() {
        EquationSolvableStrategy strategy = new SecantMethod();

        equationSolver.setEquasionSolveStrategy(strategy);

        EqutionsReadable reader = null;

        int strat = UPCommunitation.askForInteger(x -> (x > 0) && (x <= 2),
                "Читать данные из файла или из консоли? \n -[1] Файл \n -[2] Консоль", new Scanner(System.in));

        switch (strat) {
            case 1:
                reader = equationReaderFactory.createFileReader();
                reader.setConstructable(new EquationDataConstructorVClos());
                break;
            default:
                reader = equationReaderFactory.createStdSpecialReader();
                break;
        }

        int wrtstrat = UPCommunitation.askForInteger(x -> (x > 0) && (x <= 2),
                "Писать в файл или в консоль? \n -[1] Файл \n -[2] Консоль", new Scanner(System.in));

        switch (wrtstrat) {
            case 1:
                strategy.setWriter(equationWriteFactory.createFileWriter());
                break;
            default:
                strategy.setWriter(equationWriteFactory.createStdWriter());
                break;
        }

        EquationCollectable equationCollectable;

        try {
            equationCollectable = reader.read();
            equationCollectable.setEquation(askForEquation());
            equationSolver.executeStrategy(equationCollectable);
            drawChart.draw(equationCollectable.getBounds()[0], equationCollectable.getBounds()[1], equationCollectable.getEquation());
        }catch (DataValidationException | IOException | NonCompliaceException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void binMethodExecute() {
        EquationSolvableStrategy strategy = new BinMethod();

        equationSolver.setEquasionSolveStrategy(strategy);

        EqutionsReadable reader = null;

        int strat = UPCommunitation.askForInteger(x -> (x > 0) && (x <= 2),
                "Читать данные из файла или из консоли? \n -[1] Файл \n -[2] Консоль", new Scanner(System.in));

        switch (strat) {
            case 1:
                reader = equationReaderFactory.createFileReader();
                reader.setConstructable(new EquationDataConstructor());
                break;
            default:
                reader = equationReaderFactory.createStdReader();
                break;
        }

        int wrtstrat = UPCommunitation.askForInteger(x -> (x > 0) && (x <= 2),
                "Писать в файл или в консоль? \n -[1] Файл \n -[2] Консоль", new Scanner(System.in));

        switch (wrtstrat) {
            case 1:
                strategy.setWriter(equationWriteFactory.createFileWriter());
                break;
            default:
                strategy.setWriter(equationWriteFactory.createStdWriter());
                break;
        }

        EquationCollectable equationCollectable;

        try {
            equationCollectable = reader.read();
            equationCollectable.setEquation(askForEquation());
            equationSolver.executeStrategy(equationCollectable);
            drawChart.draw(equationCollectable.getBounds()[0], equationCollectable.getBounds()[1], equationCollectable.getEquation());
        }catch (DataValidationException | IOException | NonCompliaceException ex) {
            System.err.println(ex.getMessage());
        }

    }

        protected Calculatable askForEquation() {
        String message = "Выберете уравнение \n" + equationLibrary.getListOfEquations();
        Integer equationId;

        equationId = UPCommunitation.askForInteger(x -> (x > 0) && (x <= equationLibrary.getNumbOfEquations()), message, new Scanner(System.in));

        return equationLibrary.getEquationByKey(equationId);
    }



//    public void showEquationBuffer() {
//        TODO:show equationBuffer
//    }
}
