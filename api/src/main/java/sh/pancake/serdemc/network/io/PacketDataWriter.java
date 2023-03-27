/*
 * Created on Sun Mar 26 2023
 *
 * Copyright (c) storycraft. Licensed under the Apache Licence 2.0.
 */
package sh.pancake.serdemc.network.io;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Nullable;

import lombok.AllArgsConstructor;
import sh.pancake.serdemc.data.BlockPosition;
import sh.pancake.serdemc.data.ItemStack;
import sh.pancake.serdemc.data.metadata.MetadataValue;
import sh.pancake.serdemc.data.metadata.codec.MetadataCodec;
import sh.pancake.serdemc.data.nbt.NbtRootCompound;
import sh.pancake.serdemc.data.nbt.io.NbtWriter;
import sh.pancake.serdemc.io.DataWriter;

@AllArgsConstructor
public class PacketDataWriter {
    private final DataWriter writer;

    public void writeByte(byte value) throws IOException {
        writer.writeByte(value);
    }
    public void writeByteUnsigned(int value) throws IOException {
        writer.writeByteUnsigned(value);
    }

    public void writeBoolean(boolean value) throws IOException {
        writer.writeByte((byte) (value ? 1 : 0));
    }

    public void writeBytes(byte[] dst) throws IOException {
        writer.writeBytes(dst);
    }

    public void writeShort(short value) throws IOException {
        writer.writeShort(value);
    }
    public void writeShortUnsigned(int value) throws IOException {
        writer.writeShortUnsigned(value);
    }

    public void writeInt(int value) throws IOException {
        writer.writeInt(value);
    }

    public void writeVarInt(int value) throws IOException {
        while (true) {
            if ((value & ~0x7F) == 0) {
                writeByte((byte) value);
                return;
            }

            writeByte((byte) ((value & 0x7F) | 0x80));

            value >>>= 7;
        }
    }

    public void writeLong(long value) throws IOException {
        writer.writeLong(value);
    }

    public void writeVarLong(long value) throws IOException {
        while (true) {
            if ((value & ~0x7FL) == 0) {
                writeByte((byte) value);
                return;
            }

            writeByte((byte) ((value & 0x7F) | 0x80));

            value >>>= 7;
        }
    }

    public void writeFloat(float value) throws IOException {
        writer.writeFloat(value);
    }

    public void writeDouble(double value) throws IOException {
        writer.writeDouble(value);
    }

    public void writeString(String value) throws IOException {
        writeString(value, 32767);
    }
    public void writeString(String value, int maxLength) throws IOException {
        int length = value.length();
        if (length > maxLength) {
            throw new RuntimeException("String exceed max length. maxLength: " + maxLength + " length: " + length);
        }

        writeVarInt(length);
        writeBytes(value.getBytes());
    }

    public void writeChat(String value) throws IOException {
        writeString(value, 262144);
    }

    public void writeIdentifier(String value) throws IOException {
        writeString(value, 32767);
    }

    public void writeUUID(UUID uuid) throws IOException {
        writer.writeLong(uuid.getMostSignificantBits());
        writer.writeLong(uuid.getLeastSignificantBits());
    }

    public void writeSlot(@Nullable ItemStack item) throws IOException {
        if (item == null) {
            writeBoolean(false);
            return;
        }

        writeBoolean(true);
        writeVarInt(item.getId());
        writeByte(item.getCount());
        writeNbt(item.getNbt());
    }

    public void writePosition(BlockPosition position) throws IOException {
        writer.writeLong(position.getX() << 38L | (position.getZ() & 0x3FFFFFFL) << 12L | position.getY() & 0xFFFL);
    }

    public void writeNbt(NbtRootCompound compound) throws IOException {
        new NbtWriter(writer).writeRootCompound(compound);
    }

    @SuppressWarnings("unchecked")
    public void writeEntityMetadata(Map<Byte, MetadataValue> metadata) throws IOException {
        for (Byte index : metadata.keySet()) {
            if (index.byteValue() == (byte) 0xFF) {
                throw new RuntimeException("Index cannot be 255");
            }

            MetadataValue value = metadata.get(index);
            int type = value.getType();

            MetadataCodec<Object> codec = (MetadataCodec<Object>) MetadataCodec.getCodec(type);
            if (codec == null) {
                throw new RuntimeException("Unknown metadata type: " + type);
            }

            writeVarInt(type);
            codec.write(this, value.getValue());
        }

        writeByte((byte) 0xFF);
    }
}
