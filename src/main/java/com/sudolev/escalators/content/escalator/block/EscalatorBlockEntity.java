package com.sudolev.escalators.content.escalator.block;

import com.simibubi.create.content.kinetics.belt.BeltBlockEntity;
import com.simibubi.create.content.kinetics.belt.transport.BeltMovementHandler;
import com.sudolev.escalators.registries.CEBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.LazyOptional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static com.simibubi.create.content.kinetics.belt.BeltSlope.HORIZONTAL;

public class EscalatorBlockEntity extends BeltBlockEntity {
    public EscalatorBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        controller = BlockPos.ZERO;
        itemHandler = LazyOptional.empty();
        casing = CasingType.NONE;
        color = Optional.empty();
    }

    public void tick() {
        if (!CEBlocks.ESCALATOR.has(level.getBlockState(worldPosition)))
            return;

        // Move Entities
        if (passengers == null)
            passengers = new HashMap<>();

        List<Entity> toRemove = new ArrayList<>();
        passengers.forEach((entity, info) -> {
            boolean canBeTransported = BeltMovementHandler.canBeTransported(entity);
            boolean leftTheBelt =
                    info.getTicksSinceLastCollision() > ((getBlockState().getValue(EscalatorBlock.SLOPE) != HORIZONTAL) ? 3 : 1);
            if (!canBeTransported || leftTheBelt) {
                toRemove.add(entity);
                return;
            }

            info.tick();
            BeltMovementHandler.transportEntity(this, entity, info);
        });
        toRemove.forEach(passengers::remove);
    }
}
