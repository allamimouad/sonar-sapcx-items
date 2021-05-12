package com.sqli.sapcx.checks;
import org.sonar.check.Rule;
import org.sonarsource.analyzer.commons.xml.XmlFile;
import org.sonarsource.analyzer.commons.xml.checks.SimpleXPathBasedCheck;
import org.w3c.dom.Attr;
import org.w3c.dom.Node;

import javax.xml.xpath.XPathExpression;
import java.util.List;

@Rule(key = QualifierShouldStartWithLowerCaseCheck.RULE_KEY)
public class QualifierShouldStartWithLowerCaseCheck extends SimpleXPathBasedCheck {
    public static final String RULE_KEY = "QualifierShouldStartWithLowerCaseCheck_RULE_KEY";

    private final XPathExpression allQualifiersExpression =
            getXPathExpression("(//relation/sourceElement | // targetElement | //itemtype/attributes/attribute)/@qualifier");
    @Override
    public void scanFile(XmlFile xmlFile) {
        List<Node> allQualifiers = evaluateAsList(this.allQualifiersExpression, xmlFile.getDocument());
        allQualifiers.stream()
                .map(Attr.class::cast)
                .filter(this::isInvalidQualifier)
                .forEach(this::reportInvalidQualifier);
    }
    private void reportInvalidQualifier(Attr invalidQualifier) {
        reportIssue(invalidQualifier, "Qualifier attribute" + invalidQualifier.getValue() + "should start with lower case letter.");
    }

    private boolean isInvalidQualifier(Attr qualifier) {
        final char FirstLetterOfQualifier= qualifier.getValue().charAt(0);
        return (Character.isLowerCase(FirstLetterOfQualifier));
    }

}
