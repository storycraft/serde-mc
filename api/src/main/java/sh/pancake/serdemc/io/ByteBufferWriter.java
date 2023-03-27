/*
 * Created on Sun Mar 26 2023
 *
 * Copyright (c) storycraft. Licensed under the Apache Licence 2.0.
 */
package sh.pancake.serdemc.io;

import java.nio.ByteBuffer;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ByteBufferWriter implements DataWriter {
    private final ByteBuffer byteBuffer;

    @Override
    public void writeByte(byte value) {
        byteBuffer.put(value);
    }

    @Override
    public void writeBytes(byte[] dst, int offset, int length) {
        byteBuffer.put(dst, offset, length);
    }

    @Override
    public void writeShort(short value) {
        byteBuffer.putShort(value);
    }

    @Override
    public void writeInt(int value) {
        byteBuffer.putInt(value);
    }

    @Override
    public void writeLong(long value) {
        byteBuffer.putLong(value);
    }
}
