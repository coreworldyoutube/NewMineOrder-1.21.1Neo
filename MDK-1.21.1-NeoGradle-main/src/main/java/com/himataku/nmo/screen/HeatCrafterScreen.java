package com.himataku.nmo.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class HeatCrafterScreen extends AbstractContainerScreen<HeatCrafterMenu> {

    private static final ResourceLocation GUI_TEXTURE = new ResourceLocation("nmo", "textures/gui/heat_crafter.png");

    public HeatCrafterScreen(HeatCrafterMenu menu, net.minecraft.world.entity.player.Inventory inv, Component title) {
        super(menu, inv, title);
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    @Override
    protected void renderBg(PoseStack poseStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShaderTexture(0, GUI_TEXTURE);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        this.blit(poseStack, x, y, 0, 0, imageWidth, imageHeight);

        // プログレスバー描画（例: 縦 24px）
        int progress = menu.blockEntity.getProgress() * 24 / 100;
        this.blit(poseStack, x + 80, y + 35, 176, 0, progress, 17);
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(poseStack, mouseX, mouseY);
    }
}
