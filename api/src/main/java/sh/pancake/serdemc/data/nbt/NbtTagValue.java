/*
 * Created on Sun Mar 26 2023
 *
 * Copyright (c) storycraft. Licensed under the Apache Licence 2.0.
 */
package sh.pancake.serdemc.data.nbt;

import java.util.Map;
import java.util.Objects;

public abstract class NbtTagValue<T> {
    public static final byte TAG_END = 0;
    public static final byte TAG_BYTE = 1;
    public static final byte TAG_SHORT = 2;
    public static final byte TAG_INT = 3;
    public static final byte TAG_LONG = 4;
    public static final byte TAG_FLOAT = 5;
    public static final byte TAG_DOUBLE = 6;
    public static final byte TAG_BYTE_ARRAY = 7;
    public static final byte TAG_STRING = 8;
    public static final byte TAG_LIST = 9;
    public static final byte TAG_COMPOUND = 10;
    public static final byte TAG_INT_ARRAY = 11;
    public static final byte TAG_LONG_ARRAY = 12;

    public static NbtTagValue<Byte> fromByte(byte value) {
        return new NbtTagValue<>() {
            @Override
            public byte getType() {
                return TAG_BYTE;
            }

            @Override
            public Byte getValue() {
                return value;
            }

            @Override
            public void visit(NbtTagVisitor visitor) {
                visitor.visitByte(value);
            }
        };
    }

    public static NbtTagValue<Short> fromShort(short value) {
        return new NbtTagValue<>() {
            @Override
            public byte getType() {
                return TAG_SHORT;
            }

            @Override
            public Short getValue() {
                return value;
            }

            @Override
            public void visit(NbtTagVisitor visitor) {
                visitor.visitShort(value);
            }
        };
    }

    public static NbtTagValue<Integer> fromInt(int value) {
        return new NbtTagValue<>() {
            @Override
            public byte getType() {
                return TAG_INT;
            }

            @Override
            public Integer getValue() {
                return value;
            }

            @Override
            public void visit(NbtTagVisitor visitor) {
                visitor.visitInt(value);
            }
        };
    }

    public static NbtTagValue<Long> fromLong(long value) {
        return new NbtTagValue<>() {
            @Override
            public byte getType() {
                return TAG_LONG;
            }

            @Override
            public Long getValue() {
                return value;
            }

            @Override
            public void visit(NbtTagVisitor visitor) {
                visitor.visitLong(value);
            }
        };
    }

    public static NbtTagValue<Float> fromFloat(float value) {
        return new NbtTagValue<>() {
            @Override
            public byte getType() {
                return TAG_FLOAT;
            }

            @Override
            public Float getValue() {
                return value;
            }

            @Override
            public void visit(NbtTagVisitor visitor) {
                visitor.visitFloat(value);
            }
        };
    }

    public static NbtTagValue<Double> fromDouble(double value) {
        return new NbtTagValue<>() {
            @Override
            public byte getType() {
                return TAG_DOUBLE;
            }

            @Override
            public Double getValue() {
                return value;
            }

            @Override
            public void visit(NbtTagVisitor visitor) {
                visitor.visitDouble(value);
            }
        };
    }

    public static NbtTagValue<byte[]> fromByteArray(byte[] value) {
        return new NbtTagValue<>() {
            @Override
            public byte getType() {
                return TAG_BYTE_ARRAY;
            }

            @Override
            public byte[] getValue() {
                return value;
            }

            @Override
            public void visit(NbtTagVisitor visitor) {
                visitor.visitByteArray(value);
            }
        };
    }

    public static NbtTagValue<String> fromString(String value) {
        return new NbtTagValue<>() {
            @Override
            public byte getType() {
                return TAG_STRING;
            }

            @Override
            public String getValue() {
                return value;
            }

            @Override
            public void visit(NbtTagVisitor visitor) {
                visitor.visitString(value);
            }
        };
    }

    public static <Item> NbtTagValue<NbtTagList<Item>> fromList(NbtTagList<Item> value) {
        return new NbtTagValue<>() {
            @Override
            public byte getType() {
                return TAG_LIST;
            }

            @Override
            public NbtTagList<Item> getValue() {
                return value;
            }

            @Override
            public void visit(NbtTagVisitor visitor) {
                visitor.visitList(value);
            }
        };
    }

    public static NbtTagValue<Map<String, NbtTagValue<?>>> fromCompound(Map<String, NbtTagValue<?>> value) {
        return new NbtTagValue<>() {
            @Override
            public byte getType() {
                return TAG_COMPOUND;
            }

            @Override
            public Map<String, NbtTagValue<?>> getValue() {
                return value;
            }

            @Override
            public void visit(NbtTagVisitor visitor) {
                visitor.visitCompound(value);
            }
        };
    }

    public static NbtTagValue<int[]> fromIntArray(int[] value) {
        return new NbtTagValue<>() {
            @Override
            public byte getType() {
                return TAG_INT_ARRAY;
            }

            @Override
            public int[] getValue() {
                return value;
            }

            @Override
            public void visit(NbtTagVisitor visitor) {
                visitor.visitIntArray(value);
            }
        };
    }

    public static NbtTagValue<long[]> fromLongArray(long[] value) {
        return new NbtTagValue<>() {
            @Override
            public byte getType() {
                return TAG_LONG_ARRAY;
            }

            @Override
            public long[] getValue() {
                return value;
            }

            @Override
            public void visit(NbtTagVisitor visitor) {
                visitor.visitLongArray(value);
            }
        };
    }

    private NbtTagValue() {}
    
    public abstract byte getType();
    
    public abstract T getValue();

    public abstract void visit(NbtTagVisitor visitor);

    @Override
    public boolean equals(Object arg0) {
        if (!(arg0 instanceof NbtTagValue)) {
            return false;
        }

        NbtTagValue<?> other = (NbtTagValue<?>) arg0;
        return getType() == other.getType() && getValue().equals(other.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue(), getType());
    }
}
