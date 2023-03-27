/*
 * Created on Mon Mar 27 2023
 *
 * Copyright (c) storycraft. Licensed under the Apache Licence 2.0.
 */
package sh.pancake.serdemc.data.metadata.codec;

import java.io.IOException;
import java.util.UUID;

import javax.annotation.Nullable;

import sh.pancake.serdemc.data.BlockPosition;
import sh.pancake.serdemc.data.ItemStack;
import sh.pancake.serdemc.data.Quaternion;
import sh.pancake.serdemc.data.Vector3;
import sh.pancake.serdemc.data.nbt.GlobalBlockPosition;
import sh.pancake.serdemc.data.nbt.NbtRootCompound;
import sh.pancake.serdemc.data.particle.Particle;
import sh.pancake.serdemc.data.particle.codec.ParticleDataCodec;
import sh.pancake.serdemc.network.io.PacketDataReader;
import sh.pancake.serdemc.network.io.PacketDataWriter;

@SuppressWarnings("null")
public abstract class MetadataCodec<T> implements MetadataValueReader<T>, MetadataValueWriter<T> {
    public static final MetadataCodec<Byte> BYTE = create(PacketDataReader::readByte, PacketDataWriter::writeByte);
    public static final MetadataCodec<Integer> VAR_INT = create(PacketDataReader::readVarInt, PacketDataWriter::writeVarInt);
    public static final MetadataCodec<Long> VAR_LONG = create(PacketDataReader::readVarLong, PacketDataWriter::writeVarLong);
    public static final MetadataCodec<Float> FLOAT = create(PacketDataReader::readFloat, PacketDataWriter::writeFloat);
    public static final MetadataCodec<String> STRING = create(PacketDataReader::readString, PacketDataWriter::writeString);
    public static final MetadataCodec<String> CHAT = create(PacketDataReader::readChat, PacketDataWriter::writeChat);
    public static final MetadataCodec<ItemStack> SLOT = create(PacketDataReader::readSlot, PacketDataWriter::writeSlot);
    public static final MetadataCodec<Boolean> BOOLEAN = create(PacketDataReader::readBoolean, PacketDataWriter::writeBoolean);
    public static final MetadataCodec<BlockPosition> POSITION = create(PacketDataReader::readPosition, PacketDataWriter::writePosition);
    public static final MetadataCodec<UUID> UUID = create(PacketDataReader::readUUID, PacketDataWriter::writeUUID);
    public static final MetadataCodec<NbtRootCompound> NBT = create(PacketDataReader::readNbt, PacketDataWriter::writeNbt);
    public static final MetadataCodec<Particle> PARTICLE = new MetadataCodec<>() {
        @Override
        public Particle read(PacketDataReader reader) throws IOException {
            int type = reader.readVarInt();
            Object data = null;

            ParticleDataCodec<?> codec = ParticleDataCodec.getCodec(type);
            if (codec != null) {
                data = codec.read(reader);
            }

            return new Particle(type, data);
        }

        @SuppressWarnings("unchecked")
        public void write(PacketDataWriter writer, Particle value) throws IOException {
            int type = value.getId();

            writer.writeVarInt(type);

            ParticleDataCodec<Object> codec = (ParticleDataCodec<Object>) ParticleDataCodec.getCodec(type);
            if (codec == null) {
                return;
            }

            codec.write(writer, value);
        }
    };
    public static final MetadataCodec<GlobalBlockPosition> GLOBAL_POSITION = new MetadataCodec<>() {
        @Override
        public GlobalBlockPosition read(PacketDataReader reader) throws IOException {
            return new GlobalBlockPosition(reader.readIdentifier(), reader.readPosition());
        }

        public void write(PacketDataWriter writer, GlobalBlockPosition value) throws IOException {
            writer.writeIdentifier(value.getDimension());
            writer.writePosition(value.getPosition());
        }
    };
    public static final MetadataCodec<Vector3> VECTOR = new MetadataCodec<>() {
        @Override
        public Vector3 read(PacketDataReader reader) throws IOException {
            return new Vector3(
                reader.readFloat(),
                reader.readFloat(),
                reader.readFloat()
            );
        }

        public void write(PacketDataWriter writer, Vector3 value) throws IOException {
            writer.writeFloat(value.getX());
            writer.writeFloat(value.getY());
            writer.writeFloat(value.getZ());
        }
    };
    public static final MetadataCodec<Quaternion> QUATERNION = new MetadataCodec<>() {
        @Override
        public Quaternion read(PacketDataReader reader) throws IOException {
            return new Quaternion(
                reader.readFloat(),
                reader.readFloat(),
                reader.readFloat(),
                reader.readFloat()
            );
        }

        public void write(PacketDataWriter writer, Quaternion value) throws IOException {
            writer.writeFloat(value.getX());
            writer.writeFloat(value.getY());
            writer.writeFloat(value.getZ());
            writer.writeFloat(value.getW());
        }
    };

    private static final MetadataCodec<?>[] MAP = {
        BYTE, // 0 byte
        VAR_INT, // 1 varint
        VAR_LONG, // 2 varlong
        FLOAT, // 3 float
        STRING, // 4 string
        CHAT, // 5 chat
        optional(CHAT), // 6 opt chat
        SLOT, // 7 slot
        BOOLEAN, // 8 boolean
        VECTOR, // 9 rotation
        POSITION, // 10 position
        optional(POSITION), // 11 opt position
        VAR_INT, // 12 direction
        optional(UUID), // 13 opt uuid
        VAR_INT, // 14 block id
        VAR_INT, // 15 opt block id
        NBT, // 16 nbt
        PARTICLE, // 17 particle
        VAR_INT, // 18 villager data
        create(
            (reader) -> {
                int value = reader.readVarInt();
                if (value == 0) return null;
                return value - 1;
            },
            (writer, value) -> {
                if (value == null) {
                    writer.writeVarInt(0);
                    return;
                }

                writer.writeVarInt(value + 1);
            }
        ), // 19 opt varint
        VAR_INT, // 20 pose
        VAR_INT, // 21 cat variant
        VAR_INT, // 22 frog variant
        optional(GLOBAL_POSITION), // 23 opt global pos
        VAR_INT, // 24 painting variant
        VAR_INT, // 25 sniffer state
        VECTOR, // 26 vector
        QUATERNION, // 27 quaternion
    };

    public static @Nullable MetadataCodec<?> getCodec(int type) {
        if (type < 0 || type >= MAP.length) {
            return null;
        }

        return MAP[type];
    }

    public static <Type> MetadataCodec<Type> create(
        final MetadataValueReader<Type> read,
        final MetadataValueWriter<Type> write
    ) {
        return new MetadataCodec<>() {
            @Override
            public Type read(PacketDataReader reader) throws IOException {
                return read.read(reader);
            }

            @Override
            public void write(PacketDataWriter writer, Type value) throws IOException {
                write.write(writer, value);
            }
        };
    }

    public static <Type> MetadataCodec<Type> optional(final MetadataCodec<Type> codec) {
        return new MetadataCodec<>() {
            @Override
            public @Nullable Type read(PacketDataReader reader) throws IOException {
                if (!reader.readBoolean()) {
                    return null;
                }

                return codec.read(reader);
            }

            @Override
            public void write(PacketDataWriter writer, @Nullable Type value) throws IOException {
                if (value == null) {
                    writer.writeBoolean(false);
                    return;
                }

                writer.writeBoolean(true);
                codec.write(writer, value);
            }
        };
    }
}
