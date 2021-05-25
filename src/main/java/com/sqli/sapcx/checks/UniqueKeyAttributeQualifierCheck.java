package com.sqli.sapcx.checks;

import org.sonar.check.Rule;
import org.sonarsource.analyzer.commons.xml.XmlFile;
import org.sonarsource.analyzer.commons.xml.checks.SimpleXPathBasedCheck;
import org.w3c.dom.Node;

import javax.xml.xpath.XPathExpression;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Rule(key = UniqueKeyAttributeQualifierCheck.RULE_KEY)
public class UniqueKeyAttributeQualifierCheck extends SimpleXPathBasedCheck {
    public static final String RULE_KEY = "UniqueKeyAttributeQualifierCheck_RULE_KEY";

    private final XPathExpression allItemsWithCustomUniqueKeyExpression = getXPathExpression("//itemtype[./custom-properties/property[@name='uniqueKeyAttributeQualifier']]");
    private final XPathExpression valueOfUniqueKeyAttributeQualifier = getXPathExpression("custom-properties/property[@name='uniqueKeyAttributeQualifier']/value/text()");
    private final XPathExpression nonUniqueAttributesExpression = getXPathExpression("attributes/attribute[not(modifiers) or not(modifiers/@unique) or modifiers/@unique='false']");

    @Override
    public void scanFile(XmlFile file) {
        List<Node> allItemsWithCustomUniqueKey = evaluateAsList(allItemsWithCustomUniqueKeyExpression, file.getDocument());
        allItemsWithCustomUniqueKey.stream()
                .flatMap(this::getNotUniqueAttributesDeclaredInCustomUniqueKey)
                .forEach(this::reportIssue);
    }

    private void reportIssue(Node node) {
        reportIssue(node, "Ensure unique attributes match the catalog unique attribute key, uniqueKeyAttributeQualifier.");
    }

    private Stream<Node> getNotUniqueAttributesDeclaredInCustomUniqueKey(Node item) {
        List<String> customUniqueKeyAttributes = getCustomUniqueKeyAttributes(item);
        List<Node> nonUniqueAttributes = evaluateAsList(nonUniqueAttributesExpression, item);
        return nonUniqueAttributes.stream()
                .filter(attribute -> customUniqueKeyAttributes.contains(getQualifier(attribute)));
    }

    private List<String> getCustomUniqueKeyAttributes(Node item) {
        Node uniqueKeyAttributeNode = evaluateAsList(valueOfUniqueKeyAttributeQualifier, item).iterator().next();
        final String nodeValue = uniqueKeyAttributeNode.getNodeValue();
        return Arrays.asList(nodeValue.substring(1, nodeValue.length() - 1).split(","));
    }

    private String getQualifier(Node attribute) {
        return evaluateAsList(getXPathExpression("@qualifier"), attribute).iterator().next().getNodeValue();
    }

}