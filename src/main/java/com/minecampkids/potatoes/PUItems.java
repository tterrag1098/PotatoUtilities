package com.minecampkids.potatoes;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@EventBusSubscriber
@ObjectHolder(PotatoUtilities.MODID)
public class PUItems {
    
    public static final Item PLASTIC = null;
    public static final Item POTATO_BATTERY = new ItemBlock(new BlockPotatoBattery()).setRegistryName(PotatoUtilities.MODID, "potatobattery");
    
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new Item()
                .setCreativeTab(CreativeTabs.MISC)
                .setTranslationKey(PotatoUtilities.MODID + ".plastic")
                .setRegistryName("plastic"));
        event.getRegistry().register(POTATO_BATTERY);
    }
}
