package com.sqli.sapcx.checks;

import org.junit.jupiter.api.Test;
import org.sonarsource.analyzer.commons.xml.checks.SonarXmlCheckVerifier;

public class TypeInPersistenceShouldNotBeJaloCheckTest {

    @Test
    void typeInPersistenceShouldBeFine() {
        SonarXmlCheckVerifier.verifyNoIssue("ok-type-is-not-jalo-items.xml", new TypeInPersistenceShouldNotBeJaloCheck());
    }

    @Test
    void typeInPersistenceShouldBeReported() {
        SonarXmlCheckVerifier.verifyIssues("ko-type-is-not-jalo-items.xml", new TypeInPersistenceShouldNotBeJaloCheck());
    }

}