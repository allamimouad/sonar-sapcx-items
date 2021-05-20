package com.sqli.sapcx.checks;

import org.sonar.check.Rule;
import org.sonarsource.analyzer.commons.xml.XmlFile;
import org.sonarsource.analyzer.commons.xml.checks.SimpleXPathBasedCheck;
import org.w3c.dom.Node;

import javax.xml.xpath.XPathExpression;
import java.util.List;

@Rule(key = UseDescriptionTagInAttributeCheck.RULE_KEY)
public class UseDescriptionTagInAttributeCheck extends SimpleXPathBasedCheck {
    public static final String RULE_KEY = "UseDescriptionTagInAttributeCheck_RULE_KEY";

    private final XPathExpression allAttributesWithNoDescriptionExpression = getXPathExpression("//attribute[not(./description)]");

    @Override
    public void scanFile(XmlFile file) {
        List<Node> allAttributesWithNoDescription = evaluateAsList(this.allAttributesWithNoDescriptionExpression, file.getDocument());
        allAttributesWithNoDescription
                .forEach(attributeWithNoDescriptionNode -> reportIssue(attributeWithNoDescriptionNode, "Describe the purpose and intended use of an attribute in a description tag."));
    }

}
