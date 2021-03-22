package equationRW.readers.dataConstructors;

import equasions.EquationData;
import equasions.interfaces.EquationCollectable;
import equationRW.readers.customExceptions.DataValidationException;
import equationRW.readers.interfaces.Constructable;

import java.nio.CharBuffer;

public class EquationDataConstructor implements Constructable {


    @Override
    public EquationCollectable construct(CharBuffer buffer) throws DataValidationException {
        String resString = "";
        EquationCollectable equationCollectable = new EquationData();
        Double[] bounds = new Double[2];
        Double accuracity;
        String tmp;

        tmp = getNextValue(buffer);

        resString = resString.concat(tmp + " ");
        try {
            bounds[0] = Double.valueOf(tmp);

            tmp = getNextValue(buffer);
            resString = resString.concat(tmp);

            bounds[1] = Double.valueOf(tmp);

            checkBounds(bounds);

            tmp = getNextValue(buffer);
            resString = resString.concat(tmp);

            accuracity = Double.valueOf(tmp);

            checkAccuracety(accuracity);

            equationCollectable.setBounds(bounds);
            equationCollectable.setAccuracity(accuracity);
        }catch (NumberFormatException | DataValidationException ex) {
            if(ex.getMessage().contains("empty String")) throw new DataValidationException(resString.concat(" <- Недостаточно начальных данных"));
            if(ex instanceof NumberFormatException) throw new DataValidationException(resString.concat(" <- ошибка в формате значения."));
            else throw new DataValidationException(resString.concat(" <- " + ex.getMessage()));
        }

        return equationCollectable;
    }

    protected void checkBounds(Double[] bounds) throws DataValidationException {
        if(bounds[0] >= bounds[1]) {
            throw new DataValidationException("Левая граница не должна быть больше/равно правой");
        }
    }
    protected void checkAccuracety(Double acc) throws DataValidationException {
        if(acc >= 1 || acc <= 0) throw new DataValidationException("точность должна быть в интервале (0;1)");
    }

    protected String getNextValue(CharBuffer buffer) {
        char c;
        StringBuilder val = new StringBuilder();

        while(buffer.hasRemaining()) {
            c = buffer.get();

            if(c == ' ' || c == '\n') {
                break;
            }

            val.append(c);
        }

        return val.toString();
    }
}
