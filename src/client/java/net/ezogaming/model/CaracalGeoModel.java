package net.ezogaming.model;

import net.ezogaming.entity.CaracalEntity;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class CaracalGeoModel extends GeoModel<CaracalEntity> {
    private static final Identifier MODEL = new Identifier("kemonofriends","geo/caracal.geo.json");
    private static final Identifier ANIM = new Identifier("kemonofriends","animations/generic.animation.json");
    private static final Identifier TEX = new Identifier("kemonofriends","textures/friends/caracal.png");

    @Override
    public Identifier getModelResource(CaracalEntity toki) {
        return MODEL;
    }

    @Override
    public Identifier getAnimationResource(CaracalEntity toki) {
        return ANIM;
    }

    @Override
    public Identifier getTextureResource(CaracalEntity toki) {
        return TEX;
    }
}
