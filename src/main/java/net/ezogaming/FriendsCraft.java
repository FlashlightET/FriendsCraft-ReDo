package net.ezogaming;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FriendsCraft implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("kemonofriends");

	//Setup Entities p1
	public static final EntityType<ServalEntity> SERVAL;

	static {
		SERVAL = Registry.register(
				Registries.ENTITY_TYPE,
				new Identifier("kemonofriends", "serval"),
				FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, ServalEntity::new)
						.dimensions(EntityDimensions.fixed(0.75f, 0.75f))
						.build()
		);
	}



	// Spawn eggs
	public static final Item SERVAL_SPAWN_EGG = new SpawnEggItem(SERVAL, 0xF5CA52, 0x7F5028, new FabricItemSettings());







	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		LOGGER.info("kf mod initializing");
		FabricDefaultAttributeRegistry.register(SERVAL, ServalEntity.createAttributes());
		//eggs
		Registry.register(Registries.ITEM, new Identifier("kemonofriends", "serval_spawn_egg"), SERVAL_SPAWN_EGG);
		LOGGER.info("kf mod initialized");
	}
}