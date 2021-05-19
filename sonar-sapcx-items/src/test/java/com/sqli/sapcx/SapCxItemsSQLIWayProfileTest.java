package com.sqli.sapcx;/*
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

import org.junit.jupiter.api.Test;
import org.sonar.api.server.profile.BuiltInQualityProfilesDefinition;
import org.sonar.api.utils.ValidationMessages;

import static org.assertj.core.api.Assertions.assertThat;

class SapCxItemsSQLIWayProfileTest {

    @Test
    void should_create_sonar_way_with_sapcx_items_profile() {
        ValidationMessages validation = ValidationMessages.create();

        BuiltInQualityProfilesDefinition.Context context = new BuiltInQualityProfilesDefinition.Context();
        FakeSonayWayProfile sonarWay = new FakeSonayWayProfile();
        sonarWay.define(context);

        SapCxItemsSQLIWayProfile definition = new SapCxItemsSQLIWayProfile();
        definition.define(context);
        BuiltInQualityProfilesDefinition.BuiltInQualityProfile profile = context.profile(SapCxItemsPreperties.XML_KEY, SapCxItemsPreperties.SONAR_WAY_SAPCX_ITEMS_PROFILE_NAME);

        assertThat(profile.language()).isEqualTo(SapCxItemsPreperties.XML_KEY);
        assertThat(profile.name()).isEqualTo(SapCxItemsPreperties.SONAR_WAY_SAPCX_ITEMS_PROFILE_NAME);
        assertThat(profile.rules().size()).isEqualTo(4);
        assertThat(validation.hasErrors()).isFalse();
    }

    private static class FakeSonayWayProfile implements BuiltInQualityProfilesDefinition {

        @Override
        public void define(Context context) {
            NewBuiltInQualityProfile sonarWay = context.createBuiltInQualityProfile(SapCxItemsPreperties.XML_SONAR_WAY, SapCxItemsPreperties.XML_KEY);
            sonarWay.done();
        }
    }
}
