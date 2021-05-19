package com.sqli.sapcx.checks;

import org.junit.jupiter.api.Test;
import org.sonarsource.analyzer.commons.xml.checks.SonarXmlCheckVerifier;


class UseDescriptionTagInAttributeCheckTest {

    @Test
    void descriptionTagInAttributeShouldBeFine() {
        SonarXmlCheckVerifier.verifyNoIssue("ok-attribute-description-items.xml", new UseDescriptionTagInAttributeCheck());
    }

    @Test
    void descriptionTagNotInAttributeShouldBeReported() {
        SonarXmlCheckVerifier.verifyIssues("ko-attribute-description-items.xml", new UseDescriptionTagInAttributeCheck());
    }

}