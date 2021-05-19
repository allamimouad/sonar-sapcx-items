package com.sqli.sapcx.checks;

import org.junit.jupiter.api.Test;
import org.sonarsource.analyzer.commons.xml.checks.SonarXmlCheckVerifier;

public class CodeShouldStartWithUpperCaseLetterCheckTest {
    @Test
    void codeShouldStartWithUpperCaseLetterShouldBeFine() {
        SonarXmlCheckVerifier.verifyNoIssue("ok-code-itemtype-relation-enumtype-items.xml", new CodeShouldStartWithUpperCaseLetterCheck());
    }

    @Test
    void codeShouldStartWithUpperCaseLetterShouldBeReported() {
        SonarXmlCheckVerifier.verifyIssues("ko-code-itemtype-relation-enumtype-items.xml", new CodeShouldStartWithUpperCaseLetterCheck());
    }
}
