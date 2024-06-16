package net.ezogaming.model;

import net.ezogaming.entity.EzoEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class EzoGeoModel extends GeoModel<EzoEntity> {
    private static final Identifier MODEL = new Identifier("kemonofriends","geo/ezo.geo.json");
    private static final Identifier ANIM = new Identifier("kemonofriends","animations/serval.animation.json");
    private static final Identifier TEX = new Identifier("kemonofriends","textures/friends/ezo.png");

    private static final Identifier ANIM_WALK = new Identifier("kemonofriends","animations/generic/generic_walk.json");
    private static final Identifier ANIM_SPRINT = new Identifier("kemonofriends","animations/generic/generic_sprint.json");
    private static final Identifier ANIM_IDLE = new Identifier("kemonofriends","animations/generic/generic_idle.json");
    private static final Identifier ANIM_IDLE_HAPPY = new Identifier("kemonofriends","animations/generic/generic_idle_happy.json");


    @Override
    public Identifier getModelResource(EzoEntity ezo) {
        return MODEL;
    }

    @Override
    public Identifier getAnimationResource(EzoEntity ezo) {
        return ANIM;
    }

    @Override
    public Identifier getTextureResource(EzoEntity ezo) {
        return TEX;
    }
}
