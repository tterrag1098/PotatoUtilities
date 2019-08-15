package com.minecampkids.potatoes;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber
@ObjectHolder(PotatoUtilities.MODID)
public class PUItems {
    
    public static final Item STARCHY_MIXTURE_BUCKET = null;
    public static final Item PLASTIC = null;
    public static final Item PLEATHER = null;
    
    public static final ItemBlock POTATO_BATTERY = null;
    
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new Item()
                .setCreativeTab(CreativeTabs.MISC)
                .setTranslationKey(PotatoUtilities.MODID + ".starchy_mixture_bucket")
                .setRegistryName("starchy_mixture_bucket"));
        
        event.getRegistry().register(new Item() {
            
            @Override
            @SideOnly(Side.CLIENT)
            public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
                tooltip.add(I18n.format(getTranslationKey() + ".tooltip"));
            }
        }
                .setCreativeTab(CreativeTabs.MISC)
                .setTranslationKey(PotatoUtilities.MODID + ".plastic")
                .setRegistryName("plastic"));
        
        event.getRegistry().register(new Item()
                .setCreativeTab(CreativeTabs.MISC)
                .setTranslationKey(PotatoUtilities.MODID + ".pleather")
                .setRegistryName("pleather"));
        
        event.getRegistry().register(new ItemBlock(PUBlocks.POTATO_BATTERY)
                .setRegistryName(PUBlocks.POTATO_BATTERY.getRegistryName()));
    }
}
