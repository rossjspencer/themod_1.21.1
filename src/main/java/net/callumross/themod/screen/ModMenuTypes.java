package net.callumross.themod.screen;

import net.callumross.themod.TheMod;
import net.callumross.themod.screen.custom.BillboardMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes
{
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, TheMod.MOD_ID);

    public static final RegistryObject<MenuType<BillboardMenu>> BILLBOARD_MENU =
            MENUS.register("billboard_name", () -> IForgeMenuType.create(BillboardMenu::new));

    public static void register(IEventBus eventBus)
    {
        MENUS.register(eventBus);
    }
}
