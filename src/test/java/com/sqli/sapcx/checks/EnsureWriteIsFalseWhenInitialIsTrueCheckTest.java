package com.sqli.sapcx.checks;

import org.junit.jupiter.api.Test;
import org.sonarsource.analyzer.commons.xml.checks.SonarXmlCheckVerifier;


class EnsureWriteIsFalseWhenInitialIsTrueCheckTest {

    @Test
    void writeAndInitialAttributesShouldBeFine() {
        SonarXmlCheckVerifier.verifyNoIssue("ok-write-initial-items.xml", new EnsureWriteIsFalseWhenInitialIsTrueCheck());
    }

    @Test
    void writeAndInitialAttributesShouldBeReported() {
        SonarXmlCheckVerifier.verifyIssues("ko-write-initial-items.xml", new EnsureWriteIsFalseWhenInitialIsTrueCheck());
    }

}