package dev.itsmeow.katmod;

import dev.itsmeow.katmod.items.ExplodingStickItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(
            BuiltInRegistries.ITEM,
            KatMod.MODID
    );

    public static final DeferredHolder<Item, ExplodingStickItem> EXPLODING_STICK = ITEMS.register("exploding_stick", () -> new ExplodingStickItem(new Item.Properties()));

}
