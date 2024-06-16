package net.ezogaming;

import net.ezogaming.entity.CaracalEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class CaracalGeoRenderer extends GeoEntityRenderer<CaracalEntity> {
    public CaracalGeoRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new CaracalGeoModel());
        withScale(0.9375F,0.9375F);
    }

    @Override
    public Identifier getTexture(CaracalEntity entity) {
        return new Identifier("kemonofriends", "textures/friends/caracal.png");
    }

    protected void scale(MatrixStack matrixStack) {
        matrixStack.scale(0.9375F, 0.9375F, 0.9375F);
    }
}