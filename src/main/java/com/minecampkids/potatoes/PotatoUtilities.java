package com.minecampkids.potatoes;

import org.apache.logging.log4j.Logger;

import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.oredict.OreDictionary;

@Mod(modid = PotatoUtilities.MODID, name = PotatoUtilities.NAME, version = PotatoUtilities.VERSION)
public class PotatoUtilities {

    public static final String MODID = "potatoutilities";
    public static final String NAME = "Potato Utilities";
    public static final String VERSION = "1.0";

    private static Logger logger;
    
    public static final SimpleNetworkWrapper network = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);
    
    static {
        FluidRegistry.enableUniversalBucket();
        
        network.registerMessage(PacketBatteryState.Handler.class, PacketBatteryState.class, 0, Side.CLIENT);
    }
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        PUFluids.registerFluids();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        PUFluids.liquidPlastic().setBlock(PUBlocks.LIQUID_PLASTIC);
        
        OreDictionary.registerOre("leather", PUItems.PLEATHER);
    }
}
