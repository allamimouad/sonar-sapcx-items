package com.sqli.sapcx.checks;

import com.sqli.sapcx.Utils;
import com.sqli.sapcx.types.NodeName;
import org.sonar.check.Rule;
import org.sonarsource.analyzer.commons.xml.XmlFile;
import org.sonarsource.analyzer.commons.xml.checks.SonarXmlCheck;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.List;

@Rule(key = DeploymentTagInManyToManyRelationcheck.RULE_KEY)
public class DeploymentTagInManyToManyRelationcheck extends SonarXmlCheck {

    public static final String RULE_KEY = "DeploymentTagInManyToManyRelationcheck_RULE_KEY";

    @Override
    public void scanFile(XmlFile xmlFile) {

        // get root children Nodes as a List
        List<Node> rootChildNodes = Utils.getRootChildrenNodes(xmlFile);

        rootChildNodes.stream()
                .filter( node -> node instanceof Element )
                .filter(NodeName.RELATIONS::isTypeOf)
                .flatMap(relationsNode -> XmlFile.children(relationsNode).stream())
                .filter(relationNode -> relationNode instanceof Element)
                .map(relationNode -> (Element)relationNode)
                .filter(Utils::isManyToManyRelation)
                .filter(relationNode-> ! Utils.haveDeploymentNode(relationNode))
                .forEach(relationNode -> reportIssue(relationNode, "Define a deployment table for all many-to-many relations"));

    }


}