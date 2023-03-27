/*
 * Created on Mon Mar 27 2023
 *
 * Copyright (c) storycraft. Licensed under the Apache Licence 2.0.
 */
package sh.pancake.serdemc;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

import org.junit.jupiter.api.Test;

import sh.pancake.serdemc.data.nbt.io.NbtReader;
import sh.pancake.serdemc.io.InputStreamReader;

public class TestBigNbt {
    private InputStream openTestNbtStream() throws IOException {
        return new GZIPInputStream(TestBigNbt.class.getResourceAsStream("/bigtest.nbt"));
    }

    @Test
    void testRead() throws Throwable {
        try (DataInputStream inputStream = new DataInputStream(openTestNbtStream())) {
            new NbtReader(new InputStreamReader(inputStream)).readRootCompound();
        }
    }
}
