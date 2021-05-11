package com.sqli.sapcx.checks;

import org.junit.jupiter.api.Test;
import org.sonarsource.analyzer.commons.xml.checks.SonarXmlCheckVerifier;

import java.nio.file.Paths;

class DeploymentTypeCodeCheckTest {

    @Test
    void deplotmentTypeCodesGreaterThan10000ShouldBeFine() {
        SonarXmlCheckVerifier.verifyNoIssue(Paths.get("ok-typecode-items.xml").toString(), new DeploymentTypeCodeCheck());
    }

    @Test
    void deplotmentTypeCodesLessThan10000ShouldBeReported() {
        SonarXmlCheckVerifier.verifyIssues(Paths.get("ko-typecode-items.xml").toString(), new DeploymentTypeCodeCheck());
    }
}