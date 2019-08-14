package com.minecampkids.potatoes;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockPotatoBattery extends Block {
    public BlockPotatoBattery(){
        super(Material.CLAY);
        this.setCreativeTab(CreativeTabs.REDSTONE);
        this.setRegistryName("potatobattery");
        this.setTranslationKey(PotatoUtilities.MODID + "." + this.getRegistryName());
        this.setTranslationKey(PotatoUtilities.MODID + "." + this.getRegistryName());
    }

    @Override
    public boolean hasTileEntity(IBlockState state){
        return true;
    }

    public TileEntity createTileEntity(World world, IBlockState state){
        return new TileEntityPotatoBattery();
    }
}
