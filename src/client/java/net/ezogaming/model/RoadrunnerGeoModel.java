package net.ezogaming.model;

import net.ezogaming.entity.RoadrunnerEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class RoadrunnerGeoModel extends GeoModel<RoadrunnerEntity> {
    private static final Identifier MODEL = new Identifier("kemonofriends","geo/roadrunner.geo.json");
    private static final Identifier ANIM = new Identifier("kemonofriends","animations/generic.animation.json");
    private static final Identifier TEX = new Identifier("kemonofriends","textures/friends/roadrunner.png");

    private static final Identifier ANIM_WALK = new Identifier("kemonofriends","animations/generic/generic_walk.json");
    private static final Identifier ANIM_SPRINT = new Identifier("kemonofriends","animations/generic/generic_sprint.json");
    private static final Identifier ANIM_IDLE = new Identifier("kemonofriends","animations/generic/generic_idle.json");
    private static final Identifier ANIM_IDLE_HAPPY = new Identifier("kemonofriends","animations/generic/generic_idle_happy.json");


    @Override
    public Identifier getModelResource(RoadrunnerEntity toki) {
        return MODEL;
    }

    @Override
    public Identifier getAnimationResource(RoadrunnerEntity toki) {
        return ANIM;
    }

    @Override
    public Identifier getTextureResource(RoadrunnerEntity toki) {
        return TEX;
    }
}
