package com.sqli.sapcx.checks;

import java.util.Arrays;
import java.util.List;

public class CheckList {

    private CheckList() {
    }

    public static List<Class<?>> getCheckClasses() {
        return Arrays.asList(
                DeploymentTagInManyToManyRelationCheck.class,
                AvoidOrderedTrueOnAnySideOfRelationWithCardinalityManyCheck.class,
                TypeInPersistenceShouldNotBeJaloOrCmpCheck.class,
                CollectionTypeSetOnAnyRelationWithCardinalityManyCheck.class,
                UseDescriptionTagInAttributeCheck.class,
                DeploymentTypeCodeCheck.class,
                CodeShouldNotStartWithGeneratedCheck.class,
                CatalogVersionAttributeMustBeUniqueCheck.class,
                CodeShouldStartWithUpperCaseLetterCheck.class,
                QualifierShouldStartWithLowerCaseCheck.class
        );
    }

}
