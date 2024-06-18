//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.ezogaming.goals;

import java.util.EnumSet;
import java.util.UUID;

import net.ezogaming.entity.FriendEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.TrackTargetGoal;
import net.minecraft.server.world.ServerWorld;

public class TrackOwnerAttackerGoalFriend extends TrackTargetGoal {
    private final FriendEntity tameable;
    private LivingEntity attacker;
    private int lastAttackedTime;

    public TrackOwnerAttackerGoalFriend(FriendEntity tameable) {
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
                this.attacker = livingEntity.getAttacker();
                int i = livingEntity.getLastAttackedTime();
                return i != this.lastAttackedTime && this.canTrack(this.attacker, TargetPredicate.DEFAULT) && this.tameable.canAttackWithOwner(this.attacker, livingEntity);
            }
        } else {
            return false;
        }
    }

    public void start() {
        this.mob.setTarget(this.attacker);
        LivingEntity livingEntity = getLivingEntityFromUUID((ServerWorld) this.tameable.getWorld(), this.tameable.getOwner());
        if (livingEntity != null) {
            this.lastAttackedTime = livingEntity.getLastAttackedTime();
        }

        super.start();
    }
}
