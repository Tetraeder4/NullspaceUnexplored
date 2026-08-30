package net.tetraeder4.nullspaceunexplored.creativemodetab;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.tetraeder4.nullspaceunexplored.NullspaceUnexplored;
import net.tetraeder4.nullspaceunexplored.block.ModBlocks;
import net.tetraeder4.nullspaceunexplored.item.ModItems;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;


public class ModCreativeModeTabs {

    public static final CreativeModeTab BACKROOMS_ITEM_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(NullspaceUnexplored.MOD_ID, "backrooms_items"),
            FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModItems.DRYWALL_DEBRIS))
                    .title(Component.translatable("creativemodetab.nullspaceunexplored.backrooms_items"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.DRYWALL_DEBRIS);
                        output.accept(ModItems.REINFORCED_BRICK);
                    }).build());


    public static final CreativeModeTab BACKROOMS_BLOCK_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(NullspaceUnexplored.MOD_ID, "backrooms_blocks"),
            FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.BACKROOMS_WALL_BLOCK))
                    .title(Component.translatable("creativemodetab.nullspaceunexplored.backrooms_blocks"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModBlocks.BACKROOMS_WALL_BLOCK);
                        output.accept(ModBlocks.DRYWALL_STAIRS);
                        output.accept(ModBlocks.DRYWALL_SLAB);
                        output.accept(ModBlocks.DRYWALL_WALL);
                        output.accept(ModBlocks.BACKROOMS_CARPET_BLOCK);
                        output.accept(ModBlocks.SOGGY_CARPET_STAIRS);
                        output.accept(ModBlocks.SOGGY_CARPET_SLAB);
                        output.accept(ModBlocks.REINFORCED_BRICK_BLOCK);
                        output.accept(ModBlocks.IRON_GRATE);
                        output.accept(ModBlocks.BACKROOMS_LAMP_BLOCK);
                        output.accept(ModBlocks.CEILING_SUPPORT);
                        output.accept(ModBlocks.CEILING_PANEL);
                    }).build());


    public static void registerModCreativeModeTabs() {
        NullspaceUnexplored.LOGGER.info("Registering Creative Mode Tabs for " + NullspaceUnexplored.MOD_ID);
    }
}