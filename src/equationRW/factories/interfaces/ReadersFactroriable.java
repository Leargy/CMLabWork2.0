package equationRW.factories.interfaces;

import equationRW.readers.interfaces.EqutionsReadable;

public interface ReadersFactroriable {
    EqutionsReadable createFileReader();
    EqutionsReadable createStdReader();
    EqutionsReadable createStdSpecialReader();
}
