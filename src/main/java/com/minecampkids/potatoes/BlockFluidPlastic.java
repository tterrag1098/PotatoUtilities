package com.minecampkids.potatoes;

import java.util.Random;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidFinite;
import net.minecraftforge.fluids.Fluid;

public class BlockFluidPlastic extends BlockFluidFinite {

    public BlockFluidPlastic(Fluid fluid) {
        super(fluid, new MaterialLiquid(MapColor.CLOTH) {

            @Override
            public boolean blocksMovement() {
                return true; // so our liquids are not replaced by water
            }
        });
    }

    @Override
    public Boolean isEntityInsideMaterial(IBlockAccess world, BlockPos blockpos, IBlockState iblockstate, Entity entity, double yToTest, Material materialIn, boolean testingHead) {
        if (materialIn == Material.WATER || materialIn == this.material) {
            return Boolean.TRUE;
        }
        return super.isEntityInsideMaterial(world, blockpos, iblockstate, entity, yToTest, materialIn, testingHead);
    }
    
    @Override
    public void randomTick(World worldIn, BlockPos pos, IBlockState state, Random random) {
        super.randomTick(worldIn, pos, state, random);
        if (random.nextInt(10) == 0) {
            worldIn.setBlockState(pos, PUBlocks.PLASTIC.getDefaultState());
        }
    }
}
