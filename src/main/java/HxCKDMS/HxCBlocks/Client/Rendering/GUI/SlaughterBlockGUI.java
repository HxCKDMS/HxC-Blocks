package HxCKDMS.HxCBlocks.Client.Rendering.GUI;

import HxCKDMS.HxCBlocks.Container.SlaughterBlockContainer;
import HxCKDMS.HxCBlocks.lib.Reference;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;

@SuppressWarnings("unchecked")
public class SlaughterBlockGUI extends GuiContainer {
    public SlaughterBlockGUI (SlaughterBlockContainer container) {
        super(container);
    }

    @Override
    public void initGui() {
        super.initGui();
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y) {
        GL11.glColor4f(1F, 1F, 1F, 1F);

        mc.getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID + ":gui/SlaughterBlock.png"));
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float f) {
        drawDefaultBackground();
        drawRect(width / 2 - 105, 60, width / 2 - 35, height / 2 + 5, new Color(50, 170, 170, 70).getRGB());
        drawCenteredString(fontRendererObj, I18n.format("Slaughter Block"), width / 2, 25, Color.WHITE.getRGB());
        super.drawScreen(mouseX, mouseY, f);
    }
}