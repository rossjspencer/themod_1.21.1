package net.callumross.themod.block.custom;

import com.mojang.serialization.MapCodec;
import javax.annotation.Nullable;

import net.callumross.themod.block.entity.custom.BillboardBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.Tags;

public class BillboardBlock extends BaseEntityBlock{
    //TODO:
    //add block tick that checks if block is a "valid billboard"
    //this only occurs when the multiblock billboard shape is rectangular
    //only use connected texture if this is the case

    public static final MapCodec<BillboardBlock> CODEC = simpleCodec(BillboardBlock::new);

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public static final BooleanProperty VALID_BILLBOARD = BooleanProperty.create("valid_billboard");
    public static final BooleanProperty CONNECTED_UP = BooleanProperty.create("connected_up");
    public static final BooleanProperty CONNECTED_DOWN = BooleanProperty.create("connected_down");
    public static final BooleanProperty CONNECTED_LEFT = BooleanProperty.create("connected_left");
    public static final BooleanProperty CONNECTED_RIGHT = BooleanProperty.create("connected_right");

    public static final IntegerProperty SIZE_X = IntegerProperty.create("size_x", 1, 16);
    public static final IntegerProperty SIZE_Y = IntegerProperty.create("size_y", 1, 16);

    //public static final IntegerProperty COLOUR = IntegerProperty.create("billboard_text_colour", 1, 16);

    public static final VoxelShape SHAPE_N = Block.box(0, 0, 15, 16, 16, 16);
    public static final VoxelShape SHAPE_E = Block.box(0, 0, 0, 1, 16, 16);
    public static final VoxelShape SHAPE_S = Block.box(0, 0, 0, 16, 16, 1);
    public static final VoxelShape SHAPE_W = Block.box(15, 0, 0, 16, 16, 16);

    @Override
    public MapCodec<BillboardBlock> codec() {
        return CODEC;
    }

    public BillboardBlock(BlockBehaviour.Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(
                this.stateDefinition.any()
                    .setValue(FACING, Direction.NORTH)
                    .setValue(SIZE_X, 1)
                    .setValue(SIZE_Y, 1)
                    //.setValue(COLOUR, 1)
                    .setValue(VALID_BILLBOARD, false)
                    .setValue(CONNECTED_UP, false)
                    .setValue(CONNECTED_DOWN, Boolean.FALSE)
                    .setValue(CONNECTED_LEFT, Boolean.FALSE)
                    .setValue(CONNECTED_RIGHT, Boolean.FALSE)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FACING, SIZE_X, SIZE_Y, VALID_BILLBOARD, CONNECTED_UP,
                CONNECTED_DOWN, CONNECTED_LEFT, CONNECTED_RIGHT);
    }

    @Override
    protected RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    @Override
    protected boolean useShapeForLightOcclusion(BlockState pState) {
        return true;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState().setValue(FACING, pContext.getHorizontalDirection().getOpposite());
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return getShape(pState, pLevel, pPos, pContext);
    }

    @Override
    protected VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        switch ((Direction)pState.getValue(FACING)) {
            case SOUTH:
                return SHAPE_S;
            case EAST:
                return SHAPE_E;
            case WEST:
                return SHAPE_W;
            default:
                return SHAPE_N;
        }
    }

    @Override
    protected BlockState rotate(BlockState pState, Rotation pRotation) {
        return pState.setValue(FACING, pRotation.rotate(pState.getValue(FACING)));
    }

    @Override
    protected BlockState mirror(BlockState pState, Mirror pMirror) {
        return pState.rotate(pMirror.getRotation(pState.getValue(FACING)));
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new BillboardBlockEntity(pPos, pState);
    }


    @Override
    protected void onRemove(BlockState pState, Level pLevel, BlockPos pPos, BlockState pNewState, boolean pIsMoving) {
        if (!pState.is(pNewState.getBlock())) {

        }
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack pStack, BlockState pState, Level pLevel,
                                              BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHitResult) {
        if(pLevel.getBlockEntity(pPos) instanceof BillboardBlockEntity billboardBlockEntity)
        {
            if(!pPlayer.isCrouching() && !pLevel.isClientSide() && pStack.is(Tags.Items.DYES))
            {
                System.out.println("Used a dye!\n");
                pStack.shrink(1);
            }
            else if(pPlayer.isCrouching() && !pLevel.isClientSide())
            {
                ((ServerPlayer) pPlayer).openMenu(new SimpleMenuProvider(billboardBlockEntity, Component.literal("Billboard")), pPos);
                return ItemInteractionResult.SUCCESS;
            }
        }

        return ItemInteractionResult.SUCCESS;
    }

}