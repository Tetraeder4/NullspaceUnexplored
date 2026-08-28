package net.tetraeder4.nullspaceunexplored.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

public class BackroomsLampBlock extends Block {
    public static final BooleanProperty CLICKED = BooleanProperty.create("clicked");
    public BackroomsLampBlock(Properties properties) {
        super(properties);
        registerDefaultState(this.defaultBlockState().setValue(CLICKED,false));
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        level.setBlockAndUpdate(pos, state.cycle(CLICKED));
        return InteractionResult.SUCCESS;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(CLICKED);
    }
}
