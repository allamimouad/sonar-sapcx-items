package com.sqli.sapcx.checks;

import org.junit.jupiter.api.Test;
import org.sonarsource.analyzer.commons.xml.checks.SonarXmlCheckVerifier;


public class QualifierShouldStartWithLowerCaseCheckTest {
    @Test
    void qualifierStartWithLowerCaseBeFine() {
        SonarXmlCheckVerifier.verifyNoIssue("ok-qualifier-items.xml", new QualifierShouldStartWithLowerCaseCheck());
    }
    @Test
    void qualifierStartWithLowerCaseBeReported() {
        SonarXmlCheckVerifier.verifyIssues("ko-typecode-items.xml", new DeploymentTypeCodeCheck());
    }

}
