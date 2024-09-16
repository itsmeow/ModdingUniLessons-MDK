package dev.itsmeow.katmod.items;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class ExplodingStickItem extends Item {

    public ExplodingStickItem(Properties properties) {
        super(properties);
    }


    @Override
    public InteractionResult useOn(UseOnContext context) {
        if(context.getHand() == InteractionHand.MAIN_HAND && !context.getLevel().isClientSide()) {
            context.getLevel()
                    .explode(
                            null,
                            context.getClickedPos().getX(),
                            context.getClickedPos().getY(),
                            context.getClickedPos().getZ(),
                            4.0F,
                            Level.ExplosionInteraction.TNT
                    );
            return InteractionResult.SUCCESS_NO_ITEM_USED;
        }
        return super.useOn(context);
    }
}
