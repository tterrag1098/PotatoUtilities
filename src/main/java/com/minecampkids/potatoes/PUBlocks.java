package com.minecampkids.potatoes;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fluids.BlockFluidFinite;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@EventBusSubscriber
@ObjectHolder(PotatoUtilities.MODID)
public class PUBlocks {
    
    public static final BlockFluidFinite LIQUID_PLASTIC = null;
    
    public static final Block PLASTIC = null;
    public static final Block POTATO_BATTERY = null;

    @SubscribeEvent
    public static void registerBlocks(Register<Block> event) {
        event.getRegistry().register(new BlockFluidPlastic(PUFluids.liquidPlastic())
                .setCreativeTab(CreativeTabs.MISC)
                .setTranslationKey(PotatoUtilities.MODID + "." + "liquid_plastic")
                .setRegistryName("liquid_plastic"));
        
        event.getRegistry().register(new BlockPlastic()
                .setCreativeTab(CreativeTabs.MISC)
                .setTranslationKey(PotatoUtilities.MODID + "." + "plastic")
                .setHardness(0.5F)
                .setRegistryName("plastic"));
        
        event.getRegistry().register(new BlockPotatoBattery()
                .setCreativeTab(CreativeTabs.REDSTONE)
                .setTranslationKey(PotatoUtilities.MODID + "." + "potato_battery")
                .setRegistryName("potato_battery"));
        GameRegistry.registerTileEntity(TileEntityPotatoBattery.class, new ResourceLocation(PotatoUtilities.MODID, "potato_battery"));
    }
}
