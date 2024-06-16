package net.ezogaming.model;

import net.ezogaming.entity.RoadrunnerEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class RoadrunnerGeoModel extends GeoModel<RoadrunnerEntity> {
    private static final Identifier MODEL = new Identifier("kemonofriends","geo/roadrunner.geo.json");
    private static final Identifier ANIM = new Identifier("kemonofriends","animations/roadrunner.animation.json");
    private static final Identifier TEX = new Identifier("kemonofriends","textures/friends/roadrunner.png");

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
