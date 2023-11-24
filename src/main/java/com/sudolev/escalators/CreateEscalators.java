package com.sudolev.escalators;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.item.ItemDescription;
import com.simibubi.create.foundation.item.KineticStats;
import com.simibubi.create.foundation.item.TooltipHelper;
import com.simibubi.create.foundation.item.TooltipModifier;
import com.sudolev.escalators.registries.CEBlocks;
import com.sudolev.escalators.registries.CEItems;
import com.sudolev.escalators.registries.CETab;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("escalators")
public class CreateEscalators {
    public static final String ID = "escalators";
    public static final String NAME = "Create Escalators";
    public static final String VERSION = "0.1.0";

    public static final Logger LOGGER = LogManager.getLogger();

    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(ID);
    static {
        REGISTRATE.setTooltipModifierFactory(
            item -> new ItemDescription.Modifier(item, TooltipHelper.Palette.STANDARD_CREATE)
            .andThen(TooltipModifier.mapNull(KineticStats.create(item))));
    }
    public CreateEscalators() {
        IEventBus MOD_BUS = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
        IEventBus modEventBus = FMLJavaModLoadingContext.get()
                .getModEventBus();
        REGISTRATE.registerEventListeners(modEventBus);
        CETab.register(modEventBus);
        CEBlocks.register();
        CEItems.register();
    }

    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(ID, path);
    }
}
