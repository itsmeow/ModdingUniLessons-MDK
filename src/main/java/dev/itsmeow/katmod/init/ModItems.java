package dev.itsmeow.katmod.init;

import dev.itsmeow.katmod.KatMod;
import dev.itsmeow.katmod.items.ExplodingStickItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(KatMod.MODID);

    // Items

    public static final DeferredItem<ExplodingStickItem> EXPLODING_STICK = ITEMS.registerItem(
            "exploding_stick", ExplodingStickItem::new, new Item.Properties());

    // Block Items

    public static final DeferredItem<BlockItem> CANNON = ITEMS.registerSimpleBlockItem(ModBlocks.CANNON);

}
