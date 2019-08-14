package com.minecampkids.potatoes;

import org.apache.logging.log4j.Logger;

import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = PotatoUtilities.MODID, name = PotatoUtilities.NAME, version = PotatoUtilities.VERSION)
public class PotatoUtilities {

    public static final String MODID = "potatoutilities";
    public static final String NAME = "Potato Utilities";
    public static final String VERSION = "1.0";

    private static Logger logger;
    
    static {
        FluidRegistry.enableUniversalBucket();
    }
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        PUFluids.registerFluids();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        PUFluids.liquidPlastic().setBlock(PUBlocks.LIQUID_PLASTIC);
    }
}
