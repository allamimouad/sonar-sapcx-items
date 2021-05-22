package com.sqli.sapcx.checks;

import org.sonar.check.Rule;
import org.sonarsource.analyzer.commons.xml.XmlFile;
import org.sonarsource.analyzer.commons.xml.checks.SimpleXPathBasedCheck;
import org.w3c.dom.Node;

import javax.xml.xpath.XPathExpression;
import java.util.List;

@Rule(key = EnsureWriteIsFalseWhenInitialIsTrueCheck.RULE_KEY)
public class EnsureWriteIsFalseWhenInitialIsTrueCheck  extends SimpleXPathBasedCheck {
    public static final String RULE_KEY = "EnsureWriteIsFalseWhenInitialIsTrueCheck_RULE_KEY";
    private final XPathExpression allUselessInitialAttributesExpression =
            getXPathExpression("//modifiers[not(@write = 'false') and @initial='true']/@initial");

    @Override
    public void scanFile(XmlFile file) {
        List<Node> allUselessInitialAttributes = evaluateAsList(this.allUselessInitialAttributesExpression, file.getDocument());
        allUselessInitialAttributes
                .forEach(uselessInitialAttribute -> reportIssue(uselessInitialAttribute, "Ensure immutable fields (where write='false') have initial set to true."));

    }
}