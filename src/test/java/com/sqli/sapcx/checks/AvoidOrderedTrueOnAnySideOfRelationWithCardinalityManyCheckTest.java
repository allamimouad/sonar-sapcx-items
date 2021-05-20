package com.sqli.sapcx.checks;


import org.junit.jupiter.api.Test;
import org.sonarsource.analyzer.commons.xml.checks.SonarXmlCheckVerifier;

class AvoidOrderedTrueOnAnySideOfRelationWithCardinalityManyCheckTest {

    @Test
    void orderedInSourceOrTargetShouldBeFine() {
        SonarXmlCheckVerifier.verifyNoIssue("ok-ordered-in-source-or-target-items.xml", new AvoidOrderedTrueOnAnySideOfRelationWithCardinalityManyCheck());
    }

    @Test
    void orderedInSourceOrTargetShouldBeReported() {
        SonarXmlCheckVerifier.verifyIssues("ko-ordered-in-source-or-target-items.xml", new AvoidOrderedTrueOnAnySideOfRelationWithCardinalityManyCheck());
    }

}