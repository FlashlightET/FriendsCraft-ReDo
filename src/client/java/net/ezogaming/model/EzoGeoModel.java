package net.ezogaming.model;

import net.ezogaming.entity.EzoEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class EzoGeoModel extends GeoModel<EzoEntity> {
    private static final Identifier MODEL = new Identifier("kemonofriends","geo/ezo.geo.json");
    private static final Identifier ANIM = new Identifier("kemonofriends","animations/generic.animation.json");
    private static final Identifier TEX = new Identifier("kemonofriends","textures/friends/ezo.png");

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
