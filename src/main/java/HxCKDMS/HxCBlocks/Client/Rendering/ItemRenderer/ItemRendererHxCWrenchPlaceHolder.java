package HxCKDMS.HxCBlocks.Client.Rendering.ItemRenderer;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class ItemRendererHxCWrenchPlaceHolder implements IItemRenderer {
    RenderItem renderItem = new RenderItem();

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return type != ItemRenderType.INVENTORY;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack stack, Object... data) {
        if(stack.hasTagCompound()) {
            Block BlockToBeRendered = GameRegistry.findBlock(stack.stackTagCompound.getString("BlockOwner"), stack.stackTagCompound.getString("BlockUN"));
            ItemStack containedStack = new ItemStack(BlockToBeRendered, 1, stack.stackTagCompound.getInteger("BlockMeta"));

            Minecraft mc = Minecraft.getMinecraft();
            //RenderManager.instance.itemRenderer.renderItem(mc.thePlayer, new ItemStack(BlockToBeRendered, 1, stack.stackTagCompound.getInteger("BlockMeta")), 0, type);

            if(containedStack.getItem() != null){
                if(type == ItemRenderType.INVENTORY){
                    RenderHelper.enableGUIStandardItemLighting();
                    renderItem.renderItemIntoGUI(mc.fontRenderer, mc.renderEngine, containedStack, 0, 0);
                }else{
                    GL11.glTranslated(0.5, 0.5, 0.5);
                    GL11.glScalef(0.8f, 0.8f, 0.8f);
                    RenderManager.instance.itemRenderer.renderItem(mc.thePlayer, containedStack, 0, ItemRenderType.EQUIPPED);
                }
            }
        }
    }
}
