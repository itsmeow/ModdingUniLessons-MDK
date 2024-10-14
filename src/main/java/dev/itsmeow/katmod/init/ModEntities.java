package dev.itsmeow.katmod.init;

import dev.itsmeow.katmod.KatMod;
import dev.itsmeow.katmod.entities.LizardEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;

@EventBusSubscriber(modid = KatMod.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, KatMod.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<LizardEntity>> LIZARD = register("lizard", LizardEntity::new, MobCategory.CREATURE, builder -> builder.sized(0.35F, 0.2F).eyeHeight(0.18F));

    private static <T extends Entity> DeferredHolder<EntityType<?>, EntityType<T>> register(String key, EntityType.EntityFactory<T> factory, MobCategory category, Function<EntityType.Builder<T>, EntityType.Builder<T>> builder) {
        return ENTITIES.register(key, () -> builder.apply(EntityType.Builder.of(factory, category)).build(key));
    }

    @SubscribeEvent
    public static void entityAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.LIZARD.get(), LizardEntity.createAttributes().build());
    }

}
