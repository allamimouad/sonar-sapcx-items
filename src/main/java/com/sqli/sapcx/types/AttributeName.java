package com.sqli.sapcx.types;

import org.w3c.dom.Node;

public enum AttributeName {

    CARDINALITY("cardinality"),
    TYPECODE("typecode");


    private String name;

    AttributeName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isTypeOf(Node node) {

        return this.getName().equals(node.getNodeName());

    }

}
