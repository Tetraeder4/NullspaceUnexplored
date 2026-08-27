package net.tetraeder4.nullspaceunexplored.creativemodetab;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.tetraeder4.nullspaceunexplored.NullspaceUnexplored;
import net.tetraeder4.nullspaceunexplored.block.ModBlocks;
//import net.tetraeder4.nullspaceunexplored.item.ModItems;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;


public class ModCreativeModeTabs {
    /*
        public static final CreativeModeTab FLUORITE_ITEM_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
                Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "fluorite_items"),
                FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModItems.FLUORITE))
                        .title(Component.translatable("creativemodetab.tutorialmod.fluorite_items"))
                        .displayItems((parameters, output) -> {
                            output.accept(ModItems.FLUORITE);
                            output.accept(ModItems.RAW_FLUORITE);
                        }).build());
    */

    public static final CreativeModeTab BACKROOMS_BLOCK_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(NullspaceUnexplored.MOD_ID, "backrooms_blocks"),
            FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.BACKROOMS_WALL_BLOCK))
                    .title(Component.translatable("creativemodetab.nullspaceunexplored.backrooms_blocks"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModBlocks.BACKROOMS_WALL_BLOCK);
                    }).build());


    public static void registerModCreativeModeTabs() {
        NullspaceUnexplored.LOGGER.info("Registering Creative Mode Tabs for " + NullspaceUnexplored.MOD_ID);
    }
}