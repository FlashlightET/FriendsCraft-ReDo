package net.ezogaming;

import net.ezogaming.entity.TokiEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class TokiGeoRenderer extends GeoEntityRenderer<TokiEntity> {
    public TokiGeoRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new TokiGeoModel());
    }

    @Override
    public Identifier getTexture(TokiEntity entity) {
        return new Identifier("kemonofriends", "textures/friends/toki.png");
    }

    protected void scale(MatrixStack matrixStack) {
        matrixStack.scale(0.9375F, 0.9375F, 0.9375F);
    }
}