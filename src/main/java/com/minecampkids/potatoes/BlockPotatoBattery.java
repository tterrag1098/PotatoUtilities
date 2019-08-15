package com.minecampkids.potatoes;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockPotatoBattery extends Block {
    public BlockPotatoBattery(){
        super(Material.CLAY);
    }

    @Override
    public boolean hasTileEntity(IBlockState state){
        return true;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state){
        return false;
    }
    
    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state){
        return new TileEntityPotatoBattery();
    }
    
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack held = playerIn.getHeldItem(hand);
        if (held.getItem() == Items.IRON_INGOT) {
            TileEntity te = worldIn.getTileEntity(pos);
            if (te instanceof TileEntityPotatoBattery && ((TileEntityPotatoBattery) te).isEmpty()) {
                if (!worldIn.isRemote) {
                    ((TileEntityPotatoBattery)te).refuel();
                    held.shrink(1);
                    playerIn.setHeldItem(hand, held);
                }
                return true;
            }
        }
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
    }
}
