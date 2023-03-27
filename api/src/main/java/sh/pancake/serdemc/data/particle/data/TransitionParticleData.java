/*
 * Created on Mon Mar 27 2023
 *
 * Copyright (c) storycraft. Licensed under the Apache Licence 2.0.
 */
package sh.pancake.serdemc.data.particle.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class TransitionParticleData {
    private float fromRed;
    private float fromGreen;
    private float fromBlue;

    private float scale;

    private float toRed;
    private float toGreen;
    private float toBlue;
}
