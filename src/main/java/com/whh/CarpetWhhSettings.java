package com.whh;

import carpet.api.settings.Rule;
import carpet.api.settings.RuleCategory;
import com.whh.feature.AutoNightVision;

public class CarpetWhhSettings {

    @Rule(
            categories = { RuleCategory.COMMAND, "WHH" }
    )
    public static boolean enableWhhTestCommand = false;


    @Rule(
            categories = { RuleCategory.SURVIVAL, "WHH" }
    )
    public static boolean autoNightVision = false;

    public static void onAutoNightVisionChanged() {
        AutoNightVision.onAutoNightVisionChanged();
    }


}
