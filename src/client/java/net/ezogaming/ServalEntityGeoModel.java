package net.ezogaming;

import net.ezogaming.entity.ServalEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class ServalEntityGeoModel extends GeoModel<ServalEntity> {
    private static final Identifier MODEL = new Identifier("geo/serval.geo.json");
    private static final Identifier ANIM = new Identifier("animations/serval.animation.json");
    private static final Identifier TEX = new Identifier("textures/friends/serval.png");

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
