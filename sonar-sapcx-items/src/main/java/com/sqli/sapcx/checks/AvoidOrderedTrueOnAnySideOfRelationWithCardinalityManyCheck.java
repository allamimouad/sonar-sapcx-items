package com.sqli.sapcx.checks;

import org.sonar.check.Rule;
import org.sonarsource.analyzer.commons.xml.XmlFile;
import org.sonarsource.analyzer.commons.xml.checks.SimpleXPathBasedCheck;
import org.w3c.dom.Node;

import javax.xml.xpath.XPathExpression;
import java.util.List;

@Rule(key = AvoidOrderedTrueOnAnySideOfRelationWithCardinalityManyCheck.RULE_KEY)
public class AvoidOrderedTrueOnAnySideOfRelationWithCardinalityManyCheck extends SimpleXPathBasedCheck {
    public static final String RULE_KEY = "AvoidOrderedTrueOnAnySideOfRelationWithCardinalityManyCheck_RULE_KEY";

    private final XPathExpression allOrderedTrueExpression = getXPathExpression("(//sourceElement|//targetElement)[@cardinality='many' and @ordered='true']/@ordered");

    @Override
    public void scanFile(XmlFile file) {
        List<Node> allSourceAndTargetElement = evaluateAsList(this.allOrderedTrueExpression, file.getDocument());
        allSourceAndTargetElement
                .forEach(codeAttr -> reportIssue(codeAttr, "Avoid setting ordered='true' on any side of a relation that has cardinality='many' unless absolutely necessary."));
    }

}