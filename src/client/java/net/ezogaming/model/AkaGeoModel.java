package net.ezogaming.model;

import net.ezogaming.entity.AkaEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class AkaGeoModel extends GeoModel<AkaEntity> {
    private static final Identifier MODEL = new Identifier("kemonofriends","geo/aka.geo.json");
    private static final Identifier ANIM = new Identifier("kemonofriends","animations/generic.animation.json");
    private static final Identifier TEX = new Identifier("kemonofriends","textures/friends/aka.png");

    @Override
    public Identifier getModelResource(AkaEntity ezo) {
        return MODEL;
    }

    @Override
    public Identifier getAnimationResource(AkaEntity ezo) {
        return ANIM;
    }

    @Override
    public Identifier getTextureResource(AkaEntity ezo) {
        return TEX;
    }
}
