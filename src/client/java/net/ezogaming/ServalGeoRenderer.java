package net.ezogaming;

import net.ezogaming.entity.ServalEntity;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.GeodeCrackConfig;
import software.bernie.example.entity.BatEntity;
import software.bernie.geckolib.cache.object.BakedGeoModel;
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