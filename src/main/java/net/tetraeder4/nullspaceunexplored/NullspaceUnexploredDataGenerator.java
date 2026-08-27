package net.tetraeder4.nullspaceunexplored;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.tetraeder4.nullspaceunexplored.datagen.ModBlockLootTableProvider;
import net.tetraeder4.nullspaceunexplored.datagen.ModBlockTagsProvider;
import net.tetraeder4.nullspaceunexplored.datagen.ModModelProvider;
import net.tetraeder4.nullspaceunexplored.datagen.ModRecipeProvider;

public class NullspaceUnexploredDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		var pack = fabricDataGenerator.createPack();

		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModRecipeProvider::new);
		pack.addProvider(ModBlockTagsProvider::new);
		pack.addProvider(ModBlockLootTableProvider::new);
	}
}
