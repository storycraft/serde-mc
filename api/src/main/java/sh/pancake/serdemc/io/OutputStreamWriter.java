/*
 * Created on Mon Mar 27 2023
 *
 * Copyright (c) storycraft. Licensed under the Apache Licence 2.0.
 */
package sh.pancake.serdemc.io;

import java.io.DataOutputStream;
import java.io.IOException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OutputStreamWriter implements DataWriter {
    private final DataOutputStream stream;

    @Override
    public void writeByte(byte value) throws IOException {
        stream.writeByte(value);
    }

    @Override
    public void writeBytes(byte[] dst, int offset, int length) throws IOException {
        stream.write(dst, offset, length);
    }

    @Override
    public void writeShort(short value) throws IOException {
        stream.writeShort(value);
    }

    @Override
    public void writeInt(int value) throws IOException {
        stream.writeInt(value);
    }

    @Override
    public void writeLong(long value) throws IOException {
        stream.writeLong(value);
    }
}
