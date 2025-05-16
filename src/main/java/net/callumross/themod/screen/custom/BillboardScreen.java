package net.callumross.themod.screen.custom;

import com.mojang.blaze3d.systems.RenderSystem;
import net.callumross.themod.TheMod;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class BillboardScreen extends AbstractContainerScreen<BillboardMenu>
{
    private static final ResourceLocation GUI_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(TheMod.MOD_ID, "textures/gui/billboard/billboard_gui.png");

    public BillboardScreen(BillboardMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        final int MENUHEIGHT = 145;
        final int MENUWIDTH = 256;
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);

        //imageWidth and imageHeight default to 176 and 166 respectively, can be changed
        int x = (width - MENUWIDTH) / 2;
        int y = (height - MENUHEIGHT) / 2;

        //add two extra values for texture height and width if it is not the default of 256x256
        pGuiGraphics.blit(GUI_TEXTURE, x, y, 0, 0, MENUWIDTH, MENUHEIGHT);
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }
}
