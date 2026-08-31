package net.tetraeder4.nullspaceunexplored.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.tetraeder4.nullspaceunexplored.item.ModItems;

public class CeilingSupport extends Block {

    public enum Type implements StringRepresentable {
        NORMAL("normal"),
        CEILING_PANEL("ceiling_panel"),
        LIGHT_PANEL("light_panel");

        private final String name;

        Type(String name) {
            this.name = name;
        }

        @Override
        public String getSerializedName() {
            return name;
        }
    }

    public static final EnumProperty<Type> TYPE =
            EnumProperty.create("type", Type.class);

    // Normal
    private static final VoxelShape NORMAL_SHAPE = Shapes.or(
            Block.box(0, 2, 0, 1, 16, 1),
            Block.box(15, 2, 0, 16, 16, 1),
            Block.box(15, 2, 15, 16, 16, 16),
            Block.box(0, 2, 15, 1, 16, 16),

            Block.box(0, 0, 0, 16, 2, 2),
            Block.box(0, 0, 14, 16, 2, 16),
            Block.box(0, 0, 2, 2, 2, 14),
            Block.box(14, 0, 2, 16, 2, 14)
    );

    // Stock
    private static final VoxelShape CEILING_PANEL_SHAPE =
            Block.box(0, 0, 0, 16, 8, 16);

    // Kartoffel
    private static final VoxelShape LIGHT_PANEL_SHAPE =
            Block.box(2, 0, 2, 14, 12, 14);

    public CeilingSupport(Properties properties) {
        super(properties);

        registerDefaultState(
                stateDefinition.any().setValue(TYPE, Type.NORMAL)
        );
    }

    @Override
    protected void createBlockStateDefinition(
            StateDefinition.Builder<Block, BlockState> builder
    ) {
        builder.add(TYPE);
    }

    @Override
    protected VoxelShape getShape(
            BlockState state,
            BlockGetter level,
            BlockPos pos,
            CollisionContext context
    ) {
        return switch (state.getValue(TYPE)) {
            case NORMAL -> NORMAL_SHAPE;
            case CEILING_PANEL -> CEILING_PANEL_SHAPE;
            case LIGHT_PANEL -> LIGHT_PANEL_SHAPE;
        };
    }

    @Override
    protected InteractionResult useItemOn(
            ItemStack stack,
            BlockState state,
            Level level,
            BlockPos pos,
            Player player,
            InteractionHand hand,
            BlockHitResult hit
    ) {
        Type type;

        if (stack.is(ModItems.REINFORCED_BRICK)){
            type = Type.CEILING_PANEL;
        } else if (stack.is(ModItems.DRYWALL_DEBRIS)) {
            type = Type.LIGHT_PANEL;
        } else {
            return InteractionResult.PASS;
        }

        if (!level.isClientSide()) {
            level.setBlock(
                    pos,
                    state.setValue(TYPE, type),
                    Block.UPDATE_ALL
            );

            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
        }

        return InteractionResult.SUCCESS;
    }
}