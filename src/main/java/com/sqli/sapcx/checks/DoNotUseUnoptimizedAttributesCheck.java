package com.sqli.sapcx.checks;

import org.sonar.check.Rule;
import org.sonarsource.analyzer.commons.xml.XmlFile;
import org.sonarsource.analyzer.commons.xml.checks.SimpleXPathBasedCheck;
import org.w3c.dom.Node;

import javax.xml.xpath.XPathExpression;
import java.util.List;

@Rule(key = DoNotUseUnoptimizedAttributesCheck.RULE_KEY)
public class DoNotUseUnoptimizedAttributesCheck extends SimpleXPathBasedCheck {

    public static final String RULE_KEY = "DoNotUseUnoptimizedAttributesCheck_RULE_KEY";

    private final XPathExpression allDontOptimizeAttributesExpression = getXPathExpression("//attribute/modifiers[@dontOptimize='true']/@dontOptimize");

    @Override
    public void scanFile(XmlFile file) {
        List<Node> allDontOptimizeAttributes = evaluateAsList(this.allDontOptimizeAttributesExpression, file.getDocument());
        allDontOptimizeAttributes
                .forEach(dontOptimizeAttribute -> reportIssue(dontOptimizeAttribute, "Do not use unoptimized attributes."));
    }

}