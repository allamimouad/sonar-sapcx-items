package com.sqli.sapcx.checks;

import java.util.Arrays;
import java.util.List;

public class CheckList {

    private CheckList() {
    }

    public static List<Class<?>> getCheckClasses() {
        return Arrays.asList(
                EnsureWriteIsFalseWhenInitialIsTrueCheck.class,
                BooleanAttributesAsMandatoryCheck.class,
                DoNotUseUnoptimizedAttributesCheck.class,
                DeploymentTagInManyToManyRelationCheck.class,
                AvoidOrderedTrueOnAnySideOfRelationWithCardinalityManyCheck.class,
                TypeInPersistenceShouldNotBeJaloOrCmpCheck.class,
                CollectionTypeSetOnAnyRelationWithCardinalityManyCheck.class,
                UseDescriptionTagInAttributeCheck.class,
                DeploymentTypeCodeCheck.class,
                MandatoryfieldsCheck.class,
                CodeShouldNotStartWithGeneratedCheck.class,
                CatalogVersionAttributeMustBeUniqueCheck.class,
                CodeShouldStartWithUpperCaseLetterCheck.class,
                QualifierShouldStartWithLowerCaseCheck.class,
                UniqueKeyAttributeQualifierCheck.class,
                DefineTypesInOrderOfInheritanceCheck.class
        );
    }

}
