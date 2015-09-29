package HxCKDMS.HxCBlocks.Client.Rendering.GUI;

import HxCKDMS.HxCBlocks.Container.*;
import HxCKDMS.HxCBlocks.TileEntities.HxCTile;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GUIHandler implements IGuiHandler {
    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if(tileEntity instanceof HxCTile) {
            switch (id) {
                case (0) :
                    return new BarrierContainer(player, (HxCTile)tileEntity);
                case (1) :
                    return new PotionBrewerContainer(player, (HxCTile)tileEntity);
                case (2) :
                    return new SlaughterBlockContainer(player, (HxCTile)tileEntity);
                default : return null;
            }
        }
        return null;
    }
    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(x, y, z);
        if(tileEntity instanceof HxCTile){
            switch (id) {
                case (0) :
                    return new BarrierGUI(new BarrierContainer(player, (HxCTile)tileEntity));
                case (1) :
                    return new PotionBrewerGUI(new PotionBrewerContainer(player, (HxCTile)tileEntity));
                case (2) :
                    return new SlaughterBlockGUI(new SlaughterBlockContainer(player, (HxCTile)tileEntity));
                default : return null;
            }
        }
        return null;
    }
}