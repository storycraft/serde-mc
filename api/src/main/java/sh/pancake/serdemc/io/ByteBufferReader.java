/*
 * Created on Sun Mar 26 2023
 *
 * Copyright (c) storycraft. Licensed under the Apache Licence 2.0.
 */
package sh.pancake.serdemc.io;

import java.nio.ByteBuffer;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ByteBufferReader implements DataReader {
    private final ByteBuffer byteBuffer;

    @Override
    public byte readByte() {
        return byteBuffer.get();
    }

    @Override
    public int readBytes(byte[] dst, int offset, int length) {
        int read = Math.min(length, byteBuffer.remaining());

        byteBuffer.get(dst, offset, read);
        return read;
    }

    @Override
    public short readShort() {
        return byteBuffer.getShort();
    }

    @Override
    public int readInt() {
        return byteBuffer.getInt();
    }

    @Override
    public long readLong() {
        return byteBuffer.getLong();
    }
}
