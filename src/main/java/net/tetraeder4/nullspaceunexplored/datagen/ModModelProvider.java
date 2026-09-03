package net.tetraeder4.nullspaceunexplored.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.block.dispatch.Variant;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.resources.Identifier;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.block.Block;
import net.tetraeder4.nullspaceunexplored.block.ModBlocks;
import net.tetraeder4.nullspaceunexplored.block.custom.BackroomsLampBlock;
import net.tetraeder4.nullspaceunexplored.item.ModItems;

import static net.tetraeder4.nullspaceunexplored.NullspaceUnexplored.id;


public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    private static void createCustomCube(
            BlockModelGenerators generators,
            Block block,
            Identifier top,
            Identifier bottom,
            Identifier north,
            Identifier south,
            Identifier east,
            Identifier west
    ) {
        TextureMapping mapping = new TextureMapping()
                .put(TextureSlot.UP, new Material(top, false))
                .put(TextureSlot.DOWN, new Material(bottom, false))
                .put(TextureSlot.NORTH, new Material(north, false))
                .put(TextureSlot.SOUTH, new Material(south, false))
                .put(TextureSlot.EAST, new Material(east, false))
                .put(TextureSlot.WEST, new Material(west, false))
                .put(TextureSlot.PARTICLE, new Material(top, false));

        Identifier model = ModelTemplates.CUBE.create(
                block,
                mapping,
                generators.modelOutput
        );

        generators.blockStateOutput.accept(
                MultiVariantGenerator.dispatch(
                        block,
                        new MultiVariant(
                                WeightedList.<Variant>builder()
                                        .add(new Variant(model))
                                        .build()
                        )
                )
        );
    }

    private static void createCustomStairs(
            BlockModelGenerators generators,
            Block stairsBlock,
            Identifier top,
            Identifier bottom,
            Identifier north,
            Identifier south,
            Identifier east,
            Identifier west
    ) {
        TextureMapping mapping = new TextureMapping()
                .put(TextureSlot.TOP, new Material(top, false))
                .put(TextureSlot.BOTTOM, new Material(bottom, false))
                .put(TextureSlot.FRONT, new Material(north, false))
                .put(TextureSlot.BACK, new Material(south, false))
                .put(TextureSlot.SIDE, new Material(east, false))
                .put(TextureSlot.SIDE, new Material(west, false))
                .put(TextureSlot.PARTICLE, new Material(top, false));

        Identifier stairs = ModelTemplates.STAIRS_STRAIGHT.create(stairsBlock, mapping, generators.modelOutput);
        Identifier stairsInner = ModelTemplates.STAIRS_INNER.create(stairsBlock, mapping, generators.modelOutput);
        Identifier stairsOuter = ModelTemplates.STAIRS_OUTER.create(stairsBlock, mapping, generators.modelOutput);

        BlockModelGenerators.createStairs(
                stairsBlock,
                new MultiVariant(WeightedList.<Variant>builder().add(new Variant(stairs)).build()),
                new MultiVariant(WeightedList.<Variant>builder().add(new Variant(stairsInner)).build()),
                new MultiVariant(WeightedList.<Variant>builder().add(new Variant(stairsOuter)).build())
        );
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        blockModelGenerators.createTrivialCube(ModBlocks.REINFORCED_BRICK_BLOCK);
        blockModelGenerators.createNonTemplateModelBlock(ModBlocks.CEILING_SUPPORT);
        blockModelGenerators.createTrivialCube(ModBlocks.IRON_GRATE);

        createCustomCube(
                blockModelGenerators,
                ModBlocks.CARDBOARD_BLOCK,
                Identifier.fromNamespaceAndPath("nullspaceunexplored", "block/cardboard_block_top"),
                Identifier.fromNamespaceAndPath("nullspaceunexplored", "block/cardboard_block_top"),
                Identifier.fromNamespaceAndPath("nullspaceunexplored", "block/cardboard_block_front"),
                Identifier.fromNamespaceAndPath("nullspaceunexplored", "block/cardboard_block_front"),
                Identifier.fromNamespaceAndPath("nullspaceunexplored", "block/cardboard_block_side"),
                Identifier.fromNamespaceAndPath("nullspaceunexplored", "block/cardboard_block_side")
        );

        createCustomStairs(
                blockModelGenerators,
                ModBlocks.CARDBOARD_STAIRS,
                id("block/cardboard_block_top"),
                id("block/cardboard_block_top"),
                id("block/cardboard_block_front"),
                id("block/cardboard_block_front"),
                id("block/cardboard_block_side"),
                id("block/cardboard_block_side")
        );

        blockModelGenerators.family(ModBlocks.BACKROOMS_WALL_BLOCK)
                .stairs(ModBlocks.DRYWALL_STAIRS)
                .slab(ModBlocks.DRYWALL_SLAB)
                .wall(ModBlocks.DRYWALL_WALL);

        blockModelGenerators.family(ModBlocks.BACKROOMS_CARPET_BLOCK)
                .stairs(ModBlocks.SOGGY_CARPET_STAIRS)
                .slab(ModBlocks.SOGGY_CARPET_SLAB);

        blockModelGenerators.family(ModBlocks.IRON_GRATE)
                .stairs(ModBlocks.IRON_GRATE_STAIRS)
                .slab(ModBlocks.IRON_GRATE_SLAB);

        Identifier lampOffIdentifier = TexturedModel.CUBE.create(ModBlocks.BACKROOMS_LAMP_BLOCK, blockModelGenerators.modelOutput);
        Identifier lampOnIdentifier = blockModelGenerators.createSuffixedVariant(ModBlocks.BACKROOMS_LAMP_BLOCK, "_on", ModelTemplates.CUBE_ALL, TextureMapping::cube);

        blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.dispatch(ModBlocks.BACKROOMS_LAMP_BLOCK)
                .with(BlockModelGenerators.createBooleanModelDispatch(BackroomsLampBlock.CLICKED,
                        new MultiVariant(WeightedList.<Variant>builder().add(new Variant(lampOnIdentifier)).build()),
                        new MultiVariant(WeightedList.<Variant>builder().add(new Variant(lampOffIdentifier)).build()))));
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        itemModelGenerators.generateFlatItem(ModItems.DRYWALL_DEBRIS, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.REINFORCED_BRICK, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.LIGHT_PANEL, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.CARDBOARD, ModelTemplates.FLAT_ITEM);
        itemModelGenerators.generateFlatItem(ModItems.CEILING_PANEL, ModelTemplates.FLAT_ITEM);
    }
}
