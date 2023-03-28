/*
 * Created on Sun Mar 26 2023
 *
 * Copyright (c) storycraft. Licensed under the Apache Licence 2.0.
 */
package sh.pancake.serdemc;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.Test;

import sh.pancake.serdemc.data.nbt.NbtRootCompound;
import sh.pancake.serdemc.data.nbt.NbtTagCompound;
import sh.pancake.serdemc.data.nbt.NbtTagValue;
import sh.pancake.serdemc.data.nbt.io.NbtReader;
import sh.pancake.serdemc.data.nbt.io.NbtWriter;
import sh.pancake.serdemc.io.InputStreamReader;
import sh.pancake.serdemc.io.OutputStreamWriter;

class TestNbt {
    private InputStream openTestNbtStream() throws IOException {
        return TestNbt.class.getResourceAsStream("/hello_world.nbt");
    }

    @Test
    void testRead() throws Throwable {
        try(DataInputStream inputStream = new DataInputStream(openTestNbtStream())) {
            NbtRootCompound root = new NbtReader(new InputStreamReader(inputStream)).readRootCompound();

            assertEquals(root.getCompound().get("name"), NbtTagValue.fromString("Bananrama"));
        }
    }

    @Test
    void testWrite() throws Throwable {
        byte[] original;
        try (InputStream stream = openTestNbtStream()) {
            original = stream.readAllBytes();
        }

        NbtRootCompound root = new NbtRootCompound("hello world", new NbtTagCompound());
        root.getCompound().put("name", NbtTagValue.fromString("Bananrama"));

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            new NbtWriter(new OutputStreamWriter(new DataOutputStream(outputStream))).writeRootCompound(root);

            assertArrayEquals(original, outputStream.toByteArray());
        }
    }
}
