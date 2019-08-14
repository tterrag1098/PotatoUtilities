package com.minecampkids.potatoes;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketBatteryState implements IMessage {
    
    int x, y, z;
    boolean empty;
    
    public PacketBatteryState() {}
    
    public PacketBatteryState(BlockPos pos, boolean empty) {
        this.x = pos.getX();
        this.y = pos.getY();
        this.z = pos.getZ();
        this.empty = empty;
    }
    
    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
        buf.writeBoolean(this.empty);
    }
    
    @Override
    public void fromBytes(ByteBuf buf) {
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
        this.empty = buf.readBoolean();
    }
    
    public static class Handler implements IMessageHandler<PacketBatteryState, IMessage> {
        
        @Override
        public IMessage onMessage(PacketBatteryState message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> {
                TileEntity te = Minecraft.getMinecraft().world.getTileEntity(new BlockPos(message.x, message.y, message.z));
                if (te instanceof TileEntityPotatoBattery) {
                    if (message.empty) {
                        ((TileEntityPotatoBattery) te).empty();
                    } else {
                        ((TileEntityPotatoBattery) te).refuel();
                    }
                }
            });
            return null;
        }
    }
}
