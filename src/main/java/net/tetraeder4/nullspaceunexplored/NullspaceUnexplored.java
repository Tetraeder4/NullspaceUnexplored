package net.tetraeder4.nullspaceunexplored;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.resources.Identifier;

import net.tetraeder4.nullspaceunexplored.block.ModBlocks;
import net.tetraeder4.nullspaceunexplored.creativemodetab.ModCreativeModeTabs;
import net.tetraeder4.nullspaceunexplored.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NullspaceUnexplored implements ModInitializer {
	public static final String MOD_ID = "nullspaceunexplored";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModBlocks.registerModBlocks();
		ModItems.registerModItems();
		ModCreativeModeTabs.registerModCreativeModeTabs();

		FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.CARDBOARD_BLOCK, 100, 20);
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
