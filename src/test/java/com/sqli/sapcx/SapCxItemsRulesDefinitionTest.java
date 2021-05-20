package com.sqli.sapcx;

import com.sqli.sapcx.checks.CheckList;
import org.junit.jupiter.api.Test;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.api.server.rule.RulesDefinition.Rule;

import static org.assertj.core.api.Assertions.assertThat;

class SapCxItemsRulesDefinitionTest {

    @Test
    void test() {
        SapCxItemsRulesDefinition rulesDefinition = new SapCxItemsRulesDefinition();
        RulesDefinition.Context context = new RulesDefinition.Context();
        rulesDefinition.define(context);
        RulesDefinition.Repository repository = context.repository("sapcx-items");

        assertThat(repository.name()).isEqualTo("SAPCX Items Analyzer");
        assertThat(repository.language()).isEqualTo("xml");
        assertThat(repository.rules()).hasSize(CheckList.getCheckClasses().size());

        for (Rule rule : repository.rules()) {
            for (RulesDefinition.Param param : rule.params()) {
                assertThat(param.description()).as("description for " + param.key()).isNotEmpty();
            }
        }
    }

}
