package net.callumross.themod.block.entity;

import net.callumross.themod.TheMod;
import net.callumross.themod.block.ModBlocks;
import net.callumross.themod.block.entity.custom.BillboardBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, TheMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<BillboardBlockEntity>> BILLBOARD_BE =
            BLOCK_ENTITIES.register("billboard_be", () -> BlockEntityType.Builder.of(
                    BillboardBlockEntity::new, ModBlocks.BILLBOARD.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}