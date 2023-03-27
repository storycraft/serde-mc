/*
 * Created on Mon Mar 27 2023
 *
 * Copyright (c) storycraft. Licensed under the Apache Licence 2.0.
 */
package sh.pancake.serdemc.io;

import java.io.DataInputStream;
import java.io.IOException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InputStreamReader implements DataReader {
    private final DataInputStream stream;

    @Override
    public byte readByte() throws IOException {
        return stream.readByte();
    }

    @Override
    public int readBytes(byte[] dst, int offset, int length) throws IOException {
        return stream.read(dst, offset, length);
    }

    @Override
    public short readShort() throws IOException {
        return stream.readShort();
    }

    @Override
    public int readInt() throws IOException {
        return stream.readInt();
    }

    @Override
    public long readLong() throws IOException {
        return stream.readLong();
    }
}
