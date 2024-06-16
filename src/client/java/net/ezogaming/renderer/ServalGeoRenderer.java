package net.ezogaming.renderer;

import net.ezogaming.entity.ServalEntity;
import net.ezogaming.model.ServalGeoModel;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.renderer.GeoEntityRenderer;


public class ServalGeoRenderer extends GeoEntityRenderer<ServalEntity> {
    public ServalGeoRenderer(EntityRendererFactory.Context renderManager)  {
        super(renderManager, new ServalGeoModel());
        withScale(0.9375F,0.9375F);
    }


    @Override
    public Identifier getTexture(ServalEntity entity) {
        return new Identifier("kemonofriends", "textures/friends/serval.png");
    }

    protected void scale(MatrixStack matrixStack) {
        matrixStack.scale(0.9375F, 0.9375F, 0.9375F);
    }


}