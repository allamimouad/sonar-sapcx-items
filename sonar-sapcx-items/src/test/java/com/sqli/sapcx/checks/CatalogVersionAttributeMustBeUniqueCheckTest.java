package com.sqli.sapcx.checks;

import org.junit.jupiter.api.Test;
import org.sonarsource.analyzer.commons.xml.checks.SonarXmlCheckVerifier;

public class CatalogVersionAttributeMustBeUniqueCheckTest {
    @Test
    void catalogVersionAttributeMustBeUniqueShouldBeFine() {
        SonarXmlCheckVerifier.verifyNoIssue("ok-catalogVersion-aware-items.xml", new CatalogVersionAttributeMustBeUniqueCheck());
    }

    @Test
    void catalogVersionAttributeMustBeUniqueShouldBeReported() {
        SonarXmlCheckVerifier.verifyIssues("ko-catalogVersion-aware-items.xml", new CatalogVersionAttributeMustBeUniqueCheck());
    }
}
