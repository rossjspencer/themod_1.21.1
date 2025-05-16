package net.callumross.themod.item;

import net.callumross.themod.TheMod;
import net.callumross.themod.item.custom.MissileLauncherItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems
{
    public static final DeferredRegister<Item> ITEMS =
        DeferredRegister.create(ForgeRegistries.ITEMS, TheMod.MOD_ID);

    // ADD ITEMS HERE
    public static final RegistryObject<Item> MISSILE_LAUNCHER = ITEMS.register("missile_launcher",
            () -> new MissileLauncherItem(new Item.Properties()));

    public static void register(IEventBus eventBus)
    {
        ITEMS.register(eventBus);
    }
}
