
package com.sqli.sapcx;

import com.sqli.sapcx.types.AttributeName;
import com.sqli.sapcx.types.NodeName;
import org.sonarsource.analyzer.commons.xml.XmlFile;
import org.sonarsource.analyzer.commons.xml.XmlTextRange;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utils {
    private Utils() {
        // utility class, forbidden constructor
    }

    public static String[] splitLines(String text) {
        return text.split("(\r)?\n|\r", -1);
    }

    /**
     * Check if element is self closing: &lt;foo ... /&gt;
     *
     * @param element element to check
     * @return true if element is self closing, false otherwise
     */
    public static boolean isSelfClosing(Element element) {
        XmlTextRange startLocation = XmlFile.startLocation(element);
        XmlTextRange endLocation = XmlFile.endLocation(element);
        return startLocation.getEndLine() == endLocation.getEndLine()
                && startLocation.getEndColumn() == endLocation.getEndColumn();
    }

    //this method make itemtype and typegroupe children(itemtype)
    // at the same stream level
    public static Stream<Node> flatMapTypeGroupToItemType(Node node){

        if (NodeName.TYPEGROUP.isTypeOf(node)) {
            return (XmlFile.children(node)).stream();
        } else {
            return Stream.of(node);
        }

    }

    public static boolean haveDeploymentNode (Element element){
        return element.getElementsByTagName(NodeName.DEPLOYMENT.getName()).getLength() != 0;
    }

    public static Element getFirstNodeOccurrenceByName (Element element,String name){
        return (Element)element.getElementsByTagName(name).item(0);
    }

    // this method return a List of the root Node
    public static List<Node> getRootChildrenNodes(XmlFile xmlFile){

        Document document = xmlFile.getDocument();
        // contain only one node witch is the root node
        List<Node> documentNodeList = XmlFile.asList(document.getChildNodes());

        // get only root children Nodes as a List
        // comment are also considered as Node (instanceof Comment) and back to line (instanceof Text)
        return XmlFile.asList(
                documentNodeList.stream()
                        .filter(node -> node instanceof Element)
                        .collect(Collectors.toList())
                        .get(0)
                        .getChildNodes()
        );

    }

    // this method verify if a relation node is Many to Many
    public static boolean isManyToManyRelation(Element element){

        Element sourceElementNode = (Element)element.getElementsByTagName(NodeName.SOURCEELEMENT.getName()).item(0);
        Element targetElementNode = (Element)element.getElementsByTagName(NodeName.TARGETELEMENT.getName()).item(0);

        if (sourceElementNode == null || targetElementNode == null ) {
            return false;
        }
        else{
            return sourceElementNode.getAttribute(AttributeName.CARDINALITY.getName()).toLowerCase().equals("many")
                    && targetElementNode.getAttribute(AttributeName.CARDINALITY.getName()).toLowerCase().equals("many");
        }

    }


}