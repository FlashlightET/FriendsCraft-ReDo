package net.ezogaming.renderer;

import net.ezogaming.entity.AraiEntity;
import net.ezogaming.model.AraiGeoModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class AraiGeoRenderer extends GeoEntityRenderer<AraiEntity> {
    public AraiGeoRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new AraiGeoModel());
        withScale(0.9375F,0.9375F);
    }

    @Override
    public Identifier getTexture(AraiEntity entity) {
        return new Identifier("kemonofriends", "textures/friends/arai.png");
    }

    protected void scale(MatrixStack matrixStack) {
        matrixStack.scale(0.9375F, 0.9375F, 0.9375F);
    }
}