package com.sqli.sapcx;

import com.sqli.sapcx.checks.CheckList;
import org.sonar.api.batch.fs.FilePredicate;
import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.rule.CheckFactory;
import org.sonar.api.batch.rule.Checks;
import org.sonar.api.batch.sensor.Sensor;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.SensorDescriptor;
import org.sonar.api.internal.google.common.annotations.VisibleForTesting;
import org.sonar.api.rule.RuleKey;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;
import org.sonarsource.analyzer.commons.ProgressReport;
import org.sonarsource.analyzer.commons.xml.XmlFile;
import org.sonarsource.analyzer.commons.xml.checks.SonarXmlCheck;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class SapCxItemsSensor implements Sensor {

    private static final Logger LOG = Loggers.get(SapCxItemsSensor.class);

    private final Checks<Object> checks;
    private final FileSystem fileSystem;
    private final FilePredicate mainFilesPredicate;

    public SapCxItemsSensor(FileSystem fileSystem, CheckFactory checkFactory) {
        this.checks = checkFactory.create(SapCxItemsPreperties.REPOSITORY_KEY).addAnnotatedChecks((Iterable<?>) CheckList.getCheckClasses());
        this.fileSystem = fileSystem;
        this.mainFilesPredicate = fileSystem.predicates().and(
                fileSystem.predicates().matchesPathPattern(SapCxItemsPreperties.SAPCX_ITEMS_SUFFIXES));
    }

    @Override
    public void execute(SensorContext context) {
        List<InputFile> inputFiles = new ArrayList<>();
        fileSystem.inputFiles(mainFilesPredicate).forEach(inputFiles::add);

        if (inputFiles.isEmpty()) {
            return;
        }

        ProgressReport progressReport = new ProgressReport("Report about progress of SAP CX Items Analyzer", TimeUnit.SECONDS.toMillis(10));
        progressReport.start(inputFiles.stream().map(InputFile::toString).collect(Collectors.toList()));

        boolean cancelled = false;
        try {
            for (InputFile inputFile : inputFiles) {
                if (context.isCancelled()) {
                    cancelled = true;
                    break;
                }
                scanFile(context, inputFile);
                progressReport.nextFile();
            }
        } finally {
            if (!cancelled) {
                progressReport.stop();
            } else {
                progressReport.cancel();
            }
        }
    }

    private void scanFile(SensorContext context, InputFile inputFile) {
        try {
            XmlFile xmlFile = XmlFile.create(inputFile);
            runChecks(context, xmlFile);
        } catch (Exception e) {
            throw new RuntimeException("Error while scanning the file : " + inputFile, e);
        }
    }

    private void runChecks(SensorContext context, XmlFile newXmlFile) {
        checks.all().stream()
                .map(SonarXmlCheck.class::cast)
                // checks.ruleKey(check) is never null because "check" is part of "checks.all()"
                .forEach(check -> runCheck(context, check, checks.ruleKey(check), newXmlFile));
    }

    @VisibleForTesting
    void runCheck(SensorContext context, SonarXmlCheck check, RuleKey ruleKey, XmlFile newXmlFile) {
        try {
            check.scanFile(context, ruleKey, newXmlFile);
        } catch (Exception e) {
            logFailingRule(ruleKey, newXmlFile.getInputFile().uri(), e);
        }
    }

    private static void logFailingRule(RuleKey rule, URI fileLocation, Exception e) {
        LOG.error(String.format("Unable to execute rule %s on %s", rule, fileLocation), e);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    @Override
    public void describe(SensorDescriptor descriptor) {
        descriptor
                .onlyOnLanguage(SapCxItemsPreperties.XML_KEY)
                .name("SAP CX Items Sensor");
    }

}
