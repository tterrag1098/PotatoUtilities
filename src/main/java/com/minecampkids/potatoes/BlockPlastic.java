package com.minecampkids.potatoes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockPlastic extends Block {
    
    public static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(0, 0, 0, 1, 1 / 16D, 1);
    
    public BlockPlastic() {
        super(Material.CLAY);
        setSoundType(SoundType.SLIME);
    }
   
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }
   
    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }
    
    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return BOUNDING_BOX;
    }
    
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return PUItems.PLASTIC;
    }
    
    @Override
    public int quantityDropped(IBlockState state, int fortune, Random random) {
        return Math.min(4, random.nextInt(3) + 1 + fortune);
    }
}
