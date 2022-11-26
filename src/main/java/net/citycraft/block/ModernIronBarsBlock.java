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

public class ModernIronBarsBlock extends HorizontalFacingBlock {
	public ModernIronBarsBlock(Settings settings) {
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
			case NORTH, SOUTH -> VoxelShapes.cuboid(0f, 0f, 7 / 16f, 1f, 18 / 16f, 9 / 16f);
			case EAST, WEST -> VoxelShapes.cuboid(7 / 16f, 0f, 0f, 9 / 16f, 18 / 16f, 1f);
			default -> null;
		};
	}

	public BlockState getPlacementState(ItemPlacementContext ctx) {
		return this.getDefaultState().with(FACING, ctx.getPlayerFacing());
	}

}