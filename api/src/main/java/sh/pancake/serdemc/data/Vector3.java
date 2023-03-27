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
public class Vector3 {
    private final float x;
    private final float y;
    private final float z;

    public Quaternion toQuaternion() {
        return toQuaternion(0f);
    }
    public Quaternion toQuaternion(float w) {
        return new Quaternion(x, y, z, w);
    }

    public static Vector3 fromQuaternion(Quaternion qu) {
        return new Vector3(qu.getX(), qu.getY(), qu.getZ());
    }
}
