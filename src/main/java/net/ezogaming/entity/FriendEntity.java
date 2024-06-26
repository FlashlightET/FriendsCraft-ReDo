package net.ezogaming.entity;

import net.ezogaming.*;
import net.minecraft.util.math.BlockPos;
import net.ezogaming.goals.AttackWithOwnerGoalFriend;
import net.ezogaming.goals.FriendTemptGoal;
import net.ezogaming.goals.TrackOwnerAttackerGoalFriend;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.MobNavigation;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Box;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.registry.Registries;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.List;
import java.util.Random;
import java.util.UUID;


public class FriendEntity extends PathAwareEntity implements InventoryOwner, GeoEntity {
    private boolean IS_EATING;
    private UUID OWNER;
    private int HEAT;
    private int LEVEL;
    private int XP;
    private boolean BEGGING=false;



    //private int currentPose=FRIEND_POSE_IDLE;


    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);



    //public static final Text NAME = Text.translatable("friend.name.generic");
    //Declare the friends inventory, consisting of 15 items, a weapon, and four armor pieces.
    private final SimpleInventory inventory = new SimpleInventory(20);
    private UUID angryAt;

    public FriendEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
        //FriendsCraft.LOGGER.info("Friend");
        MobNavigation groundNavigation = new MobNavigation(this, world);

        groundNavigation.setCanPathThroughDoors(true);
        groundNavigation.setCanEnterOpenDoors(true);

    }




    public static final RawAnimation WALK = RawAnimation.begin().thenLoop("move.walk");
    public static final RawAnimation IDLE = RawAnimation.begin().thenLoop("misc.idle");
    public static final RawAnimation BEG = RawAnimation.begin().thenPlay("beg");
    public static final RawAnimation SPRINT = RawAnimation.begin().thenLoop("move.sprint");
    public static final RawAnimation EAT = RawAnimation.begin().thenLoop("eat");

    protected void initGoals() {


        if (!this.isTamed()) { //Untamed goals
            this.targetSelector.add(7, new ActiveTargetGoal(this, HostileEntity.class, false));
            this.goalSelector.add(3, new MeleeAttackGoal(this,1.5, false));
            this.goalSelector.add(2, new WanderAroundGoal(this, 1.0));
            this.goalSelector.add(3, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));
            this.goalSelector.add(4, new LookAroundGoal(this));
            this.goalSelector.add(1, new FriendTemptGoal(this,1.0,false));
        } else {
            //Tamed Goals
            this.targetSelector.add(7, new ActiveTargetGoal(this, HostileEntity.class, false));
            this.goalSelector.add(3, new MeleeAttackGoal(this,1.5, false));
            this.goalSelector.add(2, new WanderAroundGoal(this, 1.0));
            this.goalSelector.add(3, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));
            this.goalSelector.add(4, new LookAroundGoal(this));
            this.targetSelector.add(1, new TrackOwnerAttackerGoalFriend(this));
            this.targetSelector.add(2, new AttackWithOwnerGoalFriend(this));

        }

    }


    public static boolean canSpawn(EntityType<FriendEntity> type, ServerWorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        Box spawnCheckBox = new Box(pos).expand(100); // Expands the box to 100 blocks in all directions
        List<FriendEntity> nearbyEntities = world.getEntitiesByClass(FriendEntity.class, spawnCheckBox, (entity) -> true);
        return nearbyEntities.isEmpty();
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
        Item item = itemStack.getItem();
        // Check if it's the client side
        if (!this.getWorld().isClient) {
            if (this.isTamed()) {
                if (item.isFood() && this.getHealth() < this.getMaxHealth()) {
                    if (!player.getAbilities().creativeMode) {
                        itemStack.decrement(1);
                    }

                    this.heal((float)item.getFoodComponent().getHunger());
                    this.setEating(true);
                    return ActionResult.SUCCESS;
                } else {
                    player.openHandledScreen(new FriendScreenHandlerFactory(this));
                }
            } else { // tame the friend if applicable
                this.BEGGING=true;
                if (isTamableItem(itemStack)) {
                    // eat the food
                    //ItemStack food = (player.getAbilities().creativeMode ? itemStack.copy() : itemStack).split(1);
                    this.setOwnerFromPlayer(player);
                    this.goalSelector.getGoals().clear();
                    this.targetSelector.getGoals().clear();
                    this.spawnParticles();
                    initGoals(); // re-initialize goals for tamed friend
                    return ActionResult.success(this.getWorld().isClient);
                }
            }
            // This code block will only execute on the server side
            // You can perform server-side actions here if needed

        } else {
            // This code block will execute only on the client side
            return ActionResult.success(this.getWorld().isClient);
        }



        return ActionResult.success(this.getWorld().isClient);
    }

    private boolean isFood(ItemStack itemStack) {
        return true;
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
        nbt.putInt("XP", this.getXP());
        nbt.putInt("Level", this.getLevel());
        nbt.putBoolean("Eating", this.getEating());

    }

    public void setOwner(UUID owner) {
        FriendsCraft.LOGGER.info("Owner is now...");
        FriendsCraft.LOGGER.info(String.valueOf(owner));
        this.OWNER = owner;
    }

    public void setOwnerFromPlayer(PlayerEntity owner) {
        this.setOwner(owner.getUuid());
    }


    public void setEating(Boolean bool) {
        this.IS_EATING = bool;
    }

    @Override
    public boolean cannotDespawn() {
        return true;
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

        if (nbt.contains("Eating")) {
            IS_EATING = nbt.getBoolean("Eating");
        }

        if (nbt.contains("Level")) {
            LEVEL = nbt.getInt("Level");
        }

        if (nbt.contains("XP")) {
            XP = nbt.getInt("XP");
        }

        this.setHeat(nbt.getInt("Heat"));
        this.goalSelector.getGoals().clear();
        this.targetSelector.getGoals().clear();
        this.initGoals();

    }



    public int getHeat() {
        return this.HEAT;
    }

    public int getLevel() {
        return this.LEVEL;
    }

    public int getXP() {
        return this.XP;
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
        controllers.add(new AnimationController<>(this, "eat", 5, this::Anim));


    }


    protected <E extends FriendEntity> PlayState Anim(final AnimationState<E> event) {

        double currentX = this.getX();
        double currentY = this.getY();
        double currentZ = this.getZ();

        double deltaX = currentX - this.prevX;
        double deltaY = currentY - this.prevY;
        double deltaZ = currentZ - this.prevZ;

        double speed = Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);

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
            if (this.getEating()) {
                event.getController().setAnimation(EAT);
            } else if(doesPlayerHaveJapariBun()){
                event.getController().setAnimation(BEG);

            } else {
                event.getController().setAnimation(IDLE);
            }

        }

        return PlayState.CONTINUE;
    }

    private boolean getEating() {
        return this.IS_EATING;
    }

    private boolean canPlayRunAnim() {
        return true;
    }

    private boolean canPlayWalkAnim() {
        return true;
    }

    @Nullable
    public UUID getAngryAt() {
        return this.angryAt;
    }

    public void setAngryAt(@Nullable UUID angryAt) {
        this.angryAt = angryAt;
    }

    private boolean doesPlayerHaveJapariBun() {
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


    public boolean isSitting() {
        return true;
    }

    public boolean canAttackWithOwner(LivingEntity target, LivingEntity owner) {
        if (!(target instanceof CreeperEntity) && !(target instanceof GhastEntity)) {
            if (target instanceof PlayerEntity && owner instanceof PlayerEntity && !((PlayerEntity)owner).shouldDamagePlayer((PlayerEntity)target)) {
                return false;
            } else if (target instanceof AbstractHorseEntity && ((AbstractHorseEntity)target).isTame()) {
                return false;
            } else {
                return !(target instanceof TameableEntity) || !((TameableEntity)target).isTamed();
            }
        } else {
            return false;
        }
    }
}