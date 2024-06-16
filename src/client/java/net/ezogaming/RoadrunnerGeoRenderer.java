package net.ezogaming;

import net.ezogaming.entity.RoadrunnerEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class RoadrunnerGeoRenderer extends GeoEntityRenderer<RoadrunnerEntity> {
    public RoadrunnerGeoRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new RoadrunnerGeoModel());
        withScale(0.9375F,0.9375F);
    }

    @Override
    public Identifier getTexture(RoadrunnerEntity entity) {
        return new Identifier("kemonofriends", "textures/friends/roadrunner.png");
    }

    protected void scale(MatrixStack matrixStack) {
        matrixStack.scale(0.9375F, 0.9375F, 0.9375F);
    }
}