package dev.itsmeow.katmod.client.renderers.models;

// Made with Blockbench 4.10.4

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.itsmeow.katmod.KatMod;
import dev.itsmeow.katmod.entities.LizardEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;

public class LizardModel<T extends LizardEntity> extends HierarchicalModel<T> {
	public static final ModelLayerLocation LIZARD_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(KatMod.MODID, "lizard"), "main");
	public static final AnimationDefinition NODDING_ANIMATION = AnimationDefinition.Builder.withLength(2.05F)
			.addAnimation("head", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.0833F, KeyframeAnimations.degreeVec(10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(0.5417F, KeyframeAnimations.degreeVec(-35.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.0833F, KeyframeAnimations.degreeVec(10.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(1.5417F, KeyframeAnimations.degreeVec(-35.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM),
					new Keyframe(2.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.CATMULLROM)
			))
			.build();

	private int ticksToExplode = -1;

	private final ModelPart body;
	private final ModelPart backRightLeg;
	private final ModelPart frontRightLeg;
	private final ModelPart backLeftLeg;
	private final ModelPart frontLeftLeg;
	private final ModelPart tail;
	private final ModelPart head;
	private final ModelPart snout;

	public LizardModel(ModelPart root) {
		this.body = root.getChild("body");
		this.backRightLeg = this.body.getChild("backRightLeg");
		this.frontRightLeg = this.body.getChild("frontRightLeg");
		this.backLeftLeg = this.body.getChild("backLeftLeg");
		this.frontLeftLeg = this.body.getChild("frontLeftLeg");
		this.tail = this.body.getChild("tail");
		this.head = this.body.getChild("head");
		this.snout = this.head.getChild("snout");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -1.0F, 5.0F, 4.0F, 3.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.0F, -11.0F));
		PartDefinition backRightLeg = body.addOrReplaceChild("backRightLeg", CubeListBuilder.create().texOffs(0, 24).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 1.0F, 15.0F));
		PartDefinition frontRightLeg = body.addOrReplaceChild("frontRightLeg", CubeListBuilder.create().texOffs(20, 24).addBox(-1.0F, -1.0F, -4.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 1.0F, 7.0F));
		PartDefinition backLeftLeg = body.addOrReplaceChild("backLeftLeg", CubeListBuilder.create().texOffs(11, 22).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 1.0F, 15.0F));
		PartDefinition frontLeftLeg = body.addOrReplaceChild("frontLeftLeg", CubeListBuilder.create().texOffs(20, 0).addBox(-1.0F, -1.0F, -4.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 1.0F, 7.0F));
		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 15).addBox(-1.0F, -1.25F, 0.0F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 17.0F));
		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(16, 15).addBox(-2.0F, -2.0F, -4.0F, 4.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 5.0F));
		PartDefinition snout = head.addOrReplaceChild("snout", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, -5.0F));
		return LayerDefinition.create(meshdefinition, 64, 64);
	}
	@Override
	public void setupAnim(LizardEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.ticksToExplode = entity.getTicksToExplodeClient();
		this.backRightLeg.yRot = Mth.cos(limbSwing * 0.3332F) * limbSwingAmount;
		this.backLeftLeg.yRot = Mth.cos(limbSwing * 0.3332F + (float) Math.PI) * limbSwingAmount;
		this.frontLeftLeg.yRot = Mth.cos(limbSwing * 0.3332F) * 1.4F * limbSwingAmount;
		this.frontRightLeg.yRot = Mth.cos(limbSwing * 0.3332F + (float) Math.PI) * limbSwingAmount;
		float lastTicksToExplode = (entity.lastTicksToExplodeClient == -1F ? ticksToExplode : entity.lastTicksToExplodeClient);
		float targetTailLength = (1F - (ticksToExplode / 200F)) * 5F;
		float lastTargetTailLength = (1F - (lastTicksToExplode / 200F)) * 5F;
		this.tail.zScale = ticksToExplode == -1 ? 1F : Mth.lerp(Minecraft.getInstance().gameRenderer.getMainCamera().getPartialTickTime(),lastTargetTailLength,targetTailLength) + 1F;
		this.animate(entity.noddingAnimationState, NODDING_ANIMATION, ageInTicks);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
		this.root().render(poseStack, buffer, packedLight, packedOverlay, this.ticksToExplode > -1 ? FastColor.ARGB32.lerp(1F - ((float) this.ticksToExplode / 200F), color, 0x571d80) : color);
	}

	@Override
	public ModelPart root() {
		return body;
	}
}