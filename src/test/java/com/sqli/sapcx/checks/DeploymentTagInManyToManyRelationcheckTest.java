package com.sqli.sapcx.checks;

import org.junit.jupiter.api.Test;
import org.sonarsource.analyzer.commons.xml.checks.SonarXmlCheckVerifier;


class DeploymentTagInManyToManyRelationcheckTest {

    @Test
    void deploymentExistInManyToManyShouldBeFine() {
        SonarXmlCheckVerifier.verifyNoIssue("ok-deployment-in-many-to-many-items.xml", new DeploymentTagInManyToManyRelationCheck());
    }

    @Test
    void deploymentMissingInManyToManyShouldBeReported() {
        SonarXmlCheckVerifier.verifyIssues("ko-deployment-in-many-to-many-items.xml", new DeploymentTagInManyToManyRelationCheck());
    }

}