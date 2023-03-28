/*
 * Created on Mon Mar 27 2023
 *
 * Copyright (c) storycraft. Licensed under the Apache Licence 2.0.
 */
package sh.pancake.serdemc.data.nbt;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NbtRootCompound {
    private final String name;
    private final NbtTagCompound compound;
}
