package net.ezogaming.renderer;

import net.ezogaming.entity.FennecEntity;
import net.ezogaming.model.EzoGeoModel;
import net.ezogaming.model.FennecGeoModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class FennecGeoRenderer extends GeoEntityRenderer<FennecEntity> {
    public FennecGeoRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new FennecGeoModel());
        withScale(0.9375F,0.9375F);
    }

    @Override
    public Identifier getTexture(FennecEntity entity) {
        return new Identifier("kemonofriends", "textures/friends/fennec.png");
    }

    protected void scale(MatrixStack matrixStack) {
        matrixStack.scale(0.9375F, 0.9375F, 0.9375F);
    }
}