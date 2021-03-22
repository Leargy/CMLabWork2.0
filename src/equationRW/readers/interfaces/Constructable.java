package equationRW.readers.interfaces;

import equasions.interfaces.EquationCollectable;
import equationRW.readers.customExceptions.DataValidationException;

import java.nio.CharBuffer;

public interface Constructable {
    EquationCollectable construct(CharBuffer buffer) throws DataValidationException;
}
