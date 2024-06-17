package net.ezogaming.renderer;

import net.ezogaming.entity.SilverEntity;
import net.ezogaming.model.SilverGeoModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SilverGeoRenderer extends GeoEntityRenderer<SilverEntity> {
    public SilverGeoRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new SilverGeoModel());
        withScale(0.9375F,0.9375F);
    }

    @Override
    public Identifier getTexture(SilverEntity entity) {
        return new Identifier("kemonofriends", "textures/friends/silver.png");
    }

    protected void scale(MatrixStack matrixStack) {
        matrixStack.scale(0.9375F, 0.9375F, 0.9375F);
    }
}