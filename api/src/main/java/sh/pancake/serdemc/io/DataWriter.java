/*
 * Created on Sun Mar 26 2023
 *
 * Copyright (c) storycraft. Licensed under the Apache Licence 2.0.
 */
package sh.pancake.serdemc.io;

import java.io.IOException;

public interface DataWriter {
    void writeByte(byte value) throws IOException;
    default void writeByteUnsigned(int value) throws IOException {
        writeByte((byte) value);
    }

    void writeBytes(byte[] dst, int offset, int length) throws IOException;
    public default void writeBytes(byte[] dst, int offset) throws IOException {
        writeBytes(dst, offset, dst.length - offset);
    }
    public default void writeBytes(byte[] dst) throws IOException {
        writeBytes(dst, 0, dst.length);
    }

    void writeShort(short value) throws IOException;
    default void writeShortUnsigned(int value) throws IOException {
        writeShort((short) value);
    }

    void writeInt(int value) throws IOException;
    void writeLong(long value) throws IOException;

    default void writeFloat(float value) throws IOException {
        writeInt(Float.floatToRawIntBits(value));
    }

    default void writeDouble(double value) throws IOException {
        writeLong(Double.doubleToRawLongBits(value));
    }
}
