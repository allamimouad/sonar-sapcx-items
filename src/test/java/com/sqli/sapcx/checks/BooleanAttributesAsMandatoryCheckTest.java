package com.sqli.sapcx.checks;

import org.junit.jupiter.api.Test;
import org.sonarsource.analyzer.commons.xml.checks.SonarXmlCheckVerifier;


class BooleanAttributesAsMandatoryCheckTest {

    @Test
    void booleanAttributesAsMandatoryShouldBeFine() {
        SonarXmlCheckVerifier.verifyNoIssue("ok-boolean-attributes-as-mandatory-items.xml", new BooleanAttributesAsMandatoryCheck());
    }

    @Test
    void booleanAttributesAsMandatoryShouldBeReported() {
        SonarXmlCheckVerifier.verifyIssues("ko-boolean-attributes-as-mandatory-items.xml", new BooleanAttributesAsMandatoryCheck());
    }

}