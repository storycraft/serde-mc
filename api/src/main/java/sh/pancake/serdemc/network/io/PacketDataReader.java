/*
 * Created on Sun Mar 26 2023
 *
 * Copyright (c) storycraft. Licensed under the Apache Licence 2.0.
 */
package sh.pancake.serdemc.network.io;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Nullable;

import sh.pancake.serdemc.data.BlockPosition;
import sh.pancake.serdemc.data.ItemStack;
import sh.pancake.serdemc.data.metadata.MetadataValue;
import sh.pancake.serdemc.data.metadata.codec.MetadataCodec;
import sh.pancake.serdemc.data.nbt.NbtRootCompound;
import sh.pancake.serdemc.data.nbt.io.NbtReader;
import sh.pancake.serdemc.io.DataReader;

public class PacketDataReader {
    private final DataReader reader;
    private final NbtReader nbtReader;

    public PacketDataReader(DataReader reader) {
        this.reader = reader;
        this.nbtReader = new NbtReader(reader);
    }

    public byte readByte() throws IOException {
        return reader.readByte();
    }
    public int readByteUnsigned() throws IOException {
        return reader.readByteUnsigned();
    }

    public boolean readBoolean() throws IOException {
        if (reader.readByte() == 0x00) {
            return false;
        }
        
        return true;
    }

    public int readBytes(byte[] dst) throws IOException {
        return reader.readBytes(dst);
    }

    public short readShort() throws IOException {
        return reader.readShort();
    }
    public int readShortUnsigned() throws IOException {
        return reader.readShortUnsigned();
    }

    public int readInt() throws IOException {
        return reader.readInt();
    }

    public int readVarInt() throws IOException {
        int value = 0;

        for (int position = 0;; position += 7) {
            if (position >= 32) throw new RuntimeException("VarInt is too big");

            byte current = readByte();

            value |= (current & 0x7F) << position;

            if ((current & 0x80) == 0) {
                break;
            }
        }

        return value;
    }

    public long readLong() throws IOException {
        return reader.readLong();
    }

    public long readVarLong() throws IOException {
        long value = 0;
        for (int position = 0;; position += 7) {
            if (position >= 64) throw new RuntimeException("VarLong is too big");

            byte current = readByte();

            value |= (long) (current & 0x7F) << position;

            if ((current & 0x80) == 0) {
                break;
            }
        }

        return value;
    }

    public float readFloat() throws IOException {
        return reader.readFloat();
    }

    public double readDouble() throws IOException {
        return reader.readDouble();
    }

    public String readString() throws IOException {
        return readString(32767);
    }

    public String readString(int maxLength) throws IOException {
        int length = readVarInt();
        if (length > maxLength) {
            throw new RuntimeException("String exceed max length. maxLength: " + maxLength + " length: " + length);
        }

        byte[] buf = new byte[length];
        reader.readBytes(buf);

        return new String(buf, StandardCharsets.UTF_8);
    }

    public String readChat() throws IOException {
        return readString(262144);
    }

    public String readIdentifier() throws IOException {
        return readString(32767);
    }

    public UUID readUUID() throws IOException {
        return new UUID(reader.readLong(), reader.readLong());
    }

    public @Nullable ItemStack readSlot() throws IOException {
        if (!readBoolean()) {
            return null;
        }

        return new ItemStack(readVarInt(), readByte(), readNbt());
    }

    public BlockPosition readPosition() throws IOException {
        long pos = reader.readLong();

        return new BlockPosition(
            (int) (pos >>> 38L),
            (int) ((pos >>> 12L) & 67108863L),
            (int) (pos & 4095L)
        );
    }

    public NbtRootCompound readNbt() throws IOException {
        return nbtReader.readRootCompound();
    }
    public @Nullable NbtRootCompound readNbtOptional() throws IOException {
        return nbtReader.readRootCompoundOptional();
    }

    public Map<Byte, MetadataValue> readEntityMetadata() throws IOException {
        Map<Byte, MetadataValue> map = new HashMap<>();

        for (int index; (index = readByteUnsigned()) != 0xFF;) {
            int type = readVarInt();

            MetadataCodec<?> codec = MetadataCodec.getCodec(type);
            if (codec == null) {
                throw new RuntimeException("Unknown metadata type: " + type);
            }

            map.put((byte) index, new MetadataValue(type, codec.read(this)));
        }

        return map;
    }
}
