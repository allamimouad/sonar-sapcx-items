package com.sqli.sapcx.types;


import org.w3c.dom.Node;

public enum NodeName {

    RELATIONS("relations"),
    RELATION("relation"),
    DEPLOYMENT("deployment"),
    ITEMTYPES("itemtypes"),
    TYPEGROUP("typegroup"),
    ITEMTYPE("itemtype"),
    SOURCEELEMENT("sourceElement"),
    TARGETELEMENT("targetElement"),
    ATTRIBUTES("attributes"),
    ATTRIBUTE("attribute"),
    PERSISTENCE("persistence");

    private String name;

    public String getName() {
        return name;
    }

    NodeName(String name) {
        this.name = name;
    }

    public boolean isTypeOf(Node node){

        return this.getName().equals(node.getNodeName());

    }

}