package com.sqli.sapcx;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.migrationsupport.rules.EnableRuleMigrationSupport;
import org.junit.rules.TemporaryFolder;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.fs.InputFile.Type;
import org.sonar.api.batch.fs.internal.DefaultFileSystem;
import org.sonar.api.batch.fs.internal.DefaultInputFile;
import org.sonar.api.batch.fs.internal.FileMetadata;
import org.sonar.api.batch.fs.internal.TestInputFileBuilder;
import org.sonar.api.batch.rule.CheckFactory;
import org.sonar.api.batch.rule.internal.ActiveRulesBuilder;
import org.sonar.api.batch.sensor.highlighting.TypeOfText;
import org.sonar.api.batch.sensor.internal.DefaultSensorDescriptor;
import org.sonar.api.batch.sensor.internal.SensorContextTester;
import org.sonar.api.measures.CoreMetrics;
import org.sonar.api.measures.FileLinesContext;
import org.sonar.api.measures.FileLinesContextFactory;
import org.sonar.api.rule.RuleKey;
import org.sonar.api.utils.log.LogAndArguments;
import org.sonar.api.utils.log.LogTester;
import org.sonar.api.utils.log.LoggerLevel;
import org.sonarsource.analyzer.commons.xml.XmlFile;
import org.sonarsource.analyzer.commons.xml.checks.SonarXmlCheck;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@EnableRuleMigrationSupport
class SapCxItemsSensorTest {

    private static final RuleKey MIN_TYPECODE_RULE_KEY = RuleKey.of(SapCxItemsPreperties.REPOSITORY_KEY, "DeploymentTypeCodeCheck_RULE_KEY");

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Rule
    public LogTester logTester = new LogTester();

    private DefaultFileSystem fs;
    private SapCxItemsSensor sensor;
    private SensorContextTester context;

    void test_analysis_cancellation() throws Exception {
        init();

        fs.add(createInputFile("src/test-items.xml"));
        context.setCancelled(true);
        sensor.execute(context);

        assertThat(context.allIssues()).isEmpty();
    }

    @Test
    void notItemsXMLFilesShouldBeIgnored() throws Exception {
        init();

        fs.add(createInputFile("src/test-ignored-normal.xml"));
        sensor.execute(context);

        assertThat(context.allIssues()).isEmpty();
    }

    @Test
    void test_nothing_is_executed_if_no_file() throws Exception {
        init();

        sensor.execute(context);

        assertThat(context.allIssues()).isEmpty();
    }

    @Test
    void test_descriptor() throws Exception {
        init();

        DefaultSensorDescriptor sensorDescriptor = new DefaultSensorDescriptor();
        sensor.describe(sensorDescriptor);

        assertThat(sensorDescriptor.name()).isEqualTo("SAP CX Items Sensor");
        assertThat(sensorDescriptor.languages()).containsOnly(SapCxItemsPreperties.XML_KEY);
    }

    /*
     * Only with the "DeploymentTypeCodeCheck_RULE_KEY" rule
     */
    @Test
    void test_sensor() throws Exception {
        init();
        fs.add(createInputFile("src/test-items.xml"));

        sensor.execute(context);

        assertThat(context.allIssues()).extracting("ruleKey").containsOnly(MIN_TYPECODE_RULE_KEY);
    }

    @Test
    void failing_rules_should_not_report_parse_exception() throws Exception {
        init();

        sensor.runCheck(context, new SonarXmlCheck() {
            @Override
            public void scanFile(XmlFile file) {
                throw new IllegalStateException("failing systematically");
            }
        }, MIN_TYPECODE_RULE_KEY, XmlFile.create(createInputFile("src/test-items.xml")));

        assertThat(context.allIssues()).isEmpty();
        assertThat(logTester.getLogs()).isNotEmpty();

        List<LogAndArguments> errors = logTester.getLogs(LoggerLevel.ERROR);
        assertThat(errors).hasSize(1);
        assertThat(errors.get(0).getRawMsg()).startsWith("Unable to execute rule sapcx-items:DeploymentTypeCodeCheck_RULE_KEY");
    }


    private void init() throws Exception {
        File moduleBaseDir = new File("src/test/resources");
        context = SensorContextTester.create(moduleBaseDir);

        fs = new DefaultFileSystem(moduleBaseDir);
        fs.setWorkDir(temporaryFolder.newFolder().toPath());


        ActiveRulesBuilder activeRuleBuilder = new ActiveRulesBuilder()
                .create(MIN_TYPECODE_RULE_KEY)
                .activate();

        CheckFactory checkFactory = new CheckFactory(activeRuleBuilder.build());

        FileLinesContextFactory fileLinesContextFactory = mock(FileLinesContextFactory.class);
        when(fileLinesContextFactory.createFor(any(InputFile.class))).thenReturn(mock(FileLinesContext.class));

        sensor = new SapCxItemsSensor(fs, checkFactory);
    }

    private DefaultInputFile createInputFile(String name) throws FileNotFoundException {
        DefaultInputFile inputFile = TestInputFileBuilder.create("modulekey", name)
                .setModuleBaseDir(Paths.get("src/test/resources"))
                .setType(Type.MAIN)
                .setLanguage(SapCxItemsPreperties.XML_KEY)
                .setCharset(StandardCharsets.UTF_8)
                .build();

        inputFile.setMetadata(new FileMetadata().readMetadata(new FileInputStream(inputFile.file()), StandardCharsets.UTF_8, inputFile.absolutePath()));
        return inputFile;
    }

}
