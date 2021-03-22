package equationRW.readers;

import equasions.interfaces.EquationCollectable;
import equationRW.readers.customExceptions.DataValidationException;
import equationRW.readers.interfaces.Constructable;
import equationRW.readers.interfaces.EqutionsReadable;
import matrixReaders.UPCommunitation;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.CharBuffer;
import java.util.Scanner;

public class EFileReader implements EqutionsReadable {
    private Constructable constructable;

    @Override
    public EquationCollectable read() throws DataValidationException, IOException {
        EquationCollectable equationCollectable;

        try {
            equationCollectable = constructable.construct(readFile());
        }catch (DataValidationException | IOException ex) {
            throw ex;
        }

        return equationCollectable;
    }

    @Override
    public void setConstructable(Constructable constructable) {
        this.constructable = constructable;
    }

    private CharBuffer readFile() throws IOException{
        String fileName = UPCommunitation.askForString(x -> x.contains(".txt"),"Имя файла вида: name.txt", new Scanner(System.in));
        CharBuffer buffer = CharBuffer.allocate(256 * 2);
        try(InputStreamReader reader = new java.io.FileReader(fileName)){
            reader.read(buffer);
            buffer.flip();
        }catch (IOException ex) {
            throw ex;
        }
        return buffer;
    }
}
