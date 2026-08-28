package net.tetraeder4.nullspaceunexplored.block;

import net.minecraft.world.level.block.HayBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.tetraeder4.nullspaceunexplored.NullspaceUnexplored;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.tetraeder4.nullspaceunexplored.block.custom.BackroomsLampBlock;
import net.tetraeder4.nullspaceunexplored.block.custom.CeilingPanel;

import java.util.function.Function;

public class ModBlocks {
    //List Block, then add to creative inventory,generate model and add texture and lang file
    public static final Block BACKROOMS_WALL_BLOCK = registerBlock("backrooms_wall_block",
            properties -> new Block(properties.strength(8f)
                    .requiresCorrectToolForDrops().sound(SoundType.CHISELED_BOOKSHELF)));
    public static final Block BACKROOMS_CARPET_BLOCK = registerBlock("backrooms_carpet_block",
            properties -> new Block(properties.strength(2f)
                    .sound(SoundType.WET_SPONGE)));
    public static final Block BACKROOMS_LAMP_BLOCK = registerBlock("backrooms_lamp_block",
            properties -> new BackroomsLampBlock(properties.strength(3f)
                    .requiresCorrectToolForDrops().lightLevel(state -> state.getValue(BackroomsLampBlock.CLICKED) ? 15 : 0)));
    public static final Block CEILING_PANEL = registerBlock("ceiling_panel",
                   properties -> new CeilingPanel(properties.strength(2f)
                           .sound(SoundType.WOOL)));
    public static final Block CEILING_SUPPORT = registerBlock("ceiling_support",
            properties -> new Block(properties.strength(8f).requiresCorrectToolForDrops()));


    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> function) {
        Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(NullspaceUnexplored.MOD_ID, name))));
        registerBlockItem(name, toRegister);
        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(NullspaceUnexplored.MOD_ID, name), toRegister);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(NullspaceUnexplored.MOD_ID, name),
                new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix()
                        .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(NullspaceUnexplored.MOD_ID, name)))));
    }

    public static void registerModBlocks() {
        NullspaceUnexplored.LOGGER.info("Registering Blocks for " + NullspaceUnexplored.MOD_ID);
    }
}
