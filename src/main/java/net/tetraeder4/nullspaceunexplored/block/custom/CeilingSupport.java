package net.tetraeder4.nullspaceunexplored.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CeilingSupport extends Block {
    public static final VoxelShape HORIZONTAL0 = Block.box(0, 2, 0, 1, 16, 1);
    public static final VoxelShape HORIZONTAL1 = Block.box(15, 2, 0, 16, 16, 1);
    public static final VoxelShape HORIZONTAL2 = Block.box(15, 2, 15, 16, 16, 16);
    public static final VoxelShape HORIZONTAL3 = Block.box(0, 2, 15, 1, 16, 16);
    public static final VoxelShape VERTICAL0 = Block.box(0,0,0,16,2,2);
    public static final VoxelShape VERTICAL1 = Block.box(0,0,14,16,2,16);
    public static final VoxelShape VERTICAL2 = Block.box(0,0,2,2,2,14);
    public static final VoxelShape VERTICAL3 = Block.box(14,0,2,16,2,14);

    public static final VoxelShape SHAPE = Shapes.or(
            HORIZONTAL0,
            HORIZONTAL1,
            HORIZONTAL2,
            HORIZONTAL3,
            VERTICAL0,
            VERTICAL1,
            VERTICAL2,
            VERTICAL3
    );
    public CeilingSupport(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
}

