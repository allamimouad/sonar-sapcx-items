package com.sqli.sapcx.checks;

import org.sonar.check.Rule;
import org.sonarsource.analyzer.commons.xml.XmlFile;
import org.sonarsource.analyzer.commons.xml.checks.SimpleXPathBasedCheck;
import org.w3c.dom.Attr;
import org.w3c.dom.Node;

import javax.xml.xpath.XPathExpression;
import java.util.List;


@Rule(key = DeploymentTypeCodeCheck.RULE_KEY)
public class DeploymentTypeCodeCheck extends SimpleXPathBasedCheck {

    public static final String RULE_KEY = "DeploymentTypeCodeCheck_RULE_KEY";
    public static final int MINIMUM_TYPE_CODE = 10000;

    private final XPathExpression allTypeCodesExpression = getXPathExpression(".//@typecode");

    @Override
    public void scanFile(XmlFile file) {
        List<Node> allTypeCodes = evaluateAsList(this.allTypeCodesExpression, file.getDocument());
        allTypeCodes.stream()
                .map(Attr.class::cast)
                .filter(this::isInvalidTypeCode)
                .forEach(this::reportInvalidTypeCode);
    }

    private void reportInvalidTypeCode(Attr invalidTypeCode) {
        reportIssue(invalidTypeCode, "Deployment type codes must be greater than " + MINIMUM_TYPE_CODE + ".");
    }

    private boolean isInvalidTypeCode(Attr typecode) {
        return Integer.parseInt(typecode.getValue()) < MINIMUM_TYPE_CODE;
    }

}