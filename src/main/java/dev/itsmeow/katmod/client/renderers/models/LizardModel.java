package dev.itsmeow.katmod.client.renderers.models;

// Made with Blockbench 4.10.4

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.itsmeow.katmod.KatMod;
import dev.itsmeow.katmod.entities.LizardEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;

public class LizardModel<T extends LizardEntity> extends EntityModel<T> {
	public static final ModelLayerLocation LIZARD_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(KatMod.MODID, "lizard"), "main");
	private final ModelPart root;
	private int ticksToExplode = -1;

	public LizardModel(ModelPart root) {
		this.root = root.getChild("root");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -3.0F, -6.0F, 4.0F, 3.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(16, 15).addBox(-2.0F, -5.0F, -10.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.0F, -4.0F, -12.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(20, 24).addBox(-4.0F, -2.0F, -8.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(0, 24).addBox(-4.0F, -2.0F, 3.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(11, 22).addBox(2.0F, -2.0F, 3.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(20, 0).addBox(2.0F, -2.0F, -8.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(0, 15).addBox(-1.0F, -2.25F, 6.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(LizardEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.ticksToExplode = entity.getTicksToExplodeClient();
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
		root.render(poseStack, buffer, packedLight, packedOverlay, this.ticksToExplode > -1 ? FastColor.ARGB32.lerp(1F - ((float) this.ticksToExplode / 200F), color, 0x571d80) : color);
	}
}