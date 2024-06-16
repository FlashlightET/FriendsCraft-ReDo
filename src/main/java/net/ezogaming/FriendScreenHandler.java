package net.ezogaming;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerListener;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.ezogaming.entity.FriendEntity;
import net.minecraft.util.Hand;

public class FriendScreenHandler extends ScreenHandler {


    private final FriendEntity friend;

    public static final Text TITLE = Text.translatable("container.kemonofriends.friend");

    protected FriendScreenHandler(int syncId, PlayerInventory inventory, PacketByteBuf packet) {
        this(syncId, inventory, (FriendEntity) inventory.player.getWorld().getEntityById(packet.readInt()));
    }

    public FriendScreenHandler(int syncId, PlayerInventory inventory, FriendEntity friend) {
        super(ModInventories.FRIEND, syncId);
        this.friend = friend;
        Inventory friendInventory = this.friend.getInventory();
        //main inventory
        int size = friendInventory.size();
        for (int slotY = 0; slotY < 3 && (slotY + 1) * 5 <= size; slotY++) {
            for (int slotX = 0; slotX < 5 && slotY * 5 + slotX + 1 <= size; slotX++) {
                this.addSlot(new Slot(friendInventory, slotY * 5 + slotX, 8 + slotX * 18, 16 + slotY * 18));
            }
        }
        //armor slots
        this.addSlot(new Slot(friendInventory, 16, 116, 8));
        this.addSlot(new Slot(friendInventory, 17, 116, 26));
        this.addSlot(new Slot(friendInventory, 18, 116, 44));
        this.addSlot(new Slot(friendInventory, 19, 116, 62));
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot) {
        return null;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.friend.getInventory().canPlayerUse(player) && this.friend.isAlive() && this.friend.distanceTo(player) < 8.0f;
    }

    public FriendEntity getFriend() {
        return this.friend;
    }





}
