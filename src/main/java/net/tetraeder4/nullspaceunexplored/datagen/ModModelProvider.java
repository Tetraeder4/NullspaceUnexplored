package net.tetraeder4.nullspaceunexplored.datagen;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.data.models.model.TexturedModel;
import net.minecraft.client.renderer.block.dispatch.Variant;
import net.minecraft.util.random.WeightedList;
import net.tetraeder4.nullspaceunexplored.block.ModBlocks;
import net.tetraeder4.nullspaceunexplored.block.custom.BackroomsLampBlock;
import net.tetraeder4.nullspaceunexplored.item.ModItems;
import org.intellij.lang.annotations.Identifier;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        blockModelGenerators.createTrivialCube(ModBlocks.BACKROOMS_WALL_BLOCK);
        blockModelGenerators.createTrivialCube(ModBlocks.BACKROOMS_CARPET_BLOCK);

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
    }
}
