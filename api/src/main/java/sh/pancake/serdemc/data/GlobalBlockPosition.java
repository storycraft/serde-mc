package sh.pancake.serdemc.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GlobalBlockPosition {
    private String dimension;
    private BlockPosition position;
}
