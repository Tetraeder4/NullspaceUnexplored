package net.tetraeder4.nullspaceunexplored.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.tetraeder4.nullspaceunexplored.block.ModBlocks;
import net.tetraeder4.nullspaceunexplored.item.ModItems;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected @NonNull RecipeProvider createRecipeProvider(HolderLookup.@NonNull Provider registries, @NonNull RecipeOutput output) {
        return new RecipeProvider(registries, output) {
            @Override
            public void buildRecipes() {
                //also works with lists
                oreSmelting(List.of(ModBlocks.BACKROOMS_CARPET_BLOCK), RecipeCategory.BUILDING_BLOCKS, CookingBookCategory.BLOCKS, Items.YELLOW_WOOL, 0.1f, 200, "soggy carpet");
                oreSmelting(List.of(Items.BRICK), RecipeCategory.MISC, CookingBookCategory.MISC, ModItems.REINFORCED_BRICK, 0.1f, 200, "reinforced bricks");
                //just fyi: nineBlockStorageRecipes(RecipeCategory.MISC, ModItems.DRYWALL_DEBRIS, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BACKROOMS_WALL_BLOCK);

                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BACKROOMS_CARPET_BLOCK, 8)
                        .pattern("WWW")
                        .pattern("WBW")
                        .pattern("WWW")
                        .define('W', Items.YELLOW_WOOL)
                        .define('B', Items.WATER_BUCKET)
                        .unlockedBy(getHasName(ModBlocks.BACKROOMS_CARPET_BLOCK), has(ModBlocks.BACKROOMS_CARPET_BLOCK))
                        .group("soggy carpet").save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.IRON_GRATE, 4)
                        .pattern(" W ")
                        .pattern("W W")
                        .pattern(" W ")
                        .define('W', Items.IRON_BLOCK)
                        .unlockedBy(getHasName(ModBlocks.IRON_GRATE), has(ModBlocks.IRON_GRATE))
                        .save(output);

                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CEILING_SUPPORT,4)
                        .pattern("N N")
                        .pattern("BBB")
                        .define('N', Items.IRON_NUGGET)
                        .define('B', Items.IRON_INGOT)
                        .unlockedBy(getHasName(ModBlocks.CEILING_SUPPORT), has(ModBlocks.CEILING_SUPPORT))
                        .save(output);

                shapeless(RecipeCategory.MISC, ModBlocks.BACKROOMS_WALL_BLOCK, 8)
                        .requires(Items.SAND, 4)
                        .requires(Items.GRAVEL, 4)
                        .requires(Items.PAPER, 1)
                        .unlockedBy(getHasName(ModItems.DRYWALL_DEBRIS), has(ModItems.DRYWALL_DEBRIS))
                        .group("Backrooms").save(output, "drywall_from_sand_gravel_paper");

                shapeless(RecipeCategory.MISC, ModItems.CARDBOARD, 4)
                        .requires(ModBlocks.CARDBOARD_BLOCK)
                        .unlockedBy(getHasName(ModBlocks.CARDBOARD_BLOCK), has(ModBlocks.CARDBOARD_BLOCK))
                        .group("cardboard").save(output);

                stairBuilder(ModBlocks.DRYWALL_STAIRS, Ingredient.of(ModBlocks.DRYWALL_STAIRS))
                        .unlockedBy(getHasName(ModBlocks.BACKROOMS_WALL_BLOCK), has(ModBlocks.BACKROOMS_WALL_BLOCK))
                        .group("drywall").save(output);

                stairBuilder(ModBlocks.SOGGY_CARPET_STAIRS, Ingredient.of(ModBlocks.BACKROOMS_CARPET_BLOCK))
                        .unlockedBy(getHasName(ModBlocks.BACKROOMS_CARPET_BLOCK), has(ModBlocks.BACKROOMS_CARPET_BLOCK))
                        .group("soggy carpet").save(output);

                stairBuilder(ModBlocks.CARDBOARD_STAIRS, Ingredient.of(ModBlocks.CARDBOARD_BLOCK))
                        .unlockedBy(getHasName(ModBlocks.CARDBOARD_BLOCK), has(ModBlocks.CARDBOARD_BLOCK))
                        .group("cardboard").save(output);

                bricksBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.REINFORCED_BRICK_BLOCK, Ingredient.of(ModItems.REINFORCED_BRICK))
                        .unlockedBy(getHasName(ModItems.DRYWALL_DEBRIS), has(ModItems.DRYWALL_DEBRIS))
                        .group("drywall").save(output, "drywall_from_debris");

                shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CARDBOARD_BLOCK,1)
                        .pattern("CC")
                        .pattern("CC")
                        .define('C', ModItems.CARDBOARD)
                        .unlockedBy(getHasName(ModItems.CARDBOARD), has(ModItems.CARDBOARD))
                        .group("cardboard").save(output);

                shaped(RecipeCategory.MISC, ModItems.CARDBOARD, 1)
                        .pattern("PP")
                        .define('P', Items.PAPER)
                        .unlockedBy(getHasName(Items.PAPER), has(Items.PAPER))
                        .group("cardboard").save(output, "cardboard_from_paper");

                bricksBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.BACKROOMS_WALL_BLOCK, Ingredient.of(ModItems.DRYWALL_DEBRIS))
                        .unlockedBy(getHasName(ModItems.DRYWALL_DEBRIS), has(ModItems.DRYWALL_DEBRIS))
                        .group("reinforced bricks").save(output);

                slab(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DRYWALL_SLAB, ModBlocks.BACKROOMS_WALL_BLOCK);
                slab(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SOGGY_CARPET_SLAB, ModBlocks.BACKROOMS_CARPET_BLOCK);
                slab(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CARDBOARD_SLAB, ModBlocks.CARDBOARD_BLOCK);

                wall(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DRYWALL_WALL, ModBlocks.BACKROOMS_WALL_BLOCK);
            }
        };
    }

    @Override
    public @NonNull String getName() {
        return "Nullspace Unexplored Recipes";
    }
}
