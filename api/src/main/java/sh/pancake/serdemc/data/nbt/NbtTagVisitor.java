package sh.pancake.serdemc.data.nbt;

public interface NbtTagVisitor {
    void visitByte(byte value);

    void visitShort(short value);

    void visitInt(int value);

    void visitLong(long value);

    void visitFloat(float value);

    void visitDouble(double value);

    void visitByteArray(byte[] value);

    void visitString(String value);

    void visitList(NbtTagList<?> value);

    void visitCompound(NbtTagCompound value);

    void visitIntArray(int[] value);

    void visitLongArray(long[] value);
}
