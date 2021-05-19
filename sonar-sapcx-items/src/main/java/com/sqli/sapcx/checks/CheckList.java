package com.sqli.sapcx.checks;

import java.util.Arrays;
import java.util.List;

public class CheckList {

    private CheckList() {
    }

    public static List<Class<?>> getCheckClasses() {
        return Arrays.asList(
                DeploymentTypeCodeCheck.class,
                CodeShouldNotStartWithGeneratedCheck.class,
                CatalogVersionAttributeMustBeUniqueCheck.class
        );
    }

}
