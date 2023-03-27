/*
 * Created on Mon Mar 27 2023
 *
 * Copyright (c) storycraft. Licensed under the Apache Licence 2.0.
 */
package sh.pancake.serdemc.data.particle.codec;

import java.io.IOException;

import javax.annotation.Nullable;

import sh.pancake.serdemc.data.particle.data.BlockParticleData;
import sh.pancake.serdemc.data.particle.data.DustParticleData;
import sh.pancake.serdemc.data.particle.data.ItemParticleData;
import sh.pancake.serdemc.data.particle.data.SchulkChargeParticleData;
import sh.pancake.serdemc.data.particle.data.ShriekParticleData;
import sh.pancake.serdemc.data.particle.data.TransitionParticleData;
import sh.pancake.serdemc.data.particle.data.VibrationParticleData;
import sh.pancake.serdemc.network.io.PacketDataReader;
import sh.pancake.serdemc.network.io.PacketDataWriter;

public abstract class ParticleDataCodec<T> implements ParticleDataReader<T>, ParticleDataWriter<T> {
    public static final ParticleDataCodec<BlockParticleData> BLOCK = new ParticleDataCodec<>() {
        @Override
        public BlockParticleData read(PacketDataReader reader) throws IOException {
            return new BlockParticleData(reader.readVarInt());
        }

        @Override
        public void write(PacketDataWriter writer, BlockParticleData value) throws IOException {
            writer.writeVarInt(value.getBlockId());
        }
    };

    public static final ParticleDataCodec<DustParticleData> DUST = new ParticleDataCodec<>() {
        @Override
        public DustParticleData read(PacketDataReader reader) throws IOException {
            return new DustParticleData(
                reader.readFloat(),
                reader.readFloat(),
                reader.readFloat(),
                reader.readFloat()
            );
        }

        @Override
        public void write(PacketDataWriter writer, DustParticleData value) throws IOException {
            writer.writeFloat(value.getRed());
            writer.writeFloat(value.getGreen());
            writer.writeFloat(value.getBlue());
            writer.writeFloat(value.getScale());
        }
    };

    public static final ParticleDataCodec<TransitionParticleData> TRANSITION = new ParticleDataCodec<>() {
        @Override
        public TransitionParticleData read(PacketDataReader reader) throws IOException {
            return new TransitionParticleData(
                reader.readFloat(),
                reader.readFloat(),
                reader.readFloat(),
                reader.readFloat(),
                reader.readFloat(),
                reader.readFloat(),
                reader.readFloat()
            );
        }

        @Override
        public void write(PacketDataWriter writer, TransitionParticleData value) throws IOException {
            writer.writeFloat(value.getFromRed());
            writer.writeFloat(value.getFromGreen());
            writer.writeFloat(value.getFromBlue());
            writer.writeFloat(value.getScale());
            writer.writeFloat(value.getToRed());
            writer.writeFloat(value.getToGreen());
            writer.writeFloat(value.getToBlue());
        }
    };

    public static final ParticleDataCodec<SchulkChargeParticleData> SCHULK_CHARGE = new ParticleDataCodec<>() {
        @Override
        public SchulkChargeParticleData read(PacketDataReader reader) throws IOException {
            return new SchulkChargeParticleData(reader.readFloat());
        }

        @Override
        public void write(PacketDataWriter writer, SchulkChargeParticleData value) throws IOException {
            writer.writeFloat(value.getRoll());
        }
    };

    public static final ParticleDataCodec<ItemParticleData> ITEM = new ParticleDataCodec<>() {
        @Override
        public ItemParticleData read(PacketDataReader reader) throws IOException {
            return new ItemParticleData(reader.readSlot());
        }

        @Override
        public void write(PacketDataWriter writer, ItemParticleData value) throws IOException {
            writer.writeSlot(value.getItem());
        }
    };

    public static final ParticleDataCodec<VibrationParticleData> VIBRATION = new ParticleDataCodec<>() {
        @Override
        public VibrationParticleData read(PacketDataReader reader) throws IOException {
            return new VibrationParticleData(
                reader.readString(),
                reader.readPosition(),
                reader.readVarInt(),
                reader.readFloat(),
                reader.readVarInt()
            );
        }

        @Override
        public void write(PacketDataWriter writer, VibrationParticleData value) throws IOException {
            writer.writeString(value.getType());
            writer.writePosition(value.getPosition());
            writer.writeVarInt(value.getEntityId());
            writer.writeFloat(value.getEntityEyeHeight());
            writer.writeVarInt(value.getTicks());
        }
    };

    public static final ParticleDataCodec<ShriekParticleData> SHRIEK = new ParticleDataCodec<>() {
        @Override
        public ShriekParticleData read(PacketDataReader reader) throws IOException {
            return new ShriekParticleData(reader.readVarInt());
        }

        @Override
        public void write(PacketDataWriter writer, ShriekParticleData value) throws IOException {
            writer.writeVarInt(value.getDelay());
        }
    };

    public static @Nullable ParticleDataCodec<?> getCodec(int id) {
        switch (id) {
            case 2:
            case 3:
            case 25: return BLOCK;

            case 14: return DUST;

            case 15: return TRANSITION;

            case 33: return SCHULK_CHARGE;

            case 42: return ITEM;

            case 43: return VIBRATION;

            case 95: return SHRIEK;

            default: return null;
        }
    }
}
