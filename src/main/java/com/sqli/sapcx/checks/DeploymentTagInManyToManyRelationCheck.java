package com.sqli.sapcx.checks;

import org.sonar.check.Rule;
import org.sonarsource.analyzer.commons.xml.XmlFile;
import org.sonarsource.analyzer.commons.xml.checks.SimpleXPathBasedCheck;
import org.w3c.dom.Node;

import javax.xml.xpath.XPathExpression;
import java.util.List;

@Rule(key = DeploymentTagInManyToManyRelationCheck.RULE_KEY)
public class DeploymentTagInManyToManyRelationCheck extends SimpleXPathBasedCheck {

    public static final String RULE_KEY = "DeploymentTagInManyToManyRelationCheck_RULE_KEY";

    private final XPathExpression allManyToManyRelationWithNoDeploymentExpression = getXPathExpression(
                                                                                            ".//relation[./sourceElement[@cardinality='many'] "
                                                                                            + "and ./targetElement[@cardinality='many'] "
                                                                                            + "and not(./deployment)]"
                                                                                            );

    @Override
    public void scanFile(XmlFile xmlFile) {

        // get all Many-To-Many Relation With No Deployment child
        List<Node> allManyToManyRelation = evaluateAsList(this.allManyToManyRelationWithNoDeploymentExpression, xmlFile.getDocument());

        allManyToManyRelation
                .forEach(relationNode -> reportIssue(relationNode, "Define a deployment table for all many-to-many relations."));

    }

}