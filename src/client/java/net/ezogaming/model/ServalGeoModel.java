package net.ezogaming.model;

import net.ezogaming.entity.ServalEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class ServalGeoModel extends GeoModel<ServalEntity> {
    private static final Identifier MODEL = new Identifier("kemonofriends","geo/serval.geo.json");
    private static final Identifier ANIM = new Identifier("kemonofriends","animations/generic.animation.json");
    private static final Identifier TEX = new Identifier("kemonofriends","textures/friends/serval.png");

    private static final Identifier ANIM_WALK = new Identifier("kemonofriends","animations/generic/generic_walk.json");
    private static final Identifier ANIM_SPRINT = new Identifier("kemonofriends","animations/generic/generic_sprint.json");
    private static final Identifier ANIM_IDLE = new Identifier("kemonofriends","animations/generic/generic_idle.json");
    private static final Identifier ANIM_IDLE_HAPPY = new Identifier("kemonofriends","animations/generic/generic_idle_happy.json");


    @Override
    public Identifier getModelResource(ServalEntity serval) {
        return MODEL;
    }

    @Override
    public Identifier getAnimationResource(ServalEntity serval) {
        return ANIM;
    }

    @Override
    public Identifier getTextureResource(ServalEntity serval) {
        return TEX;
    }
}
