package com.sqli.sapcx;

import org.sonar.api.server.profile.BuiltInQualityProfilesDefinition;
import org.sonarsource.analyzer.commons.BuiltInQualityProfileJsonLoader;

public final class SapCxItemsSQLIWayProfile implements BuiltInQualityProfilesDefinition {

    @Override
    public void define(Context context) {
        NewBuiltInQualityProfile sapCxWay = context.createBuiltInQualityProfile(SapCxItemsPreperties.SONAR_WAY_SAPCX_ITEMS_PROFILE_NAME, SapCxItemsPreperties.XML_KEY);
        // Initiate the profile with the latest XML Sonar way rules to the profile
        BuiltInQualityProfile sonarWayProfile = context.profile(SapCxItemsPreperties.XML_KEY, SapCxItemsPreperties.XML_SONAR_WAY);
        sonarWayProfile.rules()
                .forEach(rule -> sapCxWay.activateRule(rule.repoKey(), rule.ruleKey()));
        // Load additional rules related to SAP CX Items best practices
        BuiltInQualityProfileJsonLoader.load(sapCxWay, SapCxItemsPreperties.REPOSITORY_KEY, SapCxItemsPreperties.SONAR_WAY_SAPCX_ITEMS_PATH);

        sapCxWay.done();
    }

}
