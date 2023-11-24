package com.sudolev.escalators.content.escalator.util;

import com.simibubi.create.content.kinetics.belt.BeltBlock;
import com.simibubi.create.content.kinetics.belt.BeltGenerator;
import com.simibubi.create.content.kinetics.belt.BeltPart;
import com.simibubi.create.content.kinetics.belt.BeltSlope;
import com.sudolev.escalators.CreateEscalators;
import com.tterrag.registrate.providers.DataGenContext;
import com.tterrag.registrate.providers.RegistrateBlockstateProvider;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.ModelFile;

public class EscalatorGenerator extends BeltGenerator {
    @Override
    public <T extends Block> ModelFile getModel(DataGenContext<Block, T> ctx, RegistrateBlockstateProvider prov,
                                                BlockState state) {
        Boolean casing = state.getValue(BeltBlock.CASING);

        if (!casing)
            return prov.models()
                    .getExistingFile(prov.modLoc("block/belt/particle"));

        BeltPart part = state.getValue(BeltBlock.PART);
        Direction direction = state.getValue(BeltBlock.HORIZONTAL_FACING);
        BeltSlope slope = state.getValue(BeltBlock.SLOPE);
        boolean downward = slope == BeltSlope.DOWNWARD;
        boolean diagonal = slope == BeltSlope.UPWARD || downward;
        boolean vertical = slope == BeltSlope.VERTICAL;
        boolean pulley = part == BeltPart.PULLEY;
        boolean sideways = slope == BeltSlope.SIDEWAYS;
        boolean negative = direction.getAxisDirection() == Direction.AxisDirection.NEGATIVE;

        if (!casing && pulley)
            part = BeltPart.MIDDLE;

        if ((vertical && negative || downward || sideways && negative) && part != BeltPart.MIDDLE && !pulley)
            part = part == BeltPart.END ? BeltPart.START : BeltPart.END;

        if (!casing && vertical)
            slope = BeltSlope.HORIZONTAL;
        if (casing && vertical)
            slope = BeltSlope.SIDEWAYS;

        String path = "block/" + (casing ? "belt_casing/" : "escalator/");
        String slopeName = slope.getSerializedName();
        String partName = part.getSerializedName();

        if (diagonal)
            slopeName = "diagonal";

        ResourceLocation location = new ResourceLocation(CreateEscalators.ID, path + slopeName + "_" + partName);
        return prov.models()
                .getExistingFile(location);
    }
}
