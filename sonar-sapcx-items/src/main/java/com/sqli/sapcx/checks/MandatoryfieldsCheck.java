package com.sqli.sapcx.checks;

import org.sonar.check.Rule;
import org.sonarsource.analyzer.commons.xml.XmlFile;
import org.sonarsource.analyzer.commons.xml.checks.SimpleXPathBasedCheck;
import org.w3c.dom.Node;

import javax.xml.xpath.XPathExpression;
import java.util.List;

@Rule(key = MandatoryfieldsCheck.RULE_KEY)
public class MandatoryfieldsCheck extends SimpleXPathBasedCheck {
    public static final String RULE_KEY = "MandatoryfieldsCheckCheck_RULE_KEY";
    private final XPathExpression allMandatoryfieldsExpression =
            getXPathExpression("//attribute[modifiers[@optional='false' and @initial='false'] and not(defaultvalue)] | //attribute[(modifiers[@optional='false' and not(@initial)] ) and not(defaultvalue)]");

    @Override
    public void scanFile(XmlFile file) {
        List<Node> allMandatoryfields = evaluateAsList(this.allMandatoryfieldsExpression, file.getDocument());
        allMandatoryfields
                .forEach(mandatoryfields -> reportIssue(mandatoryfields, "Ensure mandatory fields (where optional='false') have either initial set to true or a default value defined."));

    }
}
