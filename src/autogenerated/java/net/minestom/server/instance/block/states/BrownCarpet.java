package net.minestom.server.instance.block.states;

import java.lang.Deprecated;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.BlockState;
import net.minestom.server.raw_data.RawBlockStateData;
import net.minestom.server.registry.Registry;
import net.minestom.server.utils.NamespaceID;

/**
 * AUTOGENERATED by BlockGenerator
 */
@Deprecated(
        since = "forever",
        forRemoval = false
)
public final class BrownCarpet {
    public static final BlockState BROWN_CARPET_0 = new BlockState(NamespaceID.from("minecraft:brown_carpet_0"), (short) 7882, Block.BROWN_CARPET, new RawBlockStateData(0.1, 0, true, "NORMAL", false, true, false, false, false, false, 3, "[AABB[0.0, 0.0, 0.0] -> [1.0, 0.0625, 1.0]]"));

    static {
        Registry.BLOCK_STATE_REGISTRY.register(BROWN_CARPET_0);
    }

    public static void initStates() {
        Block.BROWN_CARPET.addBlockState(BROWN_CARPET_0);
    }
}