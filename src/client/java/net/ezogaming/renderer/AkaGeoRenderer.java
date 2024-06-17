package net.ezogaming.renderer;

import net.ezogaming.entity.AkaEntity;
import net.ezogaming.model.AkaGeoModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class AkaGeoRenderer extends GeoEntityRenderer<AkaEntity> {
    public AkaGeoRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new AkaGeoModel());
        withScale(0.9375F,0.9375F);
    }

    @Override
    public Identifier getTexture(AkaEntity entity) {
        return new Identifier("kemonofriends", "textures/friends/aka.png");
    }

    protected void scale(MatrixStack matrixStack) {
        matrixStack.scale(0.9375F, 0.9375F, 0.9375F);
    }
}