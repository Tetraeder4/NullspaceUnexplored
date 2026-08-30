package net.tetraeder4.nullspaceunexplored.datagen;

import com.sun.jna.platform.win32.Tlhelp32;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.tetraeder4.nullspaceunexplored.block.ModBlocks;
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
        dropSelf(ModBlocks.SOGGY_CARPET_STAIRS);
        dropSelf(ModBlocks.CEILING_PANEL);
        dropSelf(ModBlocks.BACKROOMS_LAMP_BLOCK);
        dropSelf(ModBlocks.CEILING_SUPPORT);
        dropSelf(ModBlocks.REINFORCED_BRICK_BLOCK);


        //slabs
        add(ModBlocks.SOGGY_CARPET_SLAB, this::createSlabItemTable);
    }

    public LootTable.Builder createMultipleDrops(final Block block, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        return this.createSilkTouchDispatchTable(block, (LootPoolEntryContainer.Builder)this.applyExplosionDecay
                (block, LootItem.lootTableItem(item)
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))));

                        // not needed in this case to stop infinite rescources
                        //.apply(ApplyBonusCount.addOreBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))));
    }
}
