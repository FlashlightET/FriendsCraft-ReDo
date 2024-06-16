package net.ezogaming;


import net.ezogaming.gui.FriendScreen;
import net.ezogaming.renderer.CaracalGeoRenderer;
import net.ezogaming.renderer.RoadrunnerGeoRenderer;
import net.ezogaming.renderer.ServalGeoRenderer;
import net.ezogaming.renderer.TokiGeoRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class FriendsCraftClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		EntityRendererRegistry.register(FriendsCraft.SERVAL, ServalGeoRenderer::new);
		EntityRendererRegistry.register(FriendsCraft.TOKI, TokiGeoRenderer::new);
		EntityRendererRegistry.register(FriendsCraft.CARACAL, CaracalGeoRenderer::new);
		EntityRendererRegistry.register(FriendsCraft.ROADRUNNER, RoadrunnerGeoRenderer::new);

		HandledScreens.register(ModInventories.FRIEND, FriendScreen::new);
	}


}