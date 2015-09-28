package HxCKDMS.HxCBlocks.Client.Rendering.TileRenderer;

import HxCKDMS.HxCBlocks.TileEntities.HxCTile;
import HxCKDMS.HxCBlocks.lib.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class HxCTileRender extends TileEntitySpecialRenderer {
    public HxCTileRender() {}

    @Override
    @SideOnly(Side.CLIENT)
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float partialTick) {
        ResourceLocation texture = new ResourceLocation(Reference.MOD_ID + ":textures/blocks/" + ((HxCTile)tileEntity).NAME + ".png");
        System.out.println(texture.getResourcePath());
        GL11.glPushMatrix();
        {
            GL11.glTranslated(x, y, z);
            Tessellator tessellator = Tessellator.instance;

            //sides
            this.bindTexture(texture);
            tessellator.startDrawingQuads();
            {
                tessellator.addVertexWithUV(0, 0, 1, 1, 1);
                tessellator.addVertexWithUV(0, 1, 1, 1, 0);
                tessellator.addVertexWithUV(0, 1, 0, 0, 0);
                tessellator.addVertexWithUV(0, 0, 0, 0, 1);

                tessellator.addVertexWithUV(1, 1, 1, 0, 1);
                tessellator.addVertexWithUV(1, 0, 1, 0, 0);
                tessellator.addVertexWithUV(1, 0, 0, 1, 0);
                tessellator.addVertexWithUV(1, 1, 0, 1, 1);

                tessellator.addVertexWithUV(0, 0, 0, 0, 1);
                tessellator.addVertexWithUV(0, 1, 0, 0, 0);
                tessellator.addVertexWithUV(1, 1, 0, 1, 0);
                tessellator.addVertexWithUV(1, 0, 0, 1, 1);

                tessellator.addVertexWithUV(0, 1, 1, 0, 1);
                tessellator.addVertexWithUV(0, 0, 1, 0, 0);
                tessellator.addVertexWithUV(1, 0, 1, 1, 0);
                tessellator.addVertexWithUV(1, 1, 1, 1, 1);

                tessellator.addVertexWithUV(0, 0, 1, 0, 1);
                tessellator.addVertexWithUV(0, 0, 0, 0, 0);
                tessellator.addVertexWithUV(1, 0, 0, 1, 0);
                tessellator.addVertexWithUV(1, 0, 1, 1, 1);
            }
            tessellator.draw();
            GL11.glPopMatrix();
        }
    }
}