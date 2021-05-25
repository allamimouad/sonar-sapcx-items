package com.sqli.sapcx.checks;

import org.junit.jupiter.api.Test;
import org.sonarsource.analyzer.commons.xml.checks.SonarXmlCheckVerifier;

public class UniqueKeyAttributeQualifierCheckTest {
    @Test
    void uniqueKeyAttributeQualifierShouldBeFine() {
        SonarXmlCheckVerifier.verifyNoIssue("ok-uniqueKeyAttributeQualifier-items.xml", new UniqueKeyAttributeQualifierCheck());
    }

    @Test
    void uniqueKeyAttributeQualifierShouldBeReported() {
        SonarXmlCheckVerifier.verifyIssues("ko-uniqueKeyAttributeQualifier-items.xml", new UniqueKeyAttributeQualifierCheck());
    }
}
