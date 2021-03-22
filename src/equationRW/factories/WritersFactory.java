package equationRW.factories;

import equationRW.factories.interfaces.WritersFactoriable;
import equationRW.writers.ConsoleWriter;
import equationRW.writers.EFileWriter;
import equationRW.writers.interfaces.EquationsWritable;

public class WritersFactory implements WritersFactoriable {

    @Override
    public EquationsWritable createFileWriter() {
        return new EFileWriter();
    }

    @Override
    public EquationsWritable createStdWriter() {
        return new ConsoleWriter();
    }
}
