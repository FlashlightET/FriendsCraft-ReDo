package net.ezogaming.model;

import net.ezogaming.entity.TokiEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class TokiGeoModel extends GeoModel<TokiEntity> {
    private static final Identifier MODEL = new Identifier("kemonofriends","geo/toki.geo.json");
    private static final Identifier ANIM = new Identifier("kemonofriends","animations/generic.animation.json");
    private static final Identifier TEX = new Identifier("kemonofriends","textures/friends/toki.png");

    private static final Identifier ANIM_WALK = new Identifier("kemonofriends","animations/generic/generic_walk.json");
    private static final Identifier ANIM_SPRINT = new Identifier("kemonofriends","animations/generic/generic_sprint.json");
    private static final Identifier ANIM_IDLE = new Identifier("kemonofriends","animations/generic/generic_idle.json");
    private static final Identifier ANIM_IDLE_HAPPY = new Identifier("kemonofriends","animations/generic/generic_idle_happy.json");


    @Override
    public Identifier getModelResource(TokiEntity toki) {
        return MODEL;
    }

    @Override
    public Identifier getAnimationResource(TokiEntity toki) {
        return ANIM;
    }

    @Override
    public Identifier getTextureResource(TokiEntity toki) {
        return TEX;
    }
}
