package net.callumross.themod.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.NotNull;

public class MissileLauncherItem extends Item
{
    public MissileLauncherItem(Properties pProperties)
    {
        super(pProperties);
    }

    @Override
    @NotNull
    public  InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand)
    {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        if(!pPlayer.isCrouching() && !pLevel.isClientSide())
        {
            System.out.println("Open menu\n");
        }
        else if(!pLevel.isClientSide())
        {
            HitResult result = pPlayer.pick(30, 0.0F, true);
            Vec3 launchLocation = result.getLocation();

            double y = launchLocation.y;

            //center x and z
            double x = (int)launchLocation.x + 0.5;
            double z = (int)launchLocation.z + 0.5;

            System.out.println("Quick Launch: x = "+x+", y = "+y+", z = "+z+"\n");

            //summon missile entity and all that below
        }

        return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
    }
}
