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
public final class Sponge {
    public static final BlockState SPONGE_0 = new BlockState(NamespaceID.from("minecraft:sponge_0"), (short) 229, Block.SPONGE, new RawBlockStateData(0.6, 0, true, "NORMAL", true, false, false, false, true, true, 18, "[AABB[0.0, 0.0, 0.0] -> [1.0, 1.0, 1.0]]"));

    static {
        Registry.BLOCK_STATE_REGISTRY.register(SPONGE_0);
    }

    public static void initStates() {
        Block.SPONGE.addBlockState(SPONGE_0);
    }
}