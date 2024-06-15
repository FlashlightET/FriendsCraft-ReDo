package net.ezogaming;

import net.ezogaming.entity.ServalEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class ServalRenderer extends MobEntityRenderer<ServalEntity, ServalModel> {
    public ServalRenderer(EntityRendererFactory.Context context) {
        super(context, new ServalModel(context.getPart(FriendsCraftClient.MODEL_SERVAL_LAYER)), 0.5f);
    }

    @Override
    public Identifier getTexture(ServalEntity entity) {
        return new Identifier("kemonofriends", "textures/friends/serval.png");
    }

    protected void scale(ServalEntity entity, MatrixStack matrixStack, float f) {
        matrixStack.scale(0.9375F, 0.9375F, 0.9375F);
    }
}