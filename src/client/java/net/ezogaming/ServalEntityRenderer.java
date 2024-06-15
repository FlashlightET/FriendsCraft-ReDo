package net.ezogaming;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class ServalEntityRenderer extends MobEntityRenderer<ServalEntity, ServalEntityModel> {
    public ServalEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new ServalEntityModel(context.getPart(FriendsCraftClient.MODEL_SERVAL_LAYER)), 0.5f);
    }

    @Override
    public Identifier getTexture(ServalEntity entity) {
        return new Identifier("kemonofriends", "textures/friends/serval.png");
    }

    protected void scale(ServalEntity entity, MatrixStack matrixStack, float f) {
        matrixStack.scale(0.9375F, 0.9375F, 0.9375F);
    }
}