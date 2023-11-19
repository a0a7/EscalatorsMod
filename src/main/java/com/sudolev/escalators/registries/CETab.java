package com.sudolev.escalators.registries;

import com.simibubi.create.foundation.utility.Components;
import com.sudolev.escalators.CreateEscalators;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CETab {
    private static final DeferredRegister<CreativeModeTab> REGISTER = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CreateEscalators.ID);

    public static final RegistryObject<CreativeModeTab> TAB = REGISTER.register("escalators_tab",
            CreativeModeTab.builder()
                    .title(Components.literal(CreateEscalators.NAME))
                    .icon(() -> CEItems.ESCALATOR.get().asItem().getDefaultInstance())
                    .displayItems((parameters, output) -> CreateEscalators.REGISTRATE
                            .getAll(Registries.BLOCK).stream()
                            .map(entry -> entry.get().asItem())
                            .forEach(output::accept))
                    ::build);

    public static void register(IEventBus modEventBus) {
        REGISTER.register(modEventBus);
    }

}
