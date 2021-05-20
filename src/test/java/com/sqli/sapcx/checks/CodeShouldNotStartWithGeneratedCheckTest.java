package com.sqli.sapcx.checks;

import org.junit.jupiter.api.Test;
import org.sonarsource.analyzer.commons.xml.checks.SonarXmlCheckVerifier;

public class CodeShouldNotStartWithGeneratedCheckTest {
    @Test
    void codeShouldNotStartWithGeneratedCheckShouldBeFine() {
        SonarXmlCheckVerifier.verifyNoIssue("ok-itemtype-code-items.xml", new CodeShouldNotStartWithGeneratedCheck());
    }

    @Test
    void codeShouldNotStartWithGeneratedCheckBeReported() {
        SonarXmlCheckVerifier.verifyIssues("ko-itemtype-code-items.xml", new CodeShouldNotStartWithGeneratedCheck());
    }
}
