package net.tetraeder4.nullspaceunexplored.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.tetraeder4.nullspaceunexplored.block.ModBlocks;
import net.tetraeder4.nullspaceunexplored.block.custom.MysteriousFungusCropBlock;
import net.tetraeder4.nullspaceunexplored.item.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModBlockLootTableProvider extends FabricBlockLootSubProvider {
    public ModBlockLootTableProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(packOutput, registriesFuture);
    }

    @Override
    public void generate() {
        add(ModBlocks.BACKROOMS_WALL_BLOCK, createMultipleDrops(ModBlocks.BACKROOMS_WALL_BLOCK, ModItems.DRYWALL_DEBRIS, 2.0f, 4.0f));
        add(ModBlocks.DRYWALL_STAIRS, createMultipleDrops(ModBlocks.DRYWALL_STAIRS, ModItems.DRYWALL_DEBRIS, 1.0f, 3.0f));
        add(ModBlocks.DRYWALL_SLAB, createMultipleDrops(ModBlocks.DRYWALL_SLAB, ModItems.DRYWALL_DEBRIS, .0f, 2.0f));
        add(ModBlocks.DRYWALL_WALL, createMultipleDrops(ModBlocks.DRYWALL_WALL, ModItems.DRYWALL_DEBRIS, 2.0f, 4.0f));

        //drop self blocks
        dropSelf(ModBlocks.BACKROOMS_CARPET_BLOCK);
        dropSelf(ModBlocks.MOLDY_CARPET_BLOCK);
        dropSelf(ModBlocks.SOGGY_CARPET_STAIRS);
        dropSelf(ModBlocks.CARDBOARD_BLOCK);
        dropSelf(ModBlocks.CARDBOARD_STAIRS);
        dropSelf(ModBlocks.BACKROOMS_LAMP_BLOCK);
        dropSelf(ModBlocks.REINFORCED_BRICK_BLOCK);
        dropSelf(ModBlocks.IRON_GRATE);
        dropSelf(ModBlocks.IRON_GRATE_STAIRS);
        dropSelf(ModBlocks.BLUE_BACKROOMS_CARPET_BLOCK);

        //black mold
        add(ModBlocks.BLACK_MOLD_3, createMultipleDrops(ModBlocks.BLACK_MOLD_3, ModItems.MYSTERIOUS_FUNGUS_SPORES, 0.0f, 1));
        add(ModBlocks.BLACK_MOLD_4, createMultipleDrops(ModBlocks.BLACK_MOLD_4, ModItems.MYSTERIOUS_FUNGUS_SPORES, 0.0f, 2));
        add(ModBlocks.BLACK_MOLD_5, createMultipleDrops(ModBlocks.BLACK_MOLD_5, ModItems.MYSTERIOUS_FUNGUS_SPORES, 0.0f, 3));

        //slabs
        add(ModBlocks.SOGGY_CARPET_SLAB, this::createSlabItemTable);
        add(ModBlocks.IRON_GRATE_SLAB, this::createSlabItemTable);
        add(ModBlocks.CARDBOARD_SLAB, this::createSlabItemTable);

        //crops
        this.add(ModBlocks.MYSTERIOUS_FUNGUS_CROP, this.createCropDrops(ModBlocks.MYSTERIOUS_FUNGUS_CROP, ModItems.MYSTERIOUS_FUNGUS, ModItems.MYSTERIOUS_FUNGUS_SPORES,
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.MYSTERIOUS_FUNGUS_CROP)
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(MysteriousFungusCropBlock.AGE, MysteriousFungusCropBlock.MAX_AGE))));
    }

    public LootTable.Builder createMultipleDrops(final Block block, Item item, float minDrops, float maxDrops) {
        return this.createSilkTouchDispatchTable(block, this.applyExplosionDecay
                (block, LootItem.lootTableItem(item)
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))));
    }
}
