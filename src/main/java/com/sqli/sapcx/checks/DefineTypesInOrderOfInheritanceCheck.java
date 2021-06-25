package com.sqli.sapcx.checks;

import org.sonar.check.Rule;
import org.sonarsource.analyzer.commons.xml.XmlFile;
import org.sonarsource.analyzer.commons.xml.checks.SimpleXPathBasedCheck;
import org.w3c.dom.Node;
import javax.xml.xpath.XPathExpression;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Rule(key = DefineTypesInOrderOfInheritanceCheck.RULE_KEY)
public class DefineTypesInOrderOfInheritanceCheck extends SimpleXPathBasedCheck {
    public static final String RULE_KEY = "DefineTypesInOrderOfInheritanceCheck_RULE_KEY";

    private final XPathExpression itemTypeNodesExpression = getXPathExpression("//itemtype");
    private final XPathExpression extendsAttrOfitemType= getXPathExpression("@extends");
    private final XPathExpression codeOfItemTypeExpression = getXPathExpression("@code");

    @Override
    public void scanFile(XmlFile file) {
        final List<String> allCodes = new ArrayList<>();
        final Map<String, Node> allExtendsNeedCheck = new HashMap<>();

        List<Node> itemTypeNodes = evaluateAsList(itemTypeNodesExpression, file.getDocument());

        itemTypeNodes.forEach(itemType -> {
            final String extendsValue = (evaluateAsList(extendsAttrOfitemType, itemType).iterator().next()).getNodeValue();
            final String codeValue = (evaluateAsList(codeOfItemTypeExpression, itemType).iterator().next()).getNodeValue();
            if(!(allCodes.contains(extendsValue) || allExtendsNeedCheck.containsKey(extendsValue)))
                allExtendsNeedCheck.put(extendsValue, itemType);
            allCodes.add(codeValue);
        });

        allExtendsNeedCheck.keySet().forEach(extendsToCheck -> {
            if(allCodes.contains(extendsToCheck))
                reportIssue(allExtendsNeedCheck.get(extendsToCheck),  "Define types in order of inheritance.");
        });

    }
}