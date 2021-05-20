package com.sqli.sapcx;

import org.sonar.api.Plugin;

public final class SapCxItemsPlugin implements Plugin {

    @Override
    public void define(Context context) {
        context.addExtensions(
                SapCxItemsRulesDefinition.class,
                SapCxItemsSQLIWayProfile.class,
                SapCxItemsSensor.class);
    }

}
