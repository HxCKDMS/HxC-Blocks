package HxCKDMS.HxCBlocks.Client.Rendering.GUI;

import HxCKDMS.HxCBlocks.Container.PotionBrewerContainer;
import HxCKDMS.HxCBlocks.HxCBlocks;
import HxCKDMS.HxCBlocks.lib.Reference;
import HxCKDMS.HxCBlocks.network.HxCTileSync;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("unchecked")
public class PotionBrewerGUI extends GuiContainer {
    private int x, y, z;
    public PotionBrewerGUI(PotionBrewerContainer container) {
        super(container);
        this.x = container.hxCTile.xCoord;
        this.y = container.hxCTile.yCoord;
        this.z = container.hxCTile.zCoord;
    }

    @Override
    public void initGui() {
        // need this to calculate center
        ScaledResolution sres = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
        int button_width = 160;

        int GUITCX = (sres.getScaledWidth() - button_width) / 2;
        int GUITCY = (sres.getScaledHeight() - 160) / 2;

        addButton(new GuiButton(0, 0, 0, button_width, 20, "Brew!"), GUITCX, GUITCY + 55);
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
        drawCenteredString(fontRendererObj, I18n.format("Potion Brewer"), width / 2, 25, Color.WHITE.getRGB());
        super.drawScreen(mouseX, mouseY, f);
        for (Object button : buttonList) {
            if (button instanceof GuiButton) {
                GuiButton btn = (GuiButton) button;
                if (btn.isMouseOver()) {
                    String text = "Begin the brew!";
                    List temp = Collections.singletonList(text);
                    drawHoveringText(temp, mouseX, mouseY, fontRendererObj);
                }
            }
        }
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button.id == 0) {
            HxCBlocks.networkWrapper.sendToServer(new HxCTileSync(x, y, z));
        }
    }

    public void addButton(GuiButton btn, int xpos, int ypos) {
        btn.xPosition = xpos;
        btn.yPosition = ypos;
        buttonList.add(btn);
    }
}