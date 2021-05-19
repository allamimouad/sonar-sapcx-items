package com.sqli.sapcx.checks;

import org.sonar.check.Rule;
import org.sonarsource.analyzer.commons.xml.XmlFile;
import org.sonarsource.analyzer.commons.xml.checks.SimpleXPathBasedCheck;
import org.w3c.dom.Attr;
import org.w3c.dom.Node;

import javax.xml.xpath.XPathExpression;
import java.util.List;

@Rule(key = TypeInPersistenceShouldNotBeJaloOrCmpCheck.RULE_KEY)
public class TypeInPersistenceShouldNotBeJaloOrCmpCheck extends SimpleXPathBasedCheck {

    public static final String RULE_KEY = "TypeInPersistenceShouldNotBeJaloOrCmpCheck_RULE_KEY";

    private final XPathExpression allTypeOfPersistenceEqualsToJaloOrCmpExpression = getXPathExpression("//persistence[@type='jalo' or @type='cmp']/@type");

    @Override
    public void scanFile(XmlFile file) {

        List<Node> allTypeOfPersistenceEqualsToJaloOrCmp = evaluateAsList(this.allTypeOfPersistenceEqualsToJaloOrCmpExpression, file.getDocument());

        allTypeOfPersistenceEqualsToJaloOrCmp.stream()
                .map(Attr.class::cast)
                .forEach(this::reportDeprecatedType);

    }

    public void reportDeprecatedType(Attr typeAttribute){

        String typeAttributeValue = typeAttribute.getValue();

        if (typeAttributeValue.equals("cmp"))
            reportIssue(typeAttribute, "The cmp persistence mode is deprecated and must not be used anymore.");
        else if (typeAttributeValue.equals("jalo"))
            reportIssue(typeAttribute, "Use an attribute persistence type of dynamic, instead of jalo.");

    }

}
