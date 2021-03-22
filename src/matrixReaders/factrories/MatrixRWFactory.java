package matrixReaders.factrories;

import matrixReaders.factrories.interfaces.ReaderFactory;
import matrixReaders.readWriters.FileMatrixRW;
import matrixReaders.readWriters.StdMatrixRW;
import matrixReaders.readWriters.interfaces.MatrixRWable;

public class MatrixRWFactory implements ReaderFactory {
    public MatrixRWable createMatrixFileRW() {
        return new FileMatrixRW();
    }
    public MatrixRWable createMatrixStdRW() {
        return new StdMatrixRW();
    }
}
