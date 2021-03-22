package matrixReaders.factrories.interfaces;

import matrixReaders.readWriters.interfaces.MatrixRWable;

public interface ReaderFactory {
    MatrixRWable createMatrixFileRW();
    MatrixRWable createMatrixStdRW();
}
