package net.ezogaming.model;

import net.ezogaming.entity.FennecEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class FennecGeoModel extends GeoModel<FennecEntity> {
    private static final Identifier MODEL = new Identifier("kemonofriends","geo/fennec.geo.json");
    private static final Identifier ANIM = new Identifier("kemonofriends","animations/generic.animation.json");
    private static final Identifier TEX = new Identifier("kemonofriends","textures/friends/fennec.png");

    @Override
    public Identifier getModelResource(FennecEntity ezo) {
        return MODEL;
    }

    @Override
    public Identifier getAnimationResource(FennecEntity ezo) {
        return ANIM;
    }

    @Override
    public Identifier getTextureResource(FennecEntity ezo) {
        return TEX;
    }
}
