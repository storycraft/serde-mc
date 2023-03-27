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

class TestMetadataPacket {
    private InputStream openTestPacketStream() throws IOException {
        return TestNbt.class.getResourceAsStream("/metadata_packet.bin");
    }

    @Test
    void testRead() throws Throwable {
        try(DataInputStream inputStream = new DataInputStream(openTestPacketStream())) {
            PacketDataReader reader = new PacketDataReader(new InputStreamReader(inputStream));

            assertEquals(reader.readVarInt(), 78);
            assertEquals(reader.readVarInt(), 27);
            assertEquals(reader.readEntityMetadata().size(), 2);
        }
    }
}
