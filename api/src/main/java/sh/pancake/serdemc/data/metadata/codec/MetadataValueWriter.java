/*
 * Created on Mon Mar 27 2023
 *
 * Copyright (c) storycraft. Licensed under the Apache Licence 2.0.
 */
package sh.pancake.serdemc.data.metadata.codec;

import java.io.IOException;

import sh.pancake.serdemc.network.io.PacketDataWriter;

@FunctionalInterface
public interface MetadataValueWriter<T> {
    void write(PacketDataWriter writer, T value) throws IOException;
}
