package com.minecampkids.potatoes;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockPotatoBattery extends Block {
    
    private static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(0.8, 0.0, 0.8, 0.2, 1.0, 0.2);
    
    public BlockPotatoBattery(){
        super(Material.CLAY);
        setSoundType(SoundType.GLASS);
        setHarvestLevel("pickaxe", 0);
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
    public boolean isFullCube(IBlockState state) {
        return false;
    }
    
    @Override
    public boolean canRenderInLayer(IBlockState state, BlockRenderLayer layer) {
        return layer == BlockRenderLayer.SOLID || layer == BlockRenderLayer.TRANSLUCENT;
    }
    
    @Override
    public TileEntity createTileEntity(World world, IBlockState state){
        return new TileEntityPotatoBattery();
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos){
        return BOUNDING_BOX;
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

    @Override
    public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest) {
        if (willHarvest) {
            return true;
        }
        return super.removedByPlayer(state, world, pos, player, willHarvest);
    }

    @Override
    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, ItemStack stack) {
        super.harvestBlock(worldIn, player, pos, state, te, stack);
        worldIn.setBlockToAir(pos);
    }

    @Override
    public final void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof TileEntityPotatoBattery) {
            ItemStack drop = new ItemStack(this, 1, damageDropped(state));
            drop.getOrCreateSubCompound(PotatoUtilities.MODID).setInteger("power", ((TileEntityPotatoBattery) te).getPowerRemaining());
            drops.add(drop);
        }
    }
    
    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
        NBTTagCompound tag = stack.getSubCompound(PotatoUtilities.MODID);
        TileEntity te = worldIn.getTileEntity(pos);
        if (te instanceof TileEntityPotatoBattery && tag != null && tag.hasKey("power")) {
            ((TileEntityPotatoBattery)te).setPower(tag.getInteger("power"));
        }
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        NBTTagCompound tag = stack.getSubCompound(PotatoUtilities.MODID);
        int power = tag != null && tag.hasKey("power") ? tag.getInteger("power") : TileEntityPotatoBattery.MAX_POWER;
        tooltip.add(I18n.format(getTranslationKey() + ".tooltip", power));
    }
}
