package equationRW.factories.interfaces;

import equationRW.writers.interfaces.EquationsWritable;

public interface WritersFactoriable {
    EquationsWritable createFileWriter();
    EquationsWritable createStdWriter();
}
