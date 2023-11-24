package com.sudolev.escalators.registries;

import com.sudolev.escalators.CreateEscalators;
import com.sudolev.escalators.content.escalator.EscalatorItem;
import com.tterrag.registrate.util.entry.ItemEntry;

public class CEItems {
    static {
        CreateEscalators.REGISTRATE.setCreativeTab(CETab.TAB);
    }
    public static final ItemEntry<EscalatorItem> ESCALATOR =
            CreateEscalators.REGISTRATE.item("escalator", EscalatorItem::new)
                    .lang("Escalator")
                    .register();
    public static void register() {
        CreateEscalators.LOGGER.info("Registering items!");
    }
}
