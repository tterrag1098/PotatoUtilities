package com.minecampkids.potatoes;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class PUFluids {
        
    private static Fluid starchyMixture, liquidPlastic;
    
    public static void registerFluids() {        
        starchyMixture = registerFluid("starchy_mixture");
        FluidRegistry.addBucketForFluid(starchyMixture);
        
        liquidPlastic = registerFluid("liquid_plastic");
        FluidRegistry.addBucketForFluid(liquidPlastic);
    }
    
    private static Fluid registerFluid(String name) {
        FluidRegistry.registerFluid(
                new Fluid(name, 
                        new ResourceLocation(PotatoUtilities.MODID, "blocks/" + name + "_still"), 
                        new ResourceLocation(PotatoUtilities.MODID, "blocks/" + name + "_flow")));
        return FluidRegistry.getFluid(name);
    }
    
    public static Fluid starchyMixture() {
        return starchyMixture;
    }

    public static Fluid liquidPlastic() {
        return liquidPlastic;
    }
}
