package net.ezogaming.model;

import net.ezogaming.entity.TokiEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class TokiGeoModel extends GeoModel<TokiEntity> {
    private static final Identifier MODEL = new Identifier("kemonofriends","geo/toki.geo.json");
    private static final Identifier ANIM = new Identifier("kemonofriends","animations/toki.animation.json");
    private static final Identifier TEX = new Identifier("kemonofriends","textures/friends/toki.png");

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
