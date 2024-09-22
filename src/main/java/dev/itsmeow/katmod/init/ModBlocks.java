package dev.itsmeow.katmod.init;

import dev.itsmeow.katmod.KatMod;
import dev.itsmeow.katmod.blocks.CannonBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(KatMod.MODID);

    public static final DeferredBlock<CannonBlock> CANNON = BLOCKS.registerBlock(
            "cannon", CannonBlock::new, BlockBehaviour.Properties.of().noOcclusion().explosionResistance(1200F));
}
