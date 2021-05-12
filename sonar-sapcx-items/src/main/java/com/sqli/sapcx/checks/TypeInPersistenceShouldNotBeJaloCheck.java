package com.sqli.sapcx.checks;

import org.sonar.check.Rule;
import org.sonarsource.analyzer.commons.xml.XmlFile;
import org.sonarsource.analyzer.commons.xml.checks.SimpleXPathBasedCheck;
import org.w3c.dom.Node;

import javax.xml.xpath.XPathExpression;
import java.util.List;

@Rule(key = TypeInPersistenceShouldNotBeJaloCheck.RULE_KEY)
public class TypeInPersistenceShouldNotBeJaloCheck extends SimpleXPathBasedCheck {

    public static final String RULE_KEY = "TypeInPersistenceShouldNotBeJaloCheck_RULE_KEY";

    private final XPathExpression allTypeOfPersistenceEqualsToJaloExpression = getXPathExpression("//persistence[@type='jalo']/@type");

    @Override
    public void scanFile(XmlFile file) {

        List<Node> allTypeOfPersistenceEqualsToJalo = evaluateAsList(this.allTypeOfPersistenceEqualsToJaloExpression, file.getDocument());
        allTypeOfPersistenceEqualsToJalo
                .forEach(typeAttr -> reportIssue(typeAttr, "Use an attribute persistence type of dynamic, instead of jalo."));

    }
}
