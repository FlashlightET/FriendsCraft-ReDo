package net.ezogaming.model;

import net.ezogaming.entity.SilverEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class SilverGeoModel extends GeoModel<SilverEntity> {
    private static final Identifier MODEL = new Identifier("kemonofriends","geo/silver.geo.json");
    private static final Identifier ANIM = new Identifier("kemonofriends","animations/generic.animation.json");
    private static final Identifier TEX = new Identifier("kemonofriends","textures/friends/silver.png");

    @Override
    public Identifier getModelResource(SilverEntity ezo) {
        return MODEL;
    }

    @Override
    public Identifier getAnimationResource(SilverEntity ezo) {
        return ANIM;
    }

    @Override
    public Identifier getTextureResource(SilverEntity ezo) {
        return TEX;
    }
}
