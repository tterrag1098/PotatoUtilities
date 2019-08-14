package com.minecampkids.potatoes;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.energy.IEnergyStorage;

public class TileEntityPotatoBattery extends TileEntity implements IEnergyStorage {
    private int energy = 4;

    public TileEntityPotatoBattery(){
        super();
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        return 0;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        return energy;
    }

    @Override
    public int getEnergyStored() {
        return energy;
    }

    @Override
    public int getMaxEnergyStored() {
        return energy;
    }

    @Override
    public boolean canExtract() {
        return true;
    }

    @Override
    public boolean canReceive() {
        return false;
    }
}
