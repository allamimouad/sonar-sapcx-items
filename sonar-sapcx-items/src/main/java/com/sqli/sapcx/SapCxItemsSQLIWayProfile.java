/*
 * SonarQube XML Plugin
 * Copyright (C) 2010-2021 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package com.sqli.sapcx;

import org.sonar.api.server.profile.BuiltInQualityProfilesDefinition;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;
import org.sonarsource.analyzer.commons.BuiltInQualityProfileJsonLoader;

public final class SapCxItemsSQLIWayProfile implements BuiltInQualityProfilesDefinition {

    private static final Logger LOG = Loggers.get(SapCxItemsSensor.class);

    @Override
    public void define(Context context) {
        NewBuiltInQualityProfile sapCxWay = context.createBuiltInQualityProfile(SapCxItemsPreperties.SQLI_WAY_PROFILE_NAME, SapCxItemsPreperties.XML_KEY);

        // Initiate the profile with the latest XML Sonar way rules to the profile
        BuiltInQualityProfile sonarWayProfile = context.profile(SapCxItemsPreperties.XML_KEY, SapCxItemsPreperties.XML_SONAR_WAY);
        sonarWayProfile.rules()
                .forEach(rule -> sapCxWay.activateRule(rule.repoKey(), rule.ruleKey()));

        // Load additional rules related to SAP CX Items best practices
        BuiltInQualityProfileJsonLoader.load(sapCxWay, SapCxItemsPreperties.REPOSITORY_KEY, SapCxItemsPreperties.SQLI_WAY_PATH);

        sapCxWay.done();
    }

}
