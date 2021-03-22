package equationRW.readers.interfaces;

import equasions.interfaces.EquationCollectable;
import equationRW.readers.customExceptions.DataValidationException;

import java.io.IOException;

public interface EqutionsReadable {
    EquationCollectable read() throws DataValidationException, IOException;
    void setConstructable(Constructable constructable);
}
