package com.sqli.sapcx.checks;

import java.util.Arrays;
import java.util.List;

public class CheckList {

    private CheckList() {
    }

    public static List<Class<?>> getCheckClasses() {
        return Arrays.asList(
                AvoidOrderedTrueOnAnySideOfRelationWithCardinalityManyCheck.class,
                TypeInPersistenceShouldNotBeJaloOrCmpCheck.class,
                CollectionTypeSetOnAnyRelationWithCardinalityManyCheck.class,
                UseDescriptionTagInAttributeCheck.class,
                DeploymentTypeCodeCheck.class,
                CodeShouldNotStartWithGeneratedCheck.class,
                MandatoryfieldsCheck.class,
                CodeShouldNotStartWithGeneratedCheck.class,
                QualifierShouldStartWithLowerCaseCheck.class
        );
    }

}
