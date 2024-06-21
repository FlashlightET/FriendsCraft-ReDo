package net.ezogaming;

import net.ezogaming.entity.*;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.MobEntity;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.*;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.World;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class FriendsCraft implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("kemonofriends");
	public static final Marker MARKER = MarkerManager.getMarker("kemonofriends");




	public static final TagKey<Biome> ARAI_BIOMES = TagKey.of(RegistryKeys.BIOME, new Identifier("kemonofriends", "arai_biomes"));
	public static final TagKey<Biome> AKA_BIOMES = TagKey.of(RegistryKeys.BIOME, new Identifier("kemonofriends", "aka_biomes"));
	public static final TagKey<Biome> CARACAL_BIOMES = TagKey.of(RegistryKeys.BIOME, new Identifier("kemonofriends", "caracal_biomes"));
	public static final TagKey<Biome> EZO_BIOMES = TagKey.of(RegistryKeys.BIOME, new Identifier("kemonofriends", "ezo_biomes"));
	public static final TagKey<Biome> FENNEC_BIOMES = TagKey.of(RegistryKeys.BIOME, new Identifier("kemonofriends", "fennec_biomes"));
	public static final TagKey<Biome> ROADRUNNER_BIOMES = TagKey.of(RegistryKeys.BIOME, new Identifier("kemonofriends", "roadrunner_biomes"));
	public static final TagKey<Biome> SERVAL_BIOMES = TagKey.of(RegistryKeys.BIOME, new Identifier("kemonofriends", "serval_biomes"));
	public static final TagKey<Biome> SILVER_BIOMES = TagKey.of(RegistryKeys.BIOME, new Identifier("kemonofriends", "silver_biomes"));
	public static final TagKey<Biome> TOKI_BIOMES = TagKey.of(RegistryKeys.BIOME, new Identifier("kemonofriends", "toki_biomes"));


	//Setup Entities

	public static final EntityType<ServalEntity> SERVAL;

	static {
		SERVAL = Registry.register(Registries.ENTITY_TYPE,
				new Identifier("kemonofriends", "serval"),
				FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, ServalEntity::new)
						.dimensions(EntityDimensions.fixed(0.7f, 1.8f))
						.build()
		);
	}

	public static final EntityType<TokiEntity> TOKI;

	static {
		TOKI = Registry.register(
				Registries.ENTITY_TYPE,
				new Identifier("kemonofriends", "toki"),
				FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, TokiEntity::new)
						.dimensions(EntityDimensions.fixed(0.7f, 1.8f))
						.build()
		);
	}

	public static final EntityType<CaracalEntity> CARACAL;

	static {
		CARACAL = Registry.register(
				Registries.ENTITY_TYPE,
				new Identifier("kemonofriends", "caracal"),
				FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, CaracalEntity::new)
						.dimensions(EntityDimensions.fixed(0.7f, 1.8f))
						.build()
		);
	}

	public static final EntityType<RoadrunnerEntity> ROADRUNNER;

	static {
		ROADRUNNER = Registry.register(
				Registries.ENTITY_TYPE,
				new Identifier("kemonofriends", "roadrunner"),
				FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, RoadrunnerEntity::new)
						.dimensions(EntityDimensions.fixed(0.7f, 1.8f))
						.build()
		);
	}

	public static final EntityType<EzoEntity> EZO;

	static {
		EZO = Registry.register(
				Registries.ENTITY_TYPE,
				new Identifier("kemonofriends", "ezo"),
				FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, EzoEntity::new)
						.dimensions(EntityDimensions.fixed(0.7f, 1.8f))
						.build()
		);
	}

	public static final EntityType<SilverEntity> SILVER;

	static {
		SILVER = Registry.register(
				Registries.ENTITY_TYPE,
				new Identifier("kemonofriends", "silver_fox"),
				FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, SilverEntity::new)
						.dimensions(EntityDimensions.fixed(0.7f, 1.8f))
						.build()
		);
	}

	public static final EntityType<AkaEntity> RED_FOX;

	static {
		RED_FOX = Registry.register(
				Registries.ENTITY_TYPE,
				new Identifier("kemonofriends", "red_fox"),
				FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, AkaEntity::new)
						.dimensions(EntityDimensions.fixed(0.7f, 1.8f))
						.build()
		);
	}

	public static final EntityType<FennecEntity> FENNEC;

	static {
		FENNEC = Registry.register(
				Registries.ENTITY_TYPE,
				new Identifier("kemonofriends", "fennec"),
				FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, FennecEntity::new)
						.dimensions(EntityDimensions.fixed(0.7f, 1.8f))
						.build()
		);
	}

	public static final EntityType<AraiEntity> ARAI;

	static {
		ARAI = Registry.register(
				Registries.ENTITY_TYPE,
				new Identifier("kemonofriends", "raccoon"),
				FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, AraiEntity::new)
						.dimensions(EntityDimensions.fixed(0.7f, 1.8f))
						.build()
		);
	}

	// Items

	// Japari buns
	public static final Item RED_JAPARI_BUN = new Item(new FabricItemSettings().food(FoodComponents.APPLE));
	public static final Item YELLOW_JAPARI_BUN = new Item(new FabricItemSettings().food(FoodComponents.APPLE));
	public static final Item BLUE_JAPARI_BUN = new Item(new FabricItemSettings().food(FoodComponents.APPLE));
	public static final Item ORANGE_JAPARI_BUN = new Item(new FabricItemSettings().food(FoodComponents.APPLE));

	// Spawn eggs
	public static final Item SERVAL_SPAWN_EGG = new SpawnEggItem(SERVAL, 0xF5CA52, 0x7F5028, new FabricItemSettings());
	public static final Item TOKI_SPAWN_EGG = new SpawnEggItem(TOKI, 0xFFFFFF, 0xDD5440, new FabricItemSettings());
	public static final Item CARACAL_SPAWN_EGG = new SpawnEggItem(CARACAL, 0xEA9330, 0xFFFFFF, new FabricItemSettings());
	public static final Item ROADRUNNER_SPAWN_EGG = new SpawnEggItem(ROADRUNNER, 0x467ECF, 0xEDDF52, new FabricItemSettings());
	public static final Item EZO_SPAWN_EGG = new SpawnEggItem(EZO, 0xE57C40, 0xFFFFFF, new FabricItemSettings());
	public static final Item SILVER_SPAWN_EGG = new SpawnEggItem(SILVER, 0x485DAF, 0x292F35, new FabricItemSettings());
	public static final Item RED_FOX_SPAWN_EGG = new SpawnEggItem(RED_FOX, 0xFFFFFF, 0xF08757, new FabricItemSettings());
	public static final Item FENNEC_SPAWN_EGG = new SpawnEggItem(FENNEC, 0xFFFFFF, 0xFFEA89, new FabricItemSettings());
	public static final Item ARAI_SPAWN_EGG = new SpawnEggItem(ARAI, 0x8D9194, 0x292f35, new FabricItemSettings());







	@Override
	public void onInitialize() {



		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		LOGGER.info("kf mod initializing");






		FabricDefaultAttributeRegistry.register(SERVAL, ServalEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(TOKI, TokiEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(CARACAL, CaracalEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(ROADRUNNER, RoadrunnerEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(EZO, EzoEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(SILVER, SilverEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(RED_FOX, AkaEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(FENNEC, FennecEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(ARAI, AraiEntity.createAttributes());

		//japaribuns
		Registry.register(Registries.ITEM, new Identifier("kemonofriends", "red_japari_bun"), RED_JAPARI_BUN);
		Registry.register(Registries.ITEM, new Identifier("kemonofriends", "yellow_japari_bun"), YELLOW_JAPARI_BUN);
		Registry.register(Registries.ITEM, new Identifier("kemonofriends", "blue_japari_bun"), BLUE_JAPARI_BUN);
		Registry.register(Registries.ITEM, new Identifier("kemonofriends", "orange_japari_bun"), ORANGE_JAPARI_BUN);





		ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(content -> {
			content.add(RED_JAPARI_BUN);
		});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(content -> {
			content.add(YELLOW_JAPARI_BUN);
		});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(content -> {
			content.add(BLUE_JAPARI_BUN);
		});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(content -> {
			content.add(ORANGE_JAPARI_BUN);
		});


		//eggs
		Registry.register(Registries.ITEM, new Identifier("kemonofriends", "serval_spawn_egg"), SERVAL_SPAWN_EGG);
		Registry.register(Registries.ITEM, new Identifier("kemonofriends", "toki_spawn_egg"), TOKI_SPAWN_EGG);
		Registry.register(Registries.ITEM, new Identifier("kemonofriends", "caracal_spawn_egg"), CARACAL_SPAWN_EGG);
		Registry.register(Registries.ITEM, new Identifier("kemonofriends", "roadrunner_spawn_egg"), ROADRUNNER_SPAWN_EGG);
		Registry.register(Registries.ITEM, new Identifier("kemonofriends", "ezo_spawn_egg"), EZO_SPAWN_EGG);
		Registry.register(Registries.ITEM, new Identifier("kemonofriends", "silver_fox_spawn_egg"), SILVER_SPAWN_EGG);
		Registry.register(Registries.ITEM, new Identifier("kemonofriends", "red_fox_spawn_egg"), RED_FOX_SPAWN_EGG);
		Registry.register(Registries.ITEM, new Identifier("kemonofriends", "fennec_spawn_egg"), FENNEC_SPAWN_EGG);
		Registry.register(Registries.ITEM, new Identifier("kemonofriends", "raccoon_spawn_egg"), ARAI_SPAWN_EGG);

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(content -> {
			content.add(SERVAL_SPAWN_EGG);
		});

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(content -> {
			content.add(TOKI_SPAWN_EGG);
		});

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(content -> {
			content.add(CARACAL_SPAWN_EGG);
		});

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(content -> {
			content.add(ROADRUNNER_SPAWN_EGG);
		});

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(content -> {
			content.add(EZO_SPAWN_EGG);
		});

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(content -> {
			content.add(SILVER_SPAWN_EGG);
		});

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(content -> {
			content.add(RED_FOX_SPAWN_EGG);
		});

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(content -> {
			content.add(RED_FOX_SPAWN_EGG);
		});

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(content -> {
			content.add(FENNEC_SPAWN_EGG);
		});

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(content -> {
			content.add(ARAI_SPAWN_EGG);
		});

		BiomeModifications.addSpawn(BiomeSelectors.tag(SERVAL_BIOMES),SpawnGroup.CREATURE,SERVAL,100, 1, 1);
		BiomeModifications.addSpawn(BiomeSelectors.tag(CARACAL_BIOMES),SpawnGroup.CREATURE,CARACAL,100, 1, 1);
		BiomeModifications.addSpawn(BiomeSelectors.tag(ROADRUNNER_BIOMES),SpawnGroup.CREATURE,ROADRUNNER,100, 1, 1);
		BiomeModifications.addSpawn(BiomeSelectors.tag(TOKI_BIOMES),SpawnGroup.CREATURE,TOKI,100, 1, 1);
		BiomeModifications.addSpawn(BiomeSelectors.tag(AKA_BIOMES),SpawnGroup.CREATURE,RED_FOX,100, 1, 1);
		BiomeModifications.addSpawn(BiomeSelectors.tag(SILVER_BIOMES),SpawnGroup.CREATURE,SILVER,100, 1, 1);
		BiomeModifications.addSpawn(BiomeSelectors.tag(EZO_BIOMES),SpawnGroup.CREATURE,EZO,100, 1, 1);
		BiomeModifications.addSpawn(BiomeSelectors.tag(FENNEC_BIOMES),SpawnGroup.CREATURE,FENNEC,100, 1, 1);
		BiomeModifications.addSpawn(BiomeSelectors.tag(ARAI_BIOMES),SpawnGroup.CREATURE,ARAI,100, 1, 1);

		Registry.register(Registries.SCREEN_HANDLER, FriendsCraft.identifier("friend_screen"), ModInventories.FRIEND);



		LOGGER.info("kf mod initialized");
	}

	public static Identifier identifier(String path) {
		return new Identifier("kemonofriends", path);
	}
}