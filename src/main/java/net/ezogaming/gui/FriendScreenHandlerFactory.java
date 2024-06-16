package net.ezogaming.gui;

import net.ezogaming.entity.FriendEntity;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

public class FriendScreenHandlerFactory implements ExtendedScreenHandlerFactory {
    private final FriendEntity friend;
    public FriendScreenHandlerFactory(FriendEntity friend) {
        this.friend = friend;
    }

    @Override
    public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf packet) {
        int id = this.friend.getId();

        packet.writeInt(id);
    }

    @Override
    public Text getDisplayName() {
        return this.friend.getName();
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inventory, PlayerEntity player) {
        return new FriendScreenHandler(syncId, inventory, this.friend);
    }
}
