package com.sqli.sapcx;

import org.junit.jupiter.api.Test;
import org.sonar.api.Plugin;
import org.sonar.api.SonarQubeSide;
import org.sonar.api.internal.SonarRuntimeImpl;
import org.sonar.api.utils.Version;

import static org.assertj.core.api.Assertions.assertThat;

class SapCxItemsPluginTest {

    @Test
    void count_extensions() {
        Plugin.Context context = new Plugin.Context(SonarRuntimeImpl.forSonarQube(Version.create(7, 9), SonarQubeSide.SERVER));
        new SapCxItemsPlugin().define(context);
        assertThat(context.getExtensions()).as("Number of extensions for SQ 7.9").hasSize(3);
    }

}

