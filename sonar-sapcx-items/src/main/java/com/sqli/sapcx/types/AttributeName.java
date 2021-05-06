package com.sqli.sapcx.types;

import org.w3c.dom.Node;

public enum AttributeName {

    CARDINALITY("cardinality"),
    TYPECODE("typecode");


    AttributeName(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public boolean isTypeOf(Node node){

        return this.getName().equals(node.getNodeName());

    }

}
