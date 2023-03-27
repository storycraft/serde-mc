/*
 * Created on Mon Mar 27 2023
 *
 * Copyright (c) storycraft. Licensed under the Apache Licence 2.0.
 */
package sh.pancake.serdemc.data.particle.codec;

import java.io.IOException;

import sh.pancake.serdemc.network.io.PacketDataReader;

@FunctionalInterface
public interface ParticleDataReader<T> {
    T read(PacketDataReader reader) throws IOException;
}
