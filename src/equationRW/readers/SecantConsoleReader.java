package equationRW.readers;

import equasions.interfaces.EquationCollectable;
import matrixReaders.UPCommunitation;

public class SecantConsoleReader extends ConsoleReader{

    @Override
    public EquationCollectable read() {
        EquationCollectable equationCollectable = super.read();

        Double x1 = UPCommunitation.askForDouble(x -> (x >= equationCollectable.getBounds()[0]) && (x <= equationCollectable.getBounds()[1]),
                "Укажите начальное приближение, принадлежащее промежутку [" + equationCollectable.getBounds()[0] + ";" + equationCollectable.getBounds()[1] + "]",
                scanner);
        equationCollectable.setFirstClosnes(x1);

        return equationCollectable;
    }
}
