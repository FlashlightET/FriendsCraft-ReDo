package net.ezogaming.renderer;

import net.ezogaming.entity.EzoEntity;
import net.ezogaming.model.EzoGeoModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class EzoGeoRenderer extends GeoEntityRenderer<EzoEntity> {
    public EzoGeoRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new EzoGeoModel());
        withScale(0.9375F,0.9375F);
    }

    @Override
    public Identifier getTexture(EzoEntity entity) {
        return new Identifier("kemonofriends", "textures/friends/ezo.png");
    }

    protected void scale(MatrixStack matrixStack) {
        matrixStack.scale(0.9375F, 0.9375F, 0.9375F);
    }
}