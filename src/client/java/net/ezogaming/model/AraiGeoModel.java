package net.ezogaming.model;

import net.ezogaming.entity.AraiEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class AraiGeoModel extends GeoModel<AraiEntity> {
    private static final Identifier MODEL = new Identifier("kemonofriends","geo/arai.geo.json");
    private static final Identifier ANIM = new Identifier("kemonofriends","animations/generic.animation.json");
    private static final Identifier TEX = new Identifier("kemonofriends","textures/friends/arai.png");

    @Override
    public Identifier getModelResource(AraiEntity ezo) {
        return MODEL;
    }

    @Override
    public Identifier getAnimationResource(AraiEntity ezo) {
        return ANIM;
    }

    @Override
    public Identifier getTextureResource(AraiEntity ezo) {
        return TEX;
    }
}
