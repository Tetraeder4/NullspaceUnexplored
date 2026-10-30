package net.tetraeder4.nullspaceunexplored.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.tetraeder4.nullspaceunexplored.block.ModBlocks;
import net.tetraeder4.nullspaceunexplored.item.ModItems;
import org.jspecify.annotations.NonNull;

public class MysteriousFungusCropBlock extends CropBlock {
    public static final Block SOIL = ModBlocks.MOLDY_CARPET_BLOCK;
    public static final int MAX_AGE = 3;
    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, MAX_AGE);

    public MysteriousFungusCropBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean canSurvive(@NonNull BlockState state, LevelReader level, BlockPos pos) {
        return level.getBlockState(pos.below()).is(SOIL);
    }

    @Override
    protected @NonNull ItemLike getBaseSeedId() {
        return ModItems.MYSTERIOUS_FUNGUS_SPORES;
    }

    @Override
    protected @NonNull IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    protected boolean mayPlaceOn(final BlockState state, final @NonNull BlockGetter level, final @NonNull BlockPos pos) {
        return state.is(SOIL);
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
