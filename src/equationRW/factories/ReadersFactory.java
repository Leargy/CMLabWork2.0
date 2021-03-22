package equationRW.factories;

import equationRW.factories.interfaces.ReadersFactroriable;
import equationRW.readers.ConsoleReader;
import equationRW.readers.EFileReader;
import equationRW.readers.SecantConsoleReader;
import equationRW.readers.interfaces.EqutionsReadable;

public class ReadersFactory implements ReadersFactroriable {
    @Override
    public EqutionsReadable createFileReader() {
        return new EFileReader();
    }

    @Override
    public EqutionsReadable createStdReader() {
        return new ConsoleReader();
    }

    @Override
    public EqutionsReadable createStdSpecialReader() {
        return new SecantConsoleReader();
    }
}
