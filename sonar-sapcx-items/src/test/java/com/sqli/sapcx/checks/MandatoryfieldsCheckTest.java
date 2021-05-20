package com.sqli.sapcx.checks;

import org.junit.jupiter.api.Test;
import org.sonarsource.analyzer.commons.xml.checks.SonarXmlCheckVerifier;

public class MandatoryfieldsCheckTest {
    @Test
    void mandatoryfieldsMusthaveTrueInitialOrDefaultValueShouldBeFine() {
        SonarXmlCheckVerifier.verifyNoIssue("ok-mandatory-fields-items.xml", new MandatoryfieldsCheck());
    }

    @Test
    void MandatoryfieldsMusthaveTrueInitialOrDefaultValueShouldBeReported() {
        SonarXmlCheckVerifier.verifyIssues("ko-mandatory-fields-items.xml", new MandatoryfieldsCheck());
    }
}
