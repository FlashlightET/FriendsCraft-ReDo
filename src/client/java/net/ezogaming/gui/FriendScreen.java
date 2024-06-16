package net.ezogaming.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.ezogaming.FriendScreenHandler;
import net.ezogaming.FriendsCraft;
import net.ezogaming.entity.FriendEntity;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;


public class FriendScreen extends HandledScreen<FriendScreenHandler> {
    private final Random random = Random.create();
    private static final Identifier CONTAINER = FriendsCraft.identifier("textures/gui/friend_container.png");
    protected int backgroundWidth = 176;
    protected int backgroundHeight = 166;

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

        InventoryScreen.drawEntity(context, centerX + 151, centerY + 64, 23, centerX + 151 - mouseX, centerY + 25 - mouseY, friend);
        this.renderFriendHealth(context,(int)friend.getHealth(),(int)friend.getMaxHealth(), centerX, centerY);
        //FriendsCraft.LOGGER.info(String.valueOf(friend.getHealth()));
        //FriendsCraft.LOGGER.info(String.valueOf(friend.getMaxHealth()));

        this.renderFriendHeat(context,friend.getHeat(),friend.getMaxHeat(), centerX, centerY);
    }

    // Heart states
    private enum HeartState {
        EMPTY, HALF, FULL
    }

    private void renderFriendHealth(DrawContext context, int health, int maxHealth, int centerX, int centerY) {
        // Calculate the health per heart
        int healthPerHeart = maxHealth / 10;

        for (int i = 0; i < maxHealth / 2; i++) {
            HeartState heartState = getHeartState(i, health, healthPerHeart);
            renderHeart(context, heartState, i, false, centerX, centerY);
        }
    }

    private void renderFriendHeat(DrawContext context, int health, int maxHealth, int centerX, int centerY) {
        // Calculate the health per heart
        int healthPerHeart = maxHealth / 10;

        for (int i = 0; i < maxHealth / 2; i++) {
            HeartState heartState = getHeartState(i, health, healthPerHeart);
            renderHeart(context, heartState, i, true, centerX, centerY);
        }
    }



    private HeartState getHeartState(int heartIndex, int health, int healthPerHeart) {
        int heartHealthValue = heartIndex * healthPerHeart;

        if (health >= heartHealthValue + healthPerHeart) {
            return HeartState.FULL;
        } else if (health >= heartHealthValue + (healthPerHeart / 2)) {
            return HeartState.HALF;
        } else {
            return HeartState.EMPTY;
        }
    }

    private void renderHeart(DrawContext context, HeartState heartState, int position, boolean HeatBar, int centerX, int centerY) {
        int x = centerX;
        int y = centerY;
        int v;
        v= 166;

        y+=0;
        x+=position*8;

        if (HeatBar) {
            v+=9;
            x+=90;
        }
        //offsets
        x+=2;
        y-=10;
        switch (heartState) {
            case EMPTY:
                context.drawTexture(CONTAINER, x, y, 0, v, 9, 9);
                break;
            case HALF:
                context.drawTexture(CONTAINER, x, y, 9, v, 9, 9);
                break;
            case FULL:
                context.drawTexture(CONTAINER, x, y, 18, v, 9, 9);
                break;
        }
    }





}
