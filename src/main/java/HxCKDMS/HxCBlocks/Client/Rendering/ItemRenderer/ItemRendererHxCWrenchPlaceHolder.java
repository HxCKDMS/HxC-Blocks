package HxCKDMS.HxCBlocks.Client.Rendering.ItemRenderer;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

public class ItemRendererHxCWrenchPlaceHolder implements IItemRenderer {
    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack stack, Object... data) {
        if(stack.hasTagCompound()){
            try{
                Block BlockToBeRendered = GameRegistry.findBlock(stack.stackTagCompound.getString("BlockOwner"), stack.stackTagCompound.getString("BlockUN"));

                Minecraft mc = Minecraft.getMinecraft();
                RenderManager.instance.itemRenderer.renderItem(mc.thePlayer, new ItemStack(BlockToBeRendered, 1, stack.stackTagCompound.getInteger("BlockMeta")), 0, type);
            }catch (Exception unhandled){
                Tessellator tessellator = new Tessellator();

                tessellator.startDrawingQuads();

                tessellator.setColorOpaque(255, 255, 255);

                tessellator.addVertex(1, 1, 1);
                tessellator.addVertex(1, 0, 1);
                tessellator.addVertex(1, 0, 0);
                tessellator.addVertex(1, 1, 0);

                tessellator.addVertex(0, 1, 1);
                tessellator.addVertex(0, 0, 1);
                tessellator.addVertex(1, 0, 1);
                tessellator.addVertex(1, 1, 1);

                tessellator.addVertex(1, 1, 1);
                tessellator.addVertex(1, 1, 0);
                tessellator.addVertex(0, 1, 0);
                tessellator.addVertex(0, 1, 1);

                tessellator.draw();
            }
        }else{
            Tessellator tessellator = new Tessellator();

            tessellator.startDrawingQuads();

            tessellator.setColorOpaque(255, 255, 255);

            tessellator.addVertex(1, 1, 1);
            tessellator.addVertex(1, 0, 1);
            tessellator.addVertex(1, 0, 0);
            tessellator.addVertex(1, 1, 0);

            tessellator.addVertex(0, 1, 1);
            tessellator.addVertex(0, 0, 1);
            tessellator.addVertex(1, 0, 1);
            tessellator.addVertex(1, 1, 1);

            tessellator.addVertex(1, 1, 1);
            tessellator.addVertex(1, 1, 0);
            tessellator.addVertex(0, 1, 0);
            tessellator.addVertex(0, 1, 1);

            tessellator.draw();
        }
    }
}
