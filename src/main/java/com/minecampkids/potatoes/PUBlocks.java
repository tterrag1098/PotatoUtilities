package com.minecampkids.potatoes;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fluids.BlockFluidFinite;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@EventBusSubscriber
@ObjectHolder(PotatoUtilities.MODID)
public class PUBlocks {
    
    public static final BlockFluidFinite LIQUID_PLASTIC = null;
    
    public static final Block PLASTIC = null;
    
    @SubscribeEvent
    public static void registerBlocks(Register<Block> event) {
        event.getRegistry().register(new BlockFluidPlastic(PUFluids.liquidPlastic())
                .setCreativeTab(CreativeTabs.MISC)
                .setTranslationKey(PotatoUtilities.MODID + "." + "liquid_plastic")
                .setRegistryName("liquid_plastic"));
        
        event.getRegistry().register(new BlockPlastic()
                .setCreativeTab(CreativeTabs.MISC)
                .setTranslationKey(PotatoUtilities.MODID + "." + "plastic")
                .setHardness(1.25F)
                .setResistance(2.5F)
                .setRegistryName("plastic"));
    }
}
