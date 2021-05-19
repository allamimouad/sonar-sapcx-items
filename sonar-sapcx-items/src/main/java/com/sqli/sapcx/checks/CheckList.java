package com.sqli.sapcx.checks;

import java.util.Arrays;
import java.util.List;

public class CheckList {

    private CheckList() {
    }

    public static List<Class<?>> getCheckClasses() {
        return Arrays.asList(
                TypeInPersistenceShouldNotBeJaloOrCmpCheck.class,
                DeploymentTypeCodeCheck.class
        );
    }

}
