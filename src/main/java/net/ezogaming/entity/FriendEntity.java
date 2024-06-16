package net.ezogaming.entity;

import java.util.Optional;
import net.ezogaming.FriendScreenHandlerFactory;
import net.ezogaming.FriendsCraft;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.InventoryOwner;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.MobNavigation;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.ServerConfigHandler;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.minecraft.text.Text;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;


import java.util.UUID;

public class FriendEntity extends PathAwareEntity implements InventoryOwner {
    private UUID OWNER;



    public static final Text NAME = Text.translatable("friend.name.generic");
    //Declare the friends inventory, consisting of 15 items, a weapon, and four armor pieces.
    private final SimpleInventory inventory = new SimpleInventory(20);

    public FriendEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
        FriendsCraft.LOGGER.info("Friend");
        MobNavigation groundNavigation = new MobNavigation(this, world);

        groundNavigation.setCanPathThroughDoors(true);
        groundNavigation.setCanEnterOpenDoors(true);

    }

    protected void initGoals() {
        if (!this.isTamed()) { //Untamed goals
            this.goalSelector.add(1, new SwimGoal(this));
            this.goalSelector.add(2, new WanderAroundGoal(this, 1.0));
            this.goalSelector.add(3, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));
            this.goalSelector.add(4, new LookAroundGoal(this));
        } else {
            //Tamed Goals

        }

    }

    public static final TagKey<Item> FRIEND_TAMABLE_ITEMS =
            TagKey.of(Registries.ITEM.getKey(), new Identifier("kemonofriends", "friend_tamable_items"));

    public boolean isTamableItem(ItemStack stack) {
        return stack.isIn(FRIEND_TAMABLE_ITEMS);
    }

    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        // Check if it's the client side
        if (!this.getWorld().isClient) {
            if (this.isTamed()) {
                player.openHandledScreen(new FriendScreenHandlerFactory(this));
            } else { // tame the friend if applicable
                if (isTamableItem(itemStack)) {
                    // eat the food
                    ItemStack food = (player.getAbilities().creativeMode ? itemStack.copy() : itemStack).split(1);
                    this.setOwnerFromPlayer(player);
                    this.spawnParticles();
                    initGoals(); // re-initialize goals for tamed friend
                }
            }
            // This code block will only execute on the server side
            // You can perform server-side actions here if needed

        } else {
            // This code block will execute only on the client side


        }
        return ActionResult.success(this.getWorld().isClient);
    }

    private void spawnParticles() {
        final ParticleEffect particle = ParticleTypes.HEART;

        for (int i = 0; i < 7; i++) {
            double x = this.random.nextGaussian() * 0.02;
            double y = this.random.nextGaussian() * 0.02;
            double z = this.random.nextGaussian() * 0.02;

            ((ServerWorld) this.getWorld()).spawnParticles(particle, this.getParticleX(1.0), this.getRandomBodyY() + 0.5, this.getParticleZ(1.0), 0, x, y, z, 1.0D);
        }
    }

    public boolean isTamed() {
        return OWNER!=null;
    }

    public SimpleInventory getInventory() {
        return this.inventory;
    }

    @Override
    protected EntityNavigation createNavigation(World world) {
        MobNavigation navigation = new MobNavigation(this, world);
        navigation.setCanPathThroughDoors(true);
        navigation.setCanEnterOpenDoors(true);

        return navigation;
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        FriendsCraft.LOGGER.debug("writing nbt data");
        super.writeCustomDataToNbt(nbt);
        NbtList inventoryNbt = new NbtList();
        for (int i = 0; i < this.inventory.size(); i++) {
            ItemStack itemStack = this.inventory.getStack(i);

            if (!itemStack.isEmpty()) {
                NbtCompound nbtCompound = new NbtCompound();
                nbtCompound.putInt("Slot", i);
                itemStack.writeNbt(nbtCompound);
                inventoryNbt.add(nbtCompound);
            }
        }
        nbt.put("Inventory", inventoryNbt);
        if (this.getOwner() != null) {
            nbt.putUuid("Owner", this.OWNER);

        }
    }

    public void setOwner(UUID owner) {
        FriendsCraft.LOGGER.info("Owner is now...");
        FriendsCraft.LOGGER.info(String.valueOf(owner));
        this.OWNER = owner;
    }

    public void setOwnerFromPlayer(PlayerEntity owner) {
        this.setOwner(owner.getUuid());
    }

    public UUID getOwner() {
        return this.OWNER;
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);

        NbtList inventoryNbt = nbt.getList("Inventory", NbtElement.COMPOUND_TYPE);
        for (int i = 0; i < inventoryNbt.size(); i++) {
            NbtCompound nbtCompound = inventoryNbt.getCompound(i);
            this.inventory.setStack(nbtCompound.getInt("Slot"), ItemStack.fromNbt(nbtCompound));
        }

        if (nbt.contains("Owner")) {
            OWNER = nbt.getUuid("Owner");
        }

        this.initGoals();

    }



}