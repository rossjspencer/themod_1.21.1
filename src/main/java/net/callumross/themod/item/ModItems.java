package net.callumross.themod.item;

import net.callumross.themod.TheMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems
{
    public static final DeferredRegister<Item> ITEMS =
        DeferredRegister.create(ForgeRegistries.ITEMS, TheMod.MOD_ID);

    // ADD ITEMS HERE

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
