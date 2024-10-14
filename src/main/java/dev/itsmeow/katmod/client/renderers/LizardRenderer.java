package dev.itsmeow.katmod.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.itsmeow.katmod.KatMod;
import dev.itsmeow.katmod.client.renderers.models.LizardModel;
import dev.itsmeow.katmod.entities.LizardEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class LizardRenderer extends MobRenderer<LizardEntity, LizardModel<LizardEntity>> {

    public static final ResourceLocation LIZARD_TEXTURE = ResourceLocation.fromNamespaceAndPath(KatMod.MODID, "textures/entity/lizard.png");

    public LizardRenderer(EntityRendererProvider.Context context) {
        super(context, new LizardModel<>(context.bakeLayer(LizardModel.LIZARD_LAYER)), 0.25F);
    }

    @Override
    protected void scale(LizardEntity livingEntity, PoseStack poseStack, float partialTickTime) {
        int ticksToExplode = livingEntity.getTicksToExplodeClient();
        float scaleAdjustment = ticksToExplode > 0 ? Math.clamp(2F * (float) Math.pow(1F - ((float) ticksToExplode / 200F), 2), 0F, 4F) : 0F;
        poseStack.scale(0.5F + scaleAdjustment, 0.5F + scaleAdjustment, 0.5F + scaleAdjustment);
    }

    @Override
    public ResourceLocation getTextureLocation(LizardEntity entity) {
        return LIZARD_TEXTURE;
    }
}
