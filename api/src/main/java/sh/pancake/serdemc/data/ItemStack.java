/*
 * Created on Sun Mar 26 2023
 *
 * Copyright (c) storycraft. Licensed under the Apache Licence 2.0.
 */
package sh.pancake.serdemc.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import sh.pancake.serdemc.data.nbt.NbtRootCompound;

@Data
@AllArgsConstructor
public class ItemStack {
    private int id;

    private byte count;

    private NbtRootCompound nbt;
}
