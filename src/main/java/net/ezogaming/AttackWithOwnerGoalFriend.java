//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.ezogaming;

import java.util.EnumSet;
import java.util.UUID;

import net.ezogaming.entity.FriendEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.Goal.Control;
import net.minecraft.entity.ai.goal.TrackTargetGoal;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.server.world.ServerWorld;

public class AttackWithOwnerGoalFriend extends TrackTargetGoal {
    private final FriendEntity tameable;
    private LivingEntity attacking;
    private int lastAttackTime;

    public AttackWithOwnerGoalFriend(FriendEntity tameable) {
        super(tameable, false);
        this.tameable = tameable;
        this.setControls(EnumSet.of(Control.TARGET));
    }

    // Method to retrieve LivingEntity from a UUID in a specific ServerWorld
    public LivingEntity getLivingEntityFromUUID(ServerWorld world, UUID uuid) {
        Entity entity = world.getEntity(uuid);

        if (entity instanceof LivingEntity) {
            return (LivingEntity) entity;
        } else {
            // The entity with the given UUID is not alive or doesn't exist
            return null;
        }
    }

    public boolean canStart() {
        if (this.tameable.isTamed() && !this.tameable.isSitting()) {
            LivingEntity livingEntity = getLivingEntityFromUUID((ServerWorld) this.tameable.getWorld(), this.tameable.getOwner());
            if (livingEntity == null) {
                return false;
            } else {
                this.attacking = livingEntity.getAttacking();
                int i = livingEntity.getLastAttackTime();
                return i != this.lastAttackTime && this.canTrack(this.attacking, TargetPredicate.DEFAULT) && this.tameable.canAttackWithOwner(this.attacking, livingEntity);
            }
        } else {
            return false;
        }
    }

    public void start() {
        this.mob.setTarget(this.attacking);
        LivingEntity livingEntity = getLivingEntityFromUUID((ServerWorld) this.tameable.getWorld(), this.tameable.getOwner());
        if (livingEntity != null) {
            this.lastAttackTime = livingEntity.getLastAttackTime();
        }

        super.start();
    }
}
