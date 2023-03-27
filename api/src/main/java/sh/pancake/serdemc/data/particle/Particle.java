/*
 * Created on Mon Mar 27 2023
 *
 * Copyright (c) storycraft. Licensed under the Apache Licence 2.0.
 */
package sh.pancake.serdemc.data.particle;

import javax.annotation.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Particle {
    private int id;
    private @Nullable Object data;
}
