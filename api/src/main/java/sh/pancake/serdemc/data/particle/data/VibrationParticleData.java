/*
 * Created on Mon Mar 27 2023
 *
 * Copyright (c) storycraft. Licensed under the Apache Licence 2.0.
 */
package sh.pancake.serdemc.data.particle.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import sh.pancake.serdemc.data.BlockPosition;

@AllArgsConstructor
@Data
public class VibrationParticleData {
    private String type;
    
    private BlockPosition position;
    
    private int entityId;
    private float entityEyeHeight;

    private int ticks;
}
