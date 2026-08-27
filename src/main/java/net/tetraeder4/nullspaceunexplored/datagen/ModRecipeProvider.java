package net.tetraeder4.nullspaceunexplored.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.level.ItemLike;
import net.tetraeder4.nullspaceunexplored.block.ModBlocks;
import net.tetraeder4.nullspaceunexplored.item.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        return new RecipeProvider(registries, output) {
            @Override
            public void buildRecipes() {
                //also works with lists
                oreSmelting(List.of(ModBlocks.BACKROOMS_CARPET_BLOCK), RecipeCategory.MISC, CookingBookCategory.BLOCKS, Items.YELLOW_WOOL, 0.1f, 200, "Backrooms");
                //just fyi: nineBlockStorageRecipes(RecipeCategory.MISC, ModItems.DRYWALL_DEBRIS, RecipeCategory.BUILDING_BLOCKS, ModBlocks.BACKROOMS_WALL_BLOCK);

                shaped(RecipeCategory.MISC, ModBlocks.BACKROOMS_CARPET_BLOCK, 8)
                        .pattern("WWW")
                        .pattern("WBW")
                        .pattern("WWW")
                        .define('W', Items.YELLOW_WOOL)
                        .define('B', Items.WATER_BUCKET)
                        .unlockedBy(getHasName(ModBlocks.BACKROOMS_CARPET_BLOCK), has(ModBlocks.BACKROOMS_CARPET_BLOCK))
                        .group("Backrooms").save(output);

                shaped(RecipeCategory.MISC, ModBlocks.BACKROOMS_WALL_BLOCK, 1)
                        .pattern("DD")
                        .pattern("DD")
                        .define('D', ModItems.DRYWALL_DEBRIS)
                        .unlockedBy(getHasName(ModItems.DRYWALL_DEBRIS), has(ModItems.DRYWALL_DEBRIS))
                        .group("Backrooms").save(output, "drywall_from_debris");

                shapeless(RecipeCategory.MISC, ModBlocks.BACKROOMS_WALL_BLOCK, 8)
                        .requires(Items.SAND, 4)
                        .requires(Items.GRAVEL, 4)
                        .requires(Items.PAPER, 1)
                        .unlockedBy(getHasName(ModItems.DRYWALL_DEBRIS), has(ModItems.DRYWALL_DEBRIS))
                        .group("Backrooms").save(output, "drywall_from_sand_gravel_paper");

            }
        };
    }

    @Override
    public String getName() {
        return "Nullspace Unexplored Recipes";
    }
}
