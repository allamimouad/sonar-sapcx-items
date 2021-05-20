package com.sqli.sapcx.checks;

import org.sonar.check.Rule;
import org.sonarsource.analyzer.commons.xml.XmlFile;
import org.sonarsource.analyzer.commons.xml.checks.SimpleXPathBasedCheck;
import org.w3c.dom.Node;

import javax.xml.xpath.XPathExpression;
import java.util.List;

@Rule(key = CodeShouldNotStartWithGeneratedCheck.RULE_KEY)
public class CodeShouldNotStartWithGeneratedCheck extends SimpleXPathBasedCheck {
    public static final String RULE_KEY = "CodeShouldNotStartWithGeneratedCheck_RULE_KEY";

    private final XPathExpression allItemTypeCodesExpression = getXPathExpression("//itemtype/@code[starts-with(.,'Generated')]");

    @Override
    public void scanFile(XmlFile file) {
        List<Node> allItemTypeCodes = evaluateAsList(this.allItemTypeCodesExpression, file.getDocument());
        allItemTypeCodes
                .forEach(codeAttr -> reportIssue(codeAttr, "Do not start Item type code with Generated."));
    }

}
