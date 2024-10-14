package dev.itsmeow.katmod.client;

import dev.itsmeow.katmod.KatMod;
import dev.itsmeow.katmod.client.renderers.LizardRenderer;
import dev.itsmeow.katmod.client.renderers.models.LizardModel;
import dev.itsmeow.katmod.init.ModEntities;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@Mod(value = KatMod.MODID, dist = Dist.CLIENT)
public class KatModClient {

    public KatModClient(IEventBus modBus) {
        modBus.addListener(this::registerLayerDefinitions);
        modBus.addListener(this::registerEntityRenderers);
    }

    public void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(LizardModel.LIZARD_LAYER, LizardModel::createBodyLayer);
    }

    public void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.LIZARD.get(), LizardRenderer::new);
    }

}
