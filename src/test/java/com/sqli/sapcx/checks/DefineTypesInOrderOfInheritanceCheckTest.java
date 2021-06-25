package com.sqli.sapcx.checks;

import org.junit.jupiter.api.Test;
import org.sonarsource.analyzer.commons.xml.checks.SonarXmlCheckVerifier;

public class DefineTypesInOrderOfInheritanceCheckTest {
    @Test
    void defineTypesInOrderOfInheritanceShouldBeFine() {
        SonarXmlCheckVerifier.verifyNoIssue("ok-typesInOrderOfInheritance-items.xml", new DefineTypesInOrderOfInheritanceCheck());
    }

    @Test
    void defineTypesInOrderOfInheritanceShouldBeReported() {
        SonarXmlCheckVerifier.verifyIssues("ko-typesInOrderOfInheritance-items.xml", new DefineTypesInOrderOfInheritanceCheck());
    }
}
