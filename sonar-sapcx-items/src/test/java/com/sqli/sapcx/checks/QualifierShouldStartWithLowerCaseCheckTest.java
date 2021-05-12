package com.sqli.sapcx.checks;

import org.junit.jupiter.api.Test;
import org.sonarsource.analyzer.commons.xml.checks.SonarXmlCheckVerifier;

public class QualifierShouldStartWithLowerCaseCheckTest {
    @Test
    void qualifierShouldStartWithLowerCaseBeFine() {
        SonarXmlCheckVerifier.verifyNoIssue("ok-typecode-items.xml", new DeploymentTypeCodeCheck());
    }

    @Test
    void qualifierShouldStartWithLowerCaseBeReported() {
        SonarXmlCheckVerifier.verifyIssues("ko-typecode-items.xml", new DeploymentTypeCodeCheck());
    }
}
