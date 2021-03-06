package net.minestom.server.instance.block.states;

import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.BlockAlternative;

/**
 * Completely internal. DO NOT USE. IF YOU ARE A USER AND FACE A PROBLEM WHILE USING THIS CODE, THAT'S ON YOU.
 */
@Deprecated(
        since = "forever",
        forRemoval = false
)
public final class LightBlueWallBanner {
    /**
     * Completely internal. DO NOT USE. IF YOU ARE A USER AND FACE A PROBLEM WHILE USING THIS CODE, THAT'S ON YOU.
     */
    @Deprecated(
            since = "forever",
            forRemoval = false
    )
    public static void initStates() {
        Block.LIGHT_BLUE_WALL_BANNER.addBlockAlternative(new BlockAlternative((short) 8415, "facing=north"));
        Block.LIGHT_BLUE_WALL_BANNER.addBlockAlternative(new BlockAlternative((short) 8416, "facing=south"));
        Block.LIGHT_BLUE_WALL_BANNER.addBlockAlternative(new BlockAlternative((short) 8417, "facing=west"));
        Block.LIGHT_BLUE_WALL_BANNER.addBlockAlternative(new BlockAlternative((short) 8418, "facing=east"));
    }
}
