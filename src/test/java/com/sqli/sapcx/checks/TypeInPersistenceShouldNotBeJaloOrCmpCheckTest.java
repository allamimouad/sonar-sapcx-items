package com.sqli.sapcx.checks;

import org.junit.jupiter.api.Test;
import org.sonarsource.analyzer.commons.xml.checks.SonarXmlCheckVerifier;

class TypeInPersistenceShouldNotBeJaloOrCmpCheckTest {

    @Test
    void typeInPersistenceShouldBeFine() {
        SonarXmlCheckVerifier.verifyNoIssue("ok-type-is-not-jalo-or-cmp-items.xml", new TypeInPersistenceShouldNotBeJaloOrCmpCheck());
    }

    @Test
    void typeInPersistenceShouldBeReported() {
        SonarXmlCheckVerifier.verifyIssues("ko-type-is-not-jalo-or-cmp-items.xml", new TypeInPersistenceShouldNotBeJaloOrCmpCheck());
    }

}