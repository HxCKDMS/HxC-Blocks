package HxCKDMS.HxCBlocks.network;

import HxCKDMS.HxCBlocks.TileEntities.HxCTile;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;

public class HxCTileSync implements IMessage {
    private int x = 0, y = 0, z = 0;
    public HxCTileSync() {}

    public HxCTileSync(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }


    @Override
    public void toBytes(ByteBuf byteBuf) {
        byteBuf.writeInt(x);
        byteBuf.writeInt(y);
        byteBuf.writeInt(z);
    }

    @Override
    public void fromBytes(ByteBuf byteBuf) {
        x = byteBuf.readInt();
        y = byteBuf.readInt();
        z = byteBuf.readInt();
    }

    public static class handler implements IMessageHandler<HxCTileSync, IMessage> {
        @Override
        public IMessage onMessage(HxCTileSync message, MessageContext ctx) {
            TileEntity tileEntity = ctx.getServerHandler().playerEntity.worldObj.getTileEntity(message.x, message.y, message.z);

            if (tileEntity != null && tileEntity instanceof HxCTile) {
                HxCTile hxCTile = (HxCTile) tileEntity;
                hxCTile.poke();
            }
            return null;
        }
    }
}