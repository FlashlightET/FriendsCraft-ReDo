package net.ezogaming;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

public class ServalEntityModel extends EntityModel<ServalEntity> {
    public static final EntityModelLayer LAYER_LOCATION = new EntityModelLayer(new Identifier("kemonofriends", "serval"), "main");
    private final ModelPart Head;
    private final ModelPart Body;
    private final ModelPart RightArm;
    private final ModelPart LeftArm;
    private final ModelPart RightLeg;
    private final ModelPart LeftLeg;

    public ServalEntityModel(ModelPart root) {
        this.Head = root.getChild("Head");
        this.Body = root.getChild("Body");
        this.RightArm = root.getChild("RightArm");
        this.LeftArm = root.getChild("LeftArm");
        this.RightLeg = root.getChild("RightLeg");
        this.LeftLeg = root.getChild("LeftLeg");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();

        ModelPartData Head = modelPartData.addChild("Head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -6.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F))
                .uv(32, 0).cuboid(-4.0F, -6.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.5F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData RightEar = Head.addChild("RightEar", ModelPartBuilder.create().uv(46, 56).cuboid(-1.7F, -5.5F, -0.5F, 3.0F, 7.0F, 1.0F, new Dilation(0.0F))
                .uv(46, 54).cuboid(-0.7F, -6.5F, -0.5F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.5F, -6.5F, -0.5F));

        ModelPartData LeftEar = Head.addChild("LeftEar", ModelPartBuilder.create().uv(46, 56).mirrored().cuboid(-1.3F, -5.5F, -0.5F, 3.0F, 7.0F, 1.0F, new Dilation(0.0F)).mirrored(false)
                .uv(46, 54).mirrored().cuboid(-1.3F, -6.5F, -0.5F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(1.5F, -6.5F, -0.5F));

        ModelPartData Body = modelPartData.addChild("Body", ModelPartBuilder.create().uv(16, 16).cuboid(-4.0F, 2.0F, -2.0F, 8.0F, 11.0F, 4.0F, new Dilation(0.0F))
                .uv(0, 61).cuboid(-1.0F, 2.0F, -3.0F, 2.0F, 2.0F, 1.0F, new Dilation(-0.15F))
                .uv(0, 57).cuboid(0.6F, 1.5F, -3.0F, 2.0F, 3.0F, 1.0F, new Dilation(0.0F))
                .uv(0, 57).mirrored().cuboid(-2.6F, 1.5F, -3.0F, 2.0F, 3.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData rearbowL_r1 = Body.addChild("rearbowL_r1", ModelPartBuilder.create().uv(38, 44).mirrored().cuboid(-4.75F, -1.0F, -1.0F, 4.0F, 3.0F, 1.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(0.0F, 10.0F, 2.5F, 2.6229F, -0.0434F, 3.0659F));

        ModelPartData rearbowR_r1 = Body.addChild("rearbowR_r1", ModelPartBuilder.create().uv(38, 44).cuboid(0.75F, -1.0F, -1.0F, 4.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 10.0F, 2.5F, 2.6229F, 0.0434F, -3.0659F));

        ModelPartData rearbowC_r1 = Body.addChild("rearbowC_r1", ModelPartBuilder.create().uv(24, 44).cuboid(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new Dilation(-0.15F)), ModelTransform.of(0.0F, 10.0F, 2.5F, 2.618F, 0.0F, 3.1416F));

        ModelPartData skirt = Body.addChild("skirt", ModelPartBuilder.create().uv(0, 32).cuboid(-5.0F, 0.0F, -3.0F, 10.0F, 5.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 10.0F, 0.0F));

        ModelPartData Tail = Body.addChild("Tail", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 11.4462F, 2.1029F));

        ModelPartData Tail_r1 = Tail.addChild("Tail_r1", ModelPartBuilder.create().uv(23, 39).cuboid(-1.0F, -1.5F, -4.3029F, 3.0F, 3.0F, 9.0F, new Dilation(0.0F)), ModelTransform.of(-0.5F, 3.0F, 2.0F, -0.9163F, 0.0F, 0.0F));

        ModelPartData RightArm = modelPartData.addChild("RightArm", ModelPartBuilder.create().uv(40, 16).cuboid(-2.0F, 0.0F, -2.0F, 3.0F, 11.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.0F, 2.0F, 0.0F));

        ModelPartData LeftArm = modelPartData.addChild("LeftArm", ModelPartBuilder.create().uv(40, 16).mirrored(true).cuboid(-1.0F, 0.0F, -2.0F, 3.0F, 11.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(5.0F, 2.0F, 0.0F));

        ModelPartData RightLeg = modelPartData.addChild("RightLeg", ModelPartBuilder.create().uv(0, 16).cuboid(-2.0F, 1.0F, -2.0F, 4.0F, 11.0F, 4.0F, new Dilation(0.0F))
                .uv(16, 56).cuboid(-2.2F, 8.0F, -2.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.2F)), ModelTransform.pivot(-1.9F, 12.0F, 0.0F));

        ModelPartData LeftLeg = modelPartData.addChild("LeftLeg", ModelPartBuilder.create().uv(0, 16).mirrored(true).cuboid(-2.0F, 1.0F, -2.0F, 4.0F, 11.0F, 4.0F, new Dilation(0.0F)).mirrored(false)
                .uv(16, 56).mirrored().cuboid(-2.0F, 8.0F, -2.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.2F)).mirrored(false), ModelTransform.pivot(1.9F, 12.0F, 0.0F));

        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void setAngles(ServalEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

    }

    @Override
    public void render(MatrixStack matrixStack, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {

        Head.render(matrixStack, vertices, light, overlay, red, green, blue, alpha);
        Body.render(matrixStack, vertices, light, overlay, red, green, blue, alpha);
        RightArm.render(matrixStack, vertices, light, overlay, red, green, blue, alpha);
        LeftArm.render(matrixStack, vertices, light, overlay, red, green, blue, alpha);
        RightLeg.render(matrixStack, vertices, light, overlay, red, green, blue, alpha);
        LeftLeg.render(matrixStack, vertices, light, overlay, red, green, blue, alpha);
    }
}
