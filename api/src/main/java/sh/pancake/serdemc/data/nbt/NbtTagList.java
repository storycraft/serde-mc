/*
 * Created on Mon Mar 27 2023
 *
 * Copyright (c) storycraft. Licensed under the Apache Licence 2.0.
 */
package sh.pancake.serdemc.data.nbt;

import java.util.AbstractList;
import java.util.ArrayList;

import lombok.Getter;

public class NbtTagList<T> extends AbstractList<NbtTagValue<T>> {
    @Getter
    private final byte type;

    private final ArrayList<NbtTagValue<T>> list;

    public NbtTagList(byte type) {
        this.type = type;
        this.list = new ArrayList<>();
    }

    public NbtTagList(byte type, int length) {
        this.type = type;
        this.list = new ArrayList<>(length);
    }

    @Override
    public void add(int arg0, NbtTagValue<T> arg1) {
        list.add(arg0, arg1);
    }

    @Override
    public NbtTagValue<T> set(int arg0, NbtTagValue<T> arg1) {
        return list.set(arg0, arg1);
    }

    @Override
    public NbtTagValue<T> remove(int arg0) {
        return list.remove(arg0);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public NbtTagValue<T> get(int arg0) {
        return list.get(arg0);
    }
}
