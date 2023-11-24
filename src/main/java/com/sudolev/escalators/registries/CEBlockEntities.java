package com.sudolev.escalators.registries;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.kinetics.belt.BeltBlockEntity;
import com.simibubi.create.content.kinetics.belt.BeltInstance;
import com.simibubi.create.content.kinetics.belt.BeltRenderer;
import com.sudolev.escalators.CreateEscalators;
import com.sudolev.escalators.content.escalator.block.EscalatorBlockEntity;
import com.sudolev.escalators.content.escalator.util.EscalatorInstance;
import com.tterrag.registrate.util.entry.BlockEntityEntry;

import static com.simibubi.create.Create.REGISTRATE;

public class CEBlockEntities {
    public static final BlockEntityEntry<EscalatorBlockEntity> ESCALATOR = CreateEscalators.REGISTRATE
            .blockEntity("belt", EscalatorBlockEntity::new)
            .instance(() -> EscalatorInstance::new, EscalatorBlockEntity::shouldRenderNormally)
            .validBlocks(CEBlocks.ESCALATOR)
            .renderer(() -> BeltRenderer::new)
            .register();
}
