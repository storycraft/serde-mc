/*
 * Created on Mon Mar 27 2023
 *
 * Copyright (c) storycraft. Licensed under the Apache Licence 2.0.
 */
package sh.pancake.serdemc.data.particle.data;

import javax.annotation.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;
import sh.pancake.serdemc.data.ItemStack;

@AllArgsConstructor
@Data
public class ItemParticleData {
    @Nullable
    private ItemStack item;
}
