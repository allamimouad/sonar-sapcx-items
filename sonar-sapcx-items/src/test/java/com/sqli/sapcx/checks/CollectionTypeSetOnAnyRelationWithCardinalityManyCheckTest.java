package com.sqli.sapcx.checks;

import org.junit.jupiter.api.Test;
import org.sonarsource.analyzer.commons.xml.checks.SonarXmlCheckVerifier;


class CollectionTypeSetOnAnyRelationWithCardinalityManyCheckTest {

    @Test
    void collectionTypeSetOnAnyRelationWithCardinalityManyShouldBeFine() {
        SonarXmlCheckVerifier.verifyNoIssue("ok-collection-type-items.xml", new CollectionTypeSetOnAnyRelationWithCardinalityManyCheck());
    }

    @Test
    void collectionTypeSetOnAnyRelationWithCardinalityManyShouldBeReported() {
        SonarXmlCheckVerifier.verifyIssues("ko-collection-type-items.xml", new CollectionTypeSetOnAnyRelationWithCardinalityManyCheck());
    }

}