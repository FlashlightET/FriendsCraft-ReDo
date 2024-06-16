package net.ezogaming.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.ezogaming.FriendScreenHandler;
import net.ezogaming.FriendsCraft;
import net.ezogaming.entity.FriendEntity;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.client.render.GameRenderer;


public class FriendScreen extends HandledScreen<FriendScreenHandler> {
    private static final Identifier CONTAINER = FriendsCraft.identifier("textures/gui/friend_container.png");

    public FriendScreen(FriendScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    public void render(DrawContext matrixStack, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void drawBackground(DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, FriendScreen.CONTAINER);
        int centerX = (this.width - this.backgroundWidth) / 2;
        int centerY = (this.height - this.backgroundHeight) / 2;
        context.drawTexture(CONTAINER, centerX, centerY, 0, 0, this.backgroundWidth, this.backgroundHeight);

        FriendEntity friend = this.getScreenHandler().getFriend();
        Inventory friendInventory = friend.getInventory();
        int inventorySize = friendInventory.size();

        InventoryScreen.drawEntity(context, centerX + 151, centerY + 67, 23, centerX + 151 - mouseX, centerY + 25 - mouseY, friend);
    }

}
