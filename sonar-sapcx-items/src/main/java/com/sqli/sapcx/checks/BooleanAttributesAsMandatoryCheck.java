package com.sqli.sapcx.checks;

import org.sonar.check.Rule;
import org.sonarsource.analyzer.commons.xml.XmlFile;
import org.sonarsource.analyzer.commons.xml.checks.SimpleXPathBasedCheck;
import org.w3c.dom.Node;

import javax.xml.xpath.XPathExpression;
import java.util.List;

@Rule(key = BooleanAttributesAsMandatoryCheck.RULE_KEY)
public class BooleanAttributesAsMandatoryCheck extends SimpleXPathBasedCheck {
    public static final String RULE_KEY = "BooleanAttributesAsMandatoryCheck_RULE_KEY";

    // Not that an the default value of optional is true
    // and if a it have default value of False or True there is no problem with optional
    private final XPathExpression allOptionalBooleanAttributesWithNoDefaultValueExpression = getXPathExpression("//attribute[@type='java.lang.Boolean' "
                                                                                                + "and ( not(./defaultvalue) " + "and (not(./modifiers) or ./modifiers[not(@optional='false')])) ]");

    @Override
    public void scanFile(XmlFile file) {
        List<Node> allOptionalBooleanAttributesWithNoDefaultValue = evaluateAsList(this.allOptionalBooleanAttributesWithNoDefaultValueExpression, file.getDocument());
        allOptionalBooleanAttributesWithNoDefaultValue
                .forEach(mandatoryAttributeNode -> reportIssue(mandatoryAttributeNode, "Define Boolean attributes as mandatory."));
    }
}