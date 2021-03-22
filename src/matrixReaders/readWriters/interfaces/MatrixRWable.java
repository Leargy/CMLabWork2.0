package matrixReaders.readWriters.interfaces;

import matrixMethods.customExceptions.*;
import matrixReaders.data.MatrixData;
import matrixReaders.data.interfaces.Matrixable;

public interface MatrixRWable {
    MatrixData read() throws NonCompliaceException;
    int write(Matrixable matrix);
}
