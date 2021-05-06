package com.sqli.sapcx.enums;

public enum NodeName {

    RELATIONS("relations"),
    RELATION("relation"),
    DEPLOYMENT("deployment"),
    ITEMTYPES("itemtypes"),
    TYPEGROUP("typegroup"),
    ITEMTYPE("itemtype"),
    SOURCEELEMENT("sourceElement"),
    TARGETELEMENT("targetElement");

    public String name;

    NodeName(String name) {
        this.name = name;
    }

}
