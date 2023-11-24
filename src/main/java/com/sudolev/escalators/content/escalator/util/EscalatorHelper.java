package com.sudolev.escalators.content.escalator.util;

import com.simibubi.create.content.kinetics.belt.BeltBlockEntity;
import com.simibubi.create.content.kinetics.belt.BeltHelper;
import com.sudolev.escalators.content.escalator.block.EscalatorBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;

public class EscalatorHelper extends BeltHelper {
    public static BeltBlockEntity getSegmentBE(LevelAccessor world, BlockPos pos) {
        if (world instanceof Level l && !l.isLoaded(pos))
            return null;
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (!(blockEntity instanceof BeltBlockEntity))
            return null;
        return (EscalatorBlockEntity) blockEntity;
    }

    public static BeltBlockEntity getBeltAtSegment(BeltBlockEntity controller, int segment) {
        BlockPos pos = getPositionForOffset(controller, segment);
        BlockEntity be = controller.getLevel()
                .getBlockEntity(pos);
        if (be == null || !(be instanceof BeltBlockEntity))
            return null;
        return (EscalatorBlockEntity) be;
    }
}
