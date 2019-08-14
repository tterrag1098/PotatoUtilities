package com.minecampkids.potatoes;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.BlockFluidFinite;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class PUModels {

    @SubscribeEvent
    public static void onModelRegistry(ModelRegistryEvent event) {
        ModelLoader.setCustomModelResourceLocation(PUItems.PLASTIC, 0, new ModelResourceLocation(PUItems.PLASTIC.getRegistryName(), "inventory"));
        
        ModelLoader.setCustomStateMapper(PUBlocks.LIQUID_PLASTIC, new StateMap.Builder().ignore(BlockFluidFinite.LEVEL).build());
    }
    
}