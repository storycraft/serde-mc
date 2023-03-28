/*
 * Created on Tue Mar 28 2023
 *
 * Copyright (c) storycraft. Licensed under the Apache Licence 2.0.
 */
package sh.pancake.serdemc.data.nbt;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class NbtTagCompound implements Map<String, NbtTagValue<?>> {
    private final HashMap<String, NbtTagValue<?>> map;

    public NbtTagCompound() {
        this.map = new HashMap<>();
    }

    public NbtTagCompound(int capacity) {
        this.map = new HashMap<>(capacity);
    }

    @Override
    public Set<Entry<String, NbtTagValue<?>>> entrySet() {
        return map.entrySet();
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean containsKey(Object arg0) {
        return map.containsKey(arg0);
    }

    @Override
    public boolean containsValue(Object arg0) {
        return map.containsValue(arg0);
    }

    @Override
    public NbtTagValue<?> get(Object arg0) {
        return map.get(arg0);
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public Set<String> keySet() {
        return map.keySet();
    }

    @Override
    public NbtTagValue<?> put(String arg0, NbtTagValue<?> arg1) {
        return map.put(arg0, arg1);
    }

    @Override
    public void putAll(Map<? extends String, ? extends NbtTagValue<?>> arg0) {
        map.putAll(arg0);
    }

    @Override
    public NbtTagValue<?> remove(Object arg0) {
        return map.remove(arg0);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public Collection<NbtTagValue<?>> values() {
        return map.values();
    }
}
