package com.sqli.sapcx.checks;

import org.junit.jupiter.api.Test;
import org.sonarsource.analyzer.commons.xml.checks.SonarXmlCheckVerifier;

class DeploymentTypeCodeCheckTest {

    @Test
    void deplotmentTypeCodesGreaterThan10000ShouldBeFine() {
        SonarXmlCheckVerifier.verifyNoIssue("ok-typecode-items.xml", new DeploymentTypeCodeCheck());
    }

    @Test
    void deplotmentTypeCodesLessThan10000ShouldBeReported() {
        SonarXmlCheckVerifier.verifyIssues("ko-typecode-items.xml", new DeploymentTypeCodeCheck());
    }
}