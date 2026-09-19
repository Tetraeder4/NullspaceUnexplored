package net.tetraeder4.nullspaceunexplored.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
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
        add(ModBlocks.BLACK_MOLD_1, createMultipleDrops(ModBlocks.BLACK_MOLD_1, ModItems.MYSTERIOUS_FUNGUS_SPORES, 0, 0));
        add(ModBlocks.BLACK_MOLD_2, createMultipleDrops(ModBlocks.BLACK_MOLD_2, ModItems.MYSTERIOUS_FUNGUS_SPORES, 0, 0));
        add(ModBlocks.BLACK_MOLD_3, createMultipleDrops(ModBlocks.BLACK_MOLD_3, ModItems.MYSTERIOUS_FUNGUS_SPORES, 0, 1));
        add(ModBlocks.BLACK_MOLD_4, createMultipleDrops(ModBlocks.BLACK_MOLD_4, ModItems.MYSTERIOUS_FUNGUS_SPORES, 0, 2));
        add(ModBlocks.BLACK_MOLD_5, createMultipleDrops(ModBlocks.BLACK_MOLD_5, ModItems.MYSTERIOUS_FUNGUS_SPORES, 0, 3));

        //slabs
        add(ModBlocks.SOGGY_CARPET_SLAB, this::createSlabItemTable);
        add(ModBlocks.IRON_GRATE_SLAB, this::createSlabItemTable);
        add(ModBlocks.CARDBOARD_SLAB, this::createSlabItemTable);

        //crops
        this.add(ModBlocks.MYSTERIOUS_FUNGUS_CROP, this.createCustomCropDrops(ModBlocks.MYSTERIOUS_FUNGUS_CROP, ModItems.MYSTERIOUS_FUNGUS, ModItems.MYSTERIOUS_FUNGUS_SPORES,
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.MYSTERIOUS_FUNGUS_CROP)
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(MysteriousFungusCropBlock.AGE, MysteriousFungusCropBlock.MAX_AGE)),
        2.0f, 5.0f));
    }

    public LootTable.Builder createCustomCropDrops(final Block original, final Item cropDrop, final Item seedDrop, final LootItemCondition.Builder isMaxAge, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return (LootTable.Builder)this.applyExplosionDecay(original, LootTable.lootTable().withPool(LootPool.lootPool().add(((LootPoolSingletonContainer.Builder<?>)LootItem.lootTableItem(cropDrop).apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops))).when(isMaxAge)).otherwise(LootItem.lootTableItem(seedDrop)))).withPool(LootPool.lootPool().when(isMaxAge).add(LootItem.lootTableItem(seedDrop).apply(ApplyBonusCount.addBonusBinomialDistributionCount(enchantments.getOrThrow(Enchantments.FORTUNE), 0.5714286F, 3)))));
    }

    public LootTable.Builder createMultipleDrops(final Block block, Item item, float minDrops, float maxDrops) {
        return this.createSilkTouchDispatchTable(block, this.applyExplosionDecay
                (block, LootItem.lootTableItem(item)
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))));
    }
}