package com.sqli.sapcx.checks;

import org.junit.jupiter.api.Test;
import org.sonarsource.analyzer.commons.xml.checks.SonarXmlCheckVerifier;


class DoNotUseUnoptimizedAttributesCheckTest {

    @Test
    void doNotUseUnoptimizedAttributesShouldBeFine() {
        SonarXmlCheckVerifier.verifyNoIssue("ok-unoptimized-items.xml", new DoNotUseUnoptimizedAttributesCheck());
    }

    @Test
    void doNotUseUnoptimizedAttributesShouldBeReported() {
        SonarXmlCheckVerifier.verifyIssues("ko-unoptimized-items.xml", new DoNotUseUnoptimizedAttributesCheck());
    }

}