package net.ezogaming.entity;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Optional;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.ezogaming.FriendScreenHandlerFactory;
import net.ezogaming.FriendTemptGoal;
import net.ezogaming.FriendsCraft;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.InventoryOwner;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.MobNavigation;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.ServerConfigHandler;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.text.Text;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;


import java.util.UUID;

import static net.ezogaming.FriendsCraft.*;
//import static software.bernie.geckolib.constant.DefaultAnimations.IDLE;
import static software.bernie.geckolib.constant.DefaultAnimations.WALK;

public class FriendEntity extends PathAwareEntity implements InventoryOwner, GeoEntity {
    private UUID OWNER;
    private int HEAT;
    private boolean BEGGING=false;

    //private int currentPose=FRIEND_POSE_IDLE;


    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);



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




    public static final RawAnimation WALK = RawAnimation.begin().thenLoop("move.walk");
    public static final RawAnimation IDLE = RawAnimation.begin().thenLoop("misc.idle");
    public static final RawAnimation BEG = RawAnimation.begin().thenPlay("beg");
    public static final RawAnimation SPRINT = RawAnimation.begin().thenLoop("move.sprint");

    protected void initGoals() {
        if (!this.isTamed()) { //Untamed goals
            this.goalSelector.add(1, new FriendTemptGoal(this,1.0,false));
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
        //AnimationController.setAnimation
        //this.currentPose=FRIEND_POSE_BEG;

        ItemStack itemStack = player.getStackInHand(hand);
        // Check if it's the client side
        if (!this.getWorld().isClient) {
            if (this.isTamed()) {
                player.openHandledScreen(new FriendScreenHandlerFactory(this));
            } else { // tame the friend if applicable
                this.BEGGING=true;
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
        nbt.putInt("Heat", this.getHeat());
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

        this.setHeat(nbt.getInt("Heat"));

        this.initGoals();

    }



    public int getHeat() {
        return this.HEAT;
    }

    public int getMaxHeat() {
        return 20;
    }

    public void setHeat(int health) {
        this.HEAT = health;
    }

    @Override
    public void registerControllers(final AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "move.walk", 5, this::Anim));
        controllers.add(new AnimationController<>(this, "misc.idle", 5, this::Anim));
        controllers.add(new AnimationController<>(this, "move.sprint", 15, this::Anim));
        controllers.add(new AnimationController<>(this, "beg", 5, this::Anim));


    }


    protected <E extends FriendEntity> PlayState Anim(final AnimationState<E> event) {

        double currentX = this.getX();
        double currentY = this.getY();
        double currentZ = this.getZ();

        double deltaX = currentX - this.prevX;
        double deltaY = currentY - this.prevY;
        double deltaZ = currentZ - this.prevZ;

        double speed = Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
        //hi
        if (event.isMoving()) {
            //FriendsCraft.LOGGER.info(String.valueOf(speed));
            if(speed <= 0.2F) {
                if(canPlayWalkAnim()){
                    event.getController().setAnimation(WALK);
                }
            }else {
                if(canPlayRunAnim()){
                    event.getController().setAnimation(SPRINT);
                }
            }
        } else {
            if(doesPlayerHasJapariBun()){
                event.getController().setAnimation(BEG);
            } else {
                event.getController().setAnimation(IDLE);
            }

        }

        return PlayState.CONTINUE;
    }

    private boolean canPlayRunAnim() {
        return true;
    }

    private boolean canPlayWalkAnim() {
        return true;
    }

    private boolean doesPlayerHasJapariBun() {
        for (PlayerEntity player : this.getWorld().getEntitiesByClass(PlayerEntity.class, this.getBoundingBox().expand(4), player -> true)) {
            ItemStack item = player.getMainHandStack();
            if (isTamableItem(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }



}