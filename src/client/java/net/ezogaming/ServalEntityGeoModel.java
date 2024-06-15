package net.ezogaming;

import net.ezogaming.entity.ServalEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class ServalEntityGeoModel extends GeoModel<ServalEntity> {
    private static final Identifier MODEL = new Identifier("kemonofriends","geo/serval.geo.json");
    private static final Identifier ANIM = new Identifier("kemonofriends","animations/serval.animation.json");
    private static final Identifier TEX = new Identifier("kemonofriends","textures/friends/serval.png");

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
