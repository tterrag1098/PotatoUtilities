package com.minecampkids.potatoes;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityPotatoBattery extends TileEntity implements ITickable {
    
    private static final int ENERGY_PER_TICK = 3;
    private static final int MINUTES_OF_POWER = 20 * 3; // 3 days
    
    // ENERGY_PER_TICK FE per tick * 20 ticks per second * 60 seconds per minute * MINUTES_OF_POWER
    // = MINUTES_OF_POWER at full output (at ENERGY_PER_TICK)
    private static final int MAX_POWER = ENERGY_PER_TICK * 20 * 60 * MINUTES_OF_POWER;
    
    private int powerRemaining = MAX_POWER;
    
    private final EnergyStorage energy = new EnergyStorage(ENERGY_PER_TICK) {

        @Override
        public int getEnergyStored() {
            return ENERGY_PER_TICK;
        }

        @Override
        public int getMaxEnergyStored() {
            return ENERGY_PER_TICK;
        }

        @Override
        public boolean canExtract() {
            return false;
        }

        @Override
        public boolean canReceive() {
            return false;
        }
    };
    
    @Override
    public void update() {
        if (getWorld().isRemote) return;
        if (isEmpty()) {
            updateClient(true);
            return;
        }
        
        final int maxPowerToGive = Math.min(powerRemaining, ENERGY_PER_TICK);
        int powerToGive = maxPowerToGive;

        TileEntity te = getWorld().getTileEntity(getPos().offset(EnumFacing.UP));
        if (te != null) {
            IEnergyStorage energy = te.getCapability(CapabilityEnergy.ENERGY, EnumFacing.DOWN);
            if (energy != null) {
                powerToGive -= energy.receiveEnergy(powerToGive, false);
            }
        }
        
        if (powerToGive != maxPowerToGive) {
            powerRemaining -= maxPowerToGive - powerToGive;
            getWorld().markChunkDirty(getPos(), this);
        }
    }
    
    public boolean isEmpty() {
        return powerRemaining <= 0;
    }
    
    public void refuel() {
        this.powerRemaining = MAX_POWER;
        updateClient(false);
    }
    
    private void updateClient(boolean empty) {
        BlockPos pos = getPos();
        PotatoUtilities.network.sendToAllTracking(new PacketBatteryState(getPos(), empty), 
                new TargetPoint(getWorld().provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 1));
    }
    
    @SideOnly(Side.CLIENT)
    public void empty() {
        this.powerRemaining = 0;
    }
    
    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return (facing == EnumFacing.UP && capability == CapabilityEnergy.ENERGY) || super.hasCapability(capability, facing);
    }
    
    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY && facing == EnumFacing.UP) {
            return CapabilityEnergy.ENERGY.cast(energy);
        }
        return super.getCapability(capability, facing);
    }
    
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setInteger("potatoPower", powerRemaining);
        return super.writeToNBT(compound);
    }
    
    @Override
    public void readFromNBT(NBTTagCompound compound) {
        powerRemaining = compound.getInteger("potatoPower");
        super.readFromNBT(compound);
    }
}
