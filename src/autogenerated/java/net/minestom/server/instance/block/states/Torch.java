package net.minestom.server.instance.block.states;

import java.lang.Deprecated;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.block.BlockState;
import net.minestom.server.registry.Registries;
import net.minestom.server.utils.NamespaceID;

/**
 * AUTOGENERATED
 */
@Deprecated(
    since = "forever",
    forRemoval = false
)
public final class Torch {
  public static final BlockState TORCH_0 = new BlockState(NamespaceID.from("minecraft:torch:0"), (short) 1435, Block.TORCH);

  static {
    Registries.registerBlockState(TORCH_0);
  }

  public static void initStates() {
    Block.TORCH.addBlockState(TORCH_0);
  }
}