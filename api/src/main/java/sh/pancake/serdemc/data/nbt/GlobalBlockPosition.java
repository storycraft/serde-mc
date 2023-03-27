package sh.pancake.serdemc.data.nbt;

import lombok.AllArgsConstructor;
import lombok.Data;
import sh.pancake.serdemc.data.BlockPosition;

@Data
@AllArgsConstructor
public class GlobalBlockPosition {
    private String dimension;
    private BlockPosition position;
}
