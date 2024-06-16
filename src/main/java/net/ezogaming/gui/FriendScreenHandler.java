package net.ezogaming.gui;

import net.ezogaming.ModInventories;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.ezogaming.entity.FriendEntity;
import net.minecraft.util.Identifier;
import com.mojang.datafixers.util.Pair;
import org.jetbrains.annotations.Nullable;

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
        this.addSlot(this.new friendHelmetSlot(116, 8));
        this.addSlot(this.new friendChestplateSlot(116, 26));
        this.addSlot(this.new friendLeggingsSlot(116, 44));
        this.addSlot(this.new friendBootsSlot( 116, 62));
        //#this.addSlot(new Slot(friendInventory, 17, 116, 26));
        //#this.addSlot(new Slot(friendInventory, 18, 116, 44));
       // this.addSlot(new Slot(friendInventory, 19, 116, 62));

        // Player Inventory - rows 1-3
        for (int slotY = 0; slotY < 3; slotY++) {
            for (int slotX = 0; slotX < 9; slotX++) {
                this.addSlot(new Slot(inventory, 9 + slotY * 9 + slotX, 8 + slotX * 18, 84 + slotY * 18));
            }
        }

        // Player Inventory - hotbar
        for (int slotX = 0; slotX < 9; slotX++) {
            this.addSlot(new Slot(inventory, slotX, 8 + slotX * 18, 142));
        }

    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int index) {
        ItemStack itemStack = ItemStack.EMPTY;

        Slot slot = this.slots.get(index);
        if (slot.hasStack()) {
            ItemStack clickedStack = slot.getStack();
            itemStack = clickedStack.copy();
            int size = this.friend.getInventory().size();

            if (index >= 0 && index < 3 + size) {
                if (!this.insertItem(clickedStack, 3 + size, 39 + size, false)) return ItemStack.EMPTY;
            } else if (index >= 3 + size && index < 39 + size) {
                if (!this.insertItem(clickedStack, 1, 2, false)) {
                    if (!this.insertItem(clickedStack, 2, 3, false)) {
                        if (!this.insertItem(clickedStack, 3, 3 + size, false)) return ItemStack.EMPTY;
                    }
                }
            }

            if (clickedStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return itemStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.friend.getInventory().canPlayerUse(player) && this.friend.isAlive() && this.friend.distanceTo(player) < 8.0f;
    }

    public FriendEntity getFriend() {
        return this.friend;
    }

    private class friendHelmetSlot extends Slot {
        public friendHelmetSlot(int x, int y) {
            super(new SimpleInventory(1), 0, x, y);

            this.setStack(FriendScreenHandler.this.getFriend().getMainHandStack());
        }

        @Override
        public boolean canInsert(ItemStack itemStack) {
            return super.canInsert(itemStack) && MobEntity.getPreferredEquipmentSlot(itemStack) == EquipmentSlot.HEAD;
        }

        @Override
        public boolean canTakeItems(PlayerEntity player) {
            return !(player.isCreative() && EnchantmentHelper.hasBindingCurse(this.getStack())) && super.canTakeItems(player);
        }

        @Nullable
        @Override
        public Pair<Identifier, Identifier> getBackgroundSprite() {
            return Pair.of(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE, PlayerScreenHandler.BLOCK_ATLAS_TEXTURE);
        }
    }

    private class friendChestplateSlot extends Slot {
        public friendChestplateSlot(int x, int y) {
            super(new SimpleInventory(1), 0, x, y);

            this.setStack(FriendScreenHandler.this.getFriend().getMainHandStack());
        }

        @Override
        public boolean canInsert(ItemStack itemStack) {
            return super.canInsert(itemStack) && MobEntity.getPreferredEquipmentSlot(itemStack) == EquipmentSlot.CHEST;
        }

        @Override
        public boolean canTakeItems(PlayerEntity player) {
            return !(player.isCreative() && EnchantmentHelper.hasBindingCurse(this.getStack())) && super.canTakeItems(player);
        }

        @Nullable
        @Override
        public Pair<Identifier, Identifier> getBackgroundSprite() {
            return Pair.of(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE, PlayerScreenHandler.BLOCK_ATLAS_TEXTURE);
        }
    }

    private class friendLeggingsSlot extends Slot {
        public friendLeggingsSlot(int x, int y) {
            super(new SimpleInventory(1), 0, x, y);

            this.setStack(FriendScreenHandler.this.getFriend().getMainHandStack());
        }

        @Override
        public boolean canInsert(ItemStack itemStack) {
            return super.canInsert(itemStack) && MobEntity.getPreferredEquipmentSlot(itemStack) == EquipmentSlot.LEGS;
        }

        @Override
        public boolean canTakeItems(PlayerEntity player) {
            return !(player.isCreative() && EnchantmentHelper.hasBindingCurse(this.getStack())) && super.canTakeItems(player);
        }

        @Nullable
        @Override
        public Pair<Identifier, Identifier> getBackgroundSprite() {
            return Pair.of(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE, PlayerScreenHandler.BLOCK_ATLAS_TEXTURE);
        }
    }

    private class friendBootsSlot extends Slot {
        public friendBootsSlot(int x, int y) {
            super(new SimpleInventory(1), 0, x, y);

            this.setStack(FriendScreenHandler.this.getFriend().getMainHandStack());
        }

        @Override
        public boolean canInsert(ItemStack itemStack) {
            return super.canInsert(itemStack) && MobEntity.getPreferredEquipmentSlot(itemStack) == EquipmentSlot.FEET;
        }

        @Override
        public boolean canTakeItems(PlayerEntity player) {
            return !(player.isCreative() && EnchantmentHelper.hasBindingCurse(this.getStack())) && super.canTakeItems(player);
        }

        @Nullable
        @Override
        public Pair<Identifier, Identifier> getBackgroundSprite() {
            return Pair.of(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE, PlayerScreenHandler.BLOCK_ATLAS_TEXTURE);
        }
    }







}
