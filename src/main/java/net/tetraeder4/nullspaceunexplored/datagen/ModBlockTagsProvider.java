package net.tetraeder4.nullspaceunexplored.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;
import net.tetraeder4.nullspaceunexplored.block.ModBlocks;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends FabricTagsProvider.BlockTagsProvider {
    public ModBlockTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider registries) {
        valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.BACKROOMS_WALL_BLOCK)
                .add(ModBlocks.DRYWALL_STAIRS)
                .add(ModBlocks.DRYWALL_SLAB)
                .add(ModBlocks.CEILING_SUPPORT)
                .add(ModBlocks.REINFORCED_BRICK_BLOCK)
                .add(ModBlocks.IRON_GRATE);

        valueLookupBuilder(BlockTags.MINEABLE_WITH_HOE)
                .add(ModBlocks.BACKROOMS_CARPET_BLOCK)
                .add(ModBlocks.SOGGY_CARPET_STAIRS)
                .add(ModBlocks.SOGGY_CARPET_SLAB);

        valueLookupBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.BACKROOMS_WALL_BLOCK)
                .add(ModBlocks.DRYWALL_STAIRS)
                .add(ModBlocks.DRYWALL_SLAB)
                .add(ModBlocks.DRYWALL_WALL)
                .add(ModBlocks.REINFORCED_BRICK_BLOCK)
                .add(ModBlocks.IRON_GRATE);

        valueLookupBuilder(BlockTags.STAIRS)
                .add(ModBlocks.DRYWALL_STAIRS)
                .add(ModBlocks.SOGGY_CARPET_STAIRS);

        valueLookupBuilder(BlockTags.SLABS)
                .add(ModBlocks.DRYWALL_SLAB)
                .add(ModBlocks.SOGGY_CARPET_SLAB);

        valueLookupBuilder(BlockTags.WALLS)
                .add(ModBlocks.DRYWALL_WALL);
    }
}
