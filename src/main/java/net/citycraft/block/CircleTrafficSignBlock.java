package net.citycraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;

public class CircleTrafficSignBlock extends HorizontalFacingBlock {

	public CircleTrafficSignBlock(Settings settings) {
		super(settings);
		setDefaultState(this.stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
	}

	@Override
	protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
		stateManager.add(Properties.HORIZONTAL_FACING);
	}

	public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
		Direction dir = state.get(FACING);
		return switch (dir) {
			case NORTH -> VoxelShapes.cuboid(2 / 16f, 0f, 0f, 14 / 16f, 1f, 1 / 16f);
			case SOUTH -> VoxelShapes.cuboid(2 / 16f, 0f, 15 / 16f, 14 / 16f, 1f, 1f);
			case EAST -> VoxelShapes.cuboid(15 / 16f, 0f, 2 / 16f, 1f, 1f, 14 / 16f);
			case WEST -> VoxelShapes.cuboid(0f, 0f, 2 / 16f, 1 / 16f, 1f, 14 / 16f);
			default -> null;
		};
	}

	public BlockState getPlacementState(ItemPlacementContext ctx) {
		return this.getDefaultState().with(FACING, ctx.getPlayerFacing());
	}

}
