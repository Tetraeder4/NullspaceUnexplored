package net.tetraeder4.nullspaceunexplored;

import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.Identifier;

import net.tetraeder4.nullspaceunexplored.block.ModBlocks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NullspaceUnexplored implements ModInitializer {
	public static final String MOD_ID = "nullspaceunexplored";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModBlocks.registerModBlocks();
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
