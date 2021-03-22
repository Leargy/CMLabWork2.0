package equationRW.readers.dataConstructors;

import equasions.interfaces.EquationCollectable;
import equationRW.readers.customExceptions.DataValidationException;

import java.nio.CharBuffer;

public class EquationDataConstructorVClos extends EquationDataConstructor {

    @Override
    public EquationCollectable construct(CharBuffer buffer) throws DataValidationException {
        String resString = "";

        String tmp;
        EquationCollectable equationCollectable = super.construct(buffer);
        tmp = equationCollectable.getBounds()[0] + " " + equationCollectable.getBounds()[1] + " " + equationCollectable.getAccuracity();
        resString = tmp;

        Double approx;


        try {
            tmp = getNextValue(buffer);

            approx = Double.valueOf(tmp);

            checkFirstApprox(approx, equationCollectable.getBounds());
            equationCollectable.setFirstClosnes(approx);

        }catch (NumberFormatException | DataValidationException ex) {
            if(ex.getClass().isInstance(NumberFormatException.class)) throw new DataValidationException(resString.concat("<- ошибка в значении"));
            else throw new DataValidationException(resString.concat("<- " + ex.getMessage()));
        }
        return equationCollectable;
    }

    protected void checkFirstApprox(Double approx, Double[] bounds) throws DataValidationException {
        if(bounds[0] >= approx || bounds[1] <= approx)
            throw new DataValidationException("Приближение должно лежать в интервале ("+bounds[0] + ";" + bounds[1] + ")");
    }
}
