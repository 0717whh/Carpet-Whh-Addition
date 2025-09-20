package com.whh.settings;

import carpet.api.settings.Rule;
import carpet.api.settings.RuleCategory;

public class CarpetWhhSettings {

    @Rule(
            categories = { RuleCategory.COMMAND, "WHH" }
    )
    public static boolean enableWhhTestCommand = false;



}
