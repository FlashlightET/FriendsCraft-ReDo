package net.ezogaming;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import software.bernie.example.client.renderer.entity.BatRenderer;
import software.bernie.example.registry.EntityRegistry;

public class FriendsCraftClient implements ClientModInitializer {
	public static final EntityModelLayer MODEL_SERVAL_LAYER = new EntityModelLayer(new Identifier("kemonofriends", "serval"), "main");

	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		EntityRendererRegistry.register(FriendsCraft.SERVAL, ServalEntityGeoRenderer::new);
		EntityModelLayerRegistry.registerModelLayer(MODEL_SERVAL_LAYER, ServalEntityModel::getTexturedModelData);
	}


}