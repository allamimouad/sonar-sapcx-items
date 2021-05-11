package com.sqli.sapcx;

import com.sqli.sapcx.checks.CheckList;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonarsource.analyzer.commons.RuleMetadataLoader;

public final class SapCxItemsRulesDefinition implements RulesDefinition {

    @Override
    public void define(Context context) {
        NewRepository repository = context.createRepository(SapCxItemsPreperties.REPOSITORY_KEY, SapCxItemsPreperties.XML_KEY).setName(SapCxItemsPreperties.REPOSITORY_NAME);
        RuleMetadataLoader ruleMetadataLoader = new RuleMetadataLoader(SapCxItemsPreperties.ITEMS_RESOURCE_PATH, SapCxItemsPreperties.SQLI_WAY_PATH);
        ruleMetadataLoader.addRulesByAnnotatedClass(repository, CheckList.getCheckClasses());
        repository.done();
    }
}
