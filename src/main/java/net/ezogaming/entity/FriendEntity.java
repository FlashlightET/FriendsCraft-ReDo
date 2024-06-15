package net.ezogaming.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.InventoryOwner;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.MobNavigation;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.text.Text;

public class FriendEntity extends PathAwareEntity implements InventoryOwner {
    //Declare the friends inventory, consisting of 15 items, a weapon, and four armor pieces.
    private final SimpleInventory inventory = new SimpleInventory(20);
    private final EntityNavigation groundNavigation;

    public FriendEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);

        this.groundNavigation = new MobNavigation(this, world);

        ((MobNavigation) this.groundNavigation).setCanPathThroughDoors(true);
        ((MobNavigation) this.groundNavigation).setCanEnterOpenDoors(true);
        this.initGoals();
    }

    protected void initGoals() {
        // Example AI goalss
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new WanderAroundGoal(this, 1.0));
        this.goalSelector.add(3, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));
        this.goalSelector.add(4, new LookAroundGoal(this));
        // Add more goals as needed for movement and other behaviors
    }

    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        // Check if it's the client side
        if (!this.getWorld().isClient) {
            // This code block will only execute on the server side
            // You can perform server-side actions here if needed
        } else {
            // This code block will execute only on the client side
            player.sendMessage(Text.of("i am a kemono friend"));
        }
        return ActionResult.success(this.getWorld().isClient);
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

}