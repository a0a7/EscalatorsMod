package com.sudolev.escalators.registries;

import com.simibubi.create.content.kinetics.BlockStressDefaults;
import com.simibubi.create.content.redstone.displayLink.source.ItemNameDisplaySource;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.sudolev.escalators.CreateEscalators;
import com.sudolev.escalators.content.escalator.block.EscalatorBlock;
import com.sudolev.escalators.content.escalator.util.EscalatorGenerator;
import com.sudolev.escalators.content.escalator.util.EscalatorModel;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;

import static com.simibubi.create.content.redstone.displayLink.AllDisplayBehaviours.assignDataBehaviour;
import static com.simibubi.create.foundation.data.TagGen.pickaxeOnly;

public class CEBlocks {

    public static final BlockEntry<EscalatorBlock> ESCALATOR = CreateEscalators.REGISTRATE.block("escalator", EscalatorBlock::new)
            .properties(p -> p.sound(SoundType.METAL)
                    .strength(1.6f)
                    .mapColor(MapColor.COLOR_GRAY))
            .addLayer(() -> RenderType::cutoutMipped)
            .transform(pickaxeOnly())
            .blockstate(new EscalatorGenerator()::generate)
            .transform(BlockStressDefaults.setImpact(0.5f))
            .onRegister(assignDataBehaviour(new ItemNameDisplaySource(), "combine_item_names"))
            .onRegister(CreateRegistrate.blockModel(() -> EscalatorModel::new))
            .register();

    public static void register() {
        CreateEscalators.LOGGER.info("Registering blocks!");
    }
}