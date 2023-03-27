/*
 * Created on Sun Mar 26 2023
 *
 * Copyright (c) storycraft. Licensed under the Apache Licence 2.0.
 */
package sh.pancake.serdemc.io;

import java.io.IOException;

public interface DataReader {
    byte readByte() throws IOException;
    default int readByteUnsigned() throws IOException {
        return Byte.toUnsignedInt(readByte());
    }

    int readBytes(byte[] dst, int offset, int length) throws IOException;

    default int readBytes(byte[] dst, int offset) throws IOException {
        return readBytes(dst, offset, dst.length);
    }
    default int readBytes(byte[] dst) throws IOException {
        return readBytes(dst, 0, dst.length);
    }

    short readShort() throws IOException;
    default int readShortUnsigned() throws IOException {
        return Short.toUnsignedInt(readShort());
    }

    int readInt() throws IOException;
    long readLong() throws IOException;

    default float readFloat() throws IOException {
        return Float.intBitsToFloat(readInt());
    }

    default double readDouble() throws IOException {
        return Double.longBitsToDouble(readLong());
    }
}
