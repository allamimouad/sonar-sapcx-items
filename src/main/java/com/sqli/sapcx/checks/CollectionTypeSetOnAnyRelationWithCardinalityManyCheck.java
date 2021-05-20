package com.sqli.sapcx.checks;

import org.sonar.check.Rule;
import org.sonarsource.analyzer.commons.xml.XmlFile;
import org.sonarsource.analyzer.commons.xml.checks.SimpleXPathBasedCheck;
import org.w3c.dom.Node;

import javax.xml.xpath.XPathExpression;
import java.util.List;

@Rule(key = CollectionTypeSetOnAnyRelationWithCardinalityManyCheck.RULE_KEY)
public class CollectionTypeSetOnAnyRelationWithCardinalityManyCheck extends SimpleXPathBasedCheck {
    public static final String RULE_KEY = "CollectionTypeSetOnAnyRelationWithCardinalityManyCheck_RULE_KEY";

    // note : default of collectiontype is 'collection'
    private final XPathExpression allManyRelationSidesWithNoCollectionTypeSetExpression = getXPathExpression("(//sourceElement|//targetElement)[@cardinality='many' and not(@collectiontype = 'set')]");

    @Override
    public void scanFile(XmlFile file) {
        List<Node> allManyRelationSidesWithNoCollectionTypeSet = evaluateAsList(this.allManyRelationSidesWithNoCollectionTypeSetExpression, file.getDocument());
        allManyRelationSidesWithNoCollectionTypeSet
                .forEach(targetOrSourceElementNode -> reportIssue(targetOrSourceElementNode, "Use collectiontype='set' on any side of a relation that has cardinality='many' where items must not appear in that relation multiple times."));
    }

}
