/*
 * Created on Sun Mar 26 2023
 *
 * Copyright (c) storycraft. Licensed under the Apache Licence 2.0.
 */
package sh.pancake.serdemc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.Test;

import sh.pancake.serdemc.io.InputStreamReader;
import sh.pancake.serdemc.network.io.PacketDataReader;

class TestLoginPacket {
    private InputStream openTestPacketStream() throws IOException {
        return TestNbt.class.getResourceAsStream("/login_packet.bin");
    }

    @Test
    void testRead() throws Throwable {
        try(DataInputStream inputStream = new DataInputStream(openTestPacketStream())) {
            PacketDataReader reader = new PacketDataReader(new InputStreamReader(inputStream));

            assertEquals(reader.readVarInt(), 36);
            assertEquals(reader.readInt(), 74);
            assertEquals(reader.readBoolean(), false);
            assertEquals(reader.readByteUnsigned(), 0);
            assertEquals(reader.readByte(), -1);
            assertEquals(reader.readVarInt(), 3);
            assertEquals(reader.readIdentifier(), "minecraft:overworld");
            assertEquals(reader.readIdentifier(), "minecraft:the_nether");
            assertEquals(reader.readIdentifier(), "minecraft:the_end");
            assertEquals(reader.readNbt().getCompound().size(), 3);
        }
    }
}
