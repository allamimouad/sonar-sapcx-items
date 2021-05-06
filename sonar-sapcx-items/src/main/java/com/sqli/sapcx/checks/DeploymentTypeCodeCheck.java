package com.sqli.sapcx.checks;

import com.sqli.sapcx.Utils;
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

    public final int MINIMUM_TYPE_CODE  = 10000;

    @Override
    public void scanFile(XmlFile xmlFile) {

        // get root children Nodes as a List
        List<Node> rootChildNodes = Utils.getRootChildrenNodes(xmlFile);

        rootChildNodes.stream()
                .filter(node -> node instanceof Element )
                .filter(node -> NodeName.ITEMTYPES.isTypeOf(node) || NodeName.RELATIONS.isTypeOf(node))
                .flatMap(element -> XmlFile.children(element).stream())
                .flatMap(Utils::flatMapTypeGroupToItemType) //relation nodes are also included here
                .filter(node -> node instanceof Element)
                .map(element -> (Element) element)
                .filter(Utils::haveDeploymentNode)
                .map(element -> Utils.getFirstNodeOccurrenceByName(element,NodeName.DEPLOYMENT.getName()))
                .filter(this::haveRecomendedTypeCode)
                .forEach(deploymentNode -> reportIssue(deploymentNode, "Deployment type codes must be greater than 10000."));

    }

    // this method verify if deploymentNode have the recommended typeCode
    private boolean haveRecomendedTypeCode (Element deploymentNode){
        return Integer.parseInt(deploymentNode.getAttributeNode(AttributeName.TYPECODE.getName()).getValue()) < MINIMUM_TYPE_CODE;
    }


}