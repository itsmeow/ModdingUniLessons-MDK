package dev.itsmeow.katmod.entities;

import dev.itsmeow.katmod.init.ModEntities;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class LizardEntity extends Animal {

    private static final EntityDataAccessor<Integer> DATA_TICKS_TO_EXPLODE = SynchedEntityData.defineId(LizardEntity.class, EntityDataSerializers.INT);
    private int ticksToExplode = -1;

    public LizardEntity(EntityType<? extends LizardEntity> entityType, Level level) {
        super(entityType, level);
    }

    public LizardEntity(Level level) {
        super(ModEntities.LIZARD.get(), level);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_TICKS_TO_EXPLODE, -1);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 2.0));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25, itemStack -> itemStack.getItem() == Items.SPIDER_EYE, false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.25));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    public void makeExplode() {
        this.ticksToExplode = 200;
        this.entityData.set(DATA_TICKS_TO_EXPLODE, this.ticksToExplode);
    }

    public int getTicksToExplodeClient() {
        return this.entityData.get(DATA_TICKS_TO_EXPLODE);
    }

    @Override
    public void tick() {
        super.tick();
        if(this.ticksToExplode > 0) {
            --this.ticksToExplode;
            this.entityData.set(DATA_TICKS_TO_EXPLODE, this.ticksToExplode);
            if(this.ticksToExplode == 0) {
                this.level().explode(this, this.getX(), this.getY(), this.getZ(), 4F, Level.ExplosionInteraction.TNT);
                this.setHealth(0F);
                this.die(this.damageSources().generic());
            }
        }
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (itemstack.getItem() == Items.FERMENTED_SPIDER_EYE && this.getTicksToExplodeClient() == -1) {
            if (!this.level().isClientSide) {
                this.usePlayerItem(player, hand, itemstack);
                // get ready to explode
                this.makeExplode();
                return InteractionResult.SUCCESS;
            }


            if (this.level().isClientSide) {
                return InteractionResult.CONSUME;
            }
        }

        return super.mobInteract(player, hand);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.getItem() == Items.SPIDER_EYE;
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        return new LizardEntity(level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 12.0F).add(Attributes.MOVEMENT_SPEED, 1.5F);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.ticksToExplode = compound.getInt("TicksToExplode");
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("TicksToExplode", this.ticksToExplode);
    }
}
