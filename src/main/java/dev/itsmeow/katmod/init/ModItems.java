package dev.itsmeow.katmod.init;

import dev.itsmeow.katmod.KatMod;
import dev.itsmeow.katmod.items.ExplodingStickItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(KatMod.MODID);

    // Items

    public static final DeferredItem<ExplodingStickItem> EXPLODING_STICK = ITEMS.registerItem(
            "exploding_stick", ExplodingStickItem::new, new Item.Properties());

    // Block Items

    public static final DeferredItem<BlockItem> CANNON = ITEMS.registerSimpleBlockItem(ModBlocks.CANNON);

    // Spawn Eggs

    public static final DeferredItem<DeferredSpawnEggItem> LIZARD_SPAWN_EGG = ITEMS.register("lizard_spawn_egg", () -> new DeferredSpawnEggItem(
            ModEntities.LIZARD::get, 0x117029, 0x0c2105, new Item.Properties()
    ));
}
