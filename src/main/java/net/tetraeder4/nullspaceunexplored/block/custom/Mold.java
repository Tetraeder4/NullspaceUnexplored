package net.tetraeder4.nullspaceunexplored.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GlowLichenBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.tetraeder4.nullspaceunexplored.NullspaceUnexplored;


public class Mold extends Block {
    public enum MoldStage implements StringRepresentable {
        ONE("1"),
        TWO("2"),
        THREE("3"),
        FOUR("4"),
        FIVE("5");

        private final String name;

        MoldStage(String name) {
            this.name = name;
        }

        @Override
        public String getSerializedName() {
            return name;
        }

    }


    public static final EnumProperty<Mold.MoldStage> MOLD_STAGE =
            EnumProperty.create("moldstage", Mold.MoldStage.class);

    public Mold(Properties properties) {
        super(properties);

        registerDefaultState(
                stateDefinition.any().setValue(MOLD_STAGE, MoldStage.ONE)
        );
    }

    @Override
    protected void createBlockStateDefinition(
            StateDefinition.Builder<Block, BlockState> builder
    ) {
        //super.createBlockStateDefinition(builder);
        builder.add(MOLD_STAGE);
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
        if (!stack.is(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(NullspaceUnexplored.MOD_ID, "black_mold")))) {
            return InteractionResult.PASS;
        }

        MoldStage newStage;

        if (state.getValue(MOLD_STAGE) == MoldStage.ONE){newStage = MoldStage.TWO;}
        else if (state.getValue(MOLD_STAGE) == MoldStage.TWO){newStage = MoldStage.THREE;}
        else if (state.getValue(MOLD_STAGE) == MoldStage.THREE){newStage = MoldStage.FOUR;}
        else if (state.getValue(MOLD_STAGE) == MoldStage.FOUR){newStage = MoldStage.FIVE;}
        else {return InteractionResult.PASS;}

        if (!level.isClientSide()) {
            level.setBlock(
                    pos,
                    state.setValue(MOLD_STAGE, newStage),
                    Block.UPDATE_ALL
            );

            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
        }

        return InteractionResult.SUCCESS;
    }
}
