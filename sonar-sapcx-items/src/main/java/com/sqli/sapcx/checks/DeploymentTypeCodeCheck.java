package com.sqli.sapcx.checks;

import com.sqli.sapcx.types.AttributeName;
import com.sqli.sapcx.types.NodeName;
import org.sonar.check.Rule;
import org.sonarsource.analyzer.commons.xml.XmlFile;
import org.sonarsource.analyzer.commons.xml.checks.SonarXmlCheck;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Rule(key = DeploymentTypeCodeCheck.RULE_KEY)
public class DeploymentTypeCodeCheck extends SonarXmlCheck {

    public static final String RULE_KEY = "DeploymentTypeCodeCheck_RULE_KEY";

    public final String itemTypes = NodeName.ITEMTYPES.name;
    public final String relations = NodeName.RELATIONS.name;

    public final String typegroup = NodeName.TYPEGROUP.name;

    public final String deployment = NodeName.DEPLOYMENT.name;
    public final String targetAttributeName = AttributeName.TYPECODE.name;

    public final int recomendedTypeCode  = 10000;

    @Override
    public void scanFile(XmlFile xmlFile) {
        Document document = xmlFile.getDocument();
        // contain only one node witch is the root node
        List<Node> documentNodeList = XmlFile.asList(document.getChildNodes());

        // get only root Childs Nodes as a List
        // comment are also considered as Node (instanceof Comment) and back to line (instanceof Text)
        List<Node> rootChildNodes = XmlFile.asList(
                documentNodeList.stream()
                        .filter(node -> node instanceof Element)
                        .collect(Collectors.toList())
                        .get(0)
                        .getChildNodes()
        );

        rootChildNodes.stream()
                .filter(node -> node instanceof Element
                        && (node.getNodeName().equals(itemTypes) || node.getNodeName().equals(relations))
                )
                .map(element -> (Element) element)
                .flatMap(element -> XmlFile.children(element).stream())
                .flatMap(element -> {
                    if (element.getNodeName().equals(typegroup)) {
                        return (XmlFile.children(element)).stream();
                    } else {
                        return Stream.of(element);
                    }
                })
                .filter(node -> node instanceof Element)
                .map(element -> (Element) element)
                .filter(element -> element.getElementsByTagName(deployment).getLength() != 0)
                .map(element -> (Element)element.getElementsByTagName(deployment).item(0))
                .filter(element -> Integer.parseInt(element.getAttributeNode(targetAttributeName).getValue()) < recomendedTypeCode)
                .forEach(deploymentNode -> reportIssue(deploymentNode, "Deployment type codes must be greater than 10000."));


    }

}

