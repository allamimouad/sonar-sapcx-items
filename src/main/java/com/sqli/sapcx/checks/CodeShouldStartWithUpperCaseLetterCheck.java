package com.sqli.sapcx.checks;

import org.sonar.check.Rule;
import org.sonarsource.analyzer.commons.xml.XmlFile;
import org.sonarsource.analyzer.commons.xml.checks.SimpleXPathBasedCheck;
import org.w3c.dom.Attr;
import org.w3c.dom.Node;

import javax.xml.xpath.XPathExpression;
import java.util.List;

@Rule(key = CodeShouldStartWithUpperCaseLetterCheck.RULE_KEY)
public class CodeShouldStartWithUpperCaseLetterCheck extends SimpleXPathBasedCheck {
    public static final String RULE_KEY = "CodeShouldStartWithUpperCaseLetterCheck_RULE_KEY";
    private final XPathExpression allCodesExpression = getXPathExpression("(//itemtype | //relation | // enumtype)/@code");

    @Override
    public void scanFile(XmlFile file) {
        List<Node> allCodes = evaluateAsList(this.allCodesExpression, file.getDocument());
        allCodes.stream()
                .map(Attr.class::cast)
                .filter(this::isInvalidCode)
                .forEach(this::reportInvalidCode);
    }

    private void reportInvalidCode(Attr invalidCode) {
        reportIssue(invalidCode, "Code attribute of (Itemtype, relation, enumtype) tags should start with upper case letter.");
    }

    private boolean isInvalidCode(Attr code) {
        final char firstLetterOfCode = code.getValue().charAt(0);
        return (Character.isLowerCase(firstLetterOfCode));
    }

}
