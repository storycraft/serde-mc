/*
 * Created on Mon Mar 27 2023
 *
 * Copyright (c) storycraft. Licensed under the Apache Licence 2.0.
 */
package sh.pancake.serdemc.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Quaternion {
    private final float x;
    private final float y;
    private final float z;
    private final float w;

    public Vector3 toVector3() {
        return new Vector3(x, y, z);
    }

    public static Quaternion fromVector3(Vector3 vec) {
        return fromVector3(vec, 0f);
    }
    public static Quaternion fromVector3(Vector3 vec, float w) {
        return new Quaternion(vec.getX(), vec.getY(), vec.getZ(), w);
    }
}
