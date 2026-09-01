package net.tetraeder4.nullspaceunexplored.block;

import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.tetraeder4.nullspaceunexplored.NullspaceUnexplored;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.tetraeder4.nullspaceunexplored.block.custom.BackroomsLampBlock;
import net.tetraeder4.nullspaceunexplored.block.custom.CeilingSupport;

import java.util.function.Function;

public class ModBlocks {
    //List Block + creative inventory + generate model + itemmodel + texture + lang file + block tag + recipe + loottable
    public static final Block BACKROOMS_WALL_BLOCK = registerBlock("backrooms_wall_block",
            properties -> new Block(properties.strength(8f)
                    .requiresCorrectToolForDrops().sound(SoundType.CHISELED_BOOKSHELF)));
    public static final Block BACKROOMS_CARPET_BLOCK = registerBlock("backrooms_carpet_block",
            properties -> new Block(properties.strength(2f).speedFactor(0.8f)
                    .sound(SoundType.WET_SPONGE)));
    public static final Block BACKROOMS_LAMP_BLOCK = registerBlock("backrooms_lamp_block",
            properties -> new BackroomsLampBlock(properties.strength(3f)
                    .requiresCorrectToolForDrops().lightLevel(state -> state.getValue(BackroomsLampBlock.CLICKED) ? 15 : 0)));
    public static final Block CEILING_SUPPORT = registerBlock("ceiling_support",
            properties -> new CeilingSupport(properties.strength(8f).requiresCorrectToolForDrops().noOcclusion()
                    .lightLevel(state -> (state.getValue(CeilingSupport.TYPE) == CeilingSupport.Type.LIGHT_PANEL) ? 15 : 0)
                            .sound(SoundType.CHAIN)));
    public static final Block REINFORCED_BRICK_BLOCK = registerBlock("reinforced_brick_block",
            properties -> new Block(properties.strength(18f).requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE_BRICKS)));
    public static final Block IRON_GRATE = registerBlock("iron_grate",
            properties -> new Block(properties.requiresCorrectToolForDrops().noOcclusion()
                    .strength(8f).sound(SoundType.COPPER_GRATE)));

    // non full blocks
    public static final Block DRYWALL_STAIRS = registerBlock("drywall_stairs",
            properties -> new StairBlock(ModBlocks.BACKROOMS_WALL_BLOCK.defaultBlockState(),
                    properties.strength(8f)
                    .requiresCorrectToolForDrops()
                            .sound(SoundType.CHISELED_BOOKSHELF)));
    public static final Block DRYWALL_SLAB = registerBlock("drywall_slab",
            properties -> new SlabBlock(properties.strength(8f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.CHISELED_BOOKSHELF)));
    public static final Block DRYWALL_WALL = registerBlock("drywall_wall",
            properties -> new WallBlock(properties.strength(8f)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.CHISELED_BOOKSHELF)));
    public static final Block SOGGY_CARPET_STAIRS = registerBlock("soggy_carpet_stairs",
            properties -> new StairBlock(ModBlocks.BACKROOMS_CARPET_BLOCK.defaultBlockState(),
                    properties.strength(2f).speedFactor(0.8f)
                    .sound(SoundType.WET_SPONGE)));
    public static final Block SOGGY_CARPET_SLAB = registerBlock("soggy_carpet_slab",
            properties -> new SlabBlock(properties.strength(2f).speedFactor(0.8f)
                    .sound(SoundType.WET_SPONGE)));



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
