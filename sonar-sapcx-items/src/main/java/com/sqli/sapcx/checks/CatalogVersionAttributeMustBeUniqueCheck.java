package com.sqli.sapcx.checks;

import org.sonar.check.Rule;
import org.sonarsource.analyzer.commons.xml.XmlFile;
import org.sonarsource.analyzer.commons.xml.checks.SimpleXPathBasedCheck;
import org.w3c.dom.Node;

import javax.xml.xpath.XPathExpression;
import java.util.List;

@Rule(key = CatalogVersionAttributeMustBeUniqueCheck.RULE_KEY)
public class CatalogVersionAttributeMustBeUniqueCheck extends SimpleXPathBasedCheck {
    public static final String RULE_KEY = "CatalogVersionAttributeMustBeUniqueCheck_RULE_KEY";
    private final XPathExpression allCatalogVersionAttrExpression =
            getXPathExpression("//itemtype/attributes/attribute[@type='CatalogVersion']/modifiers[not(@unique) or @unique='false']");
    @Override
    public void scanFile(XmlFile file) {
        List<Node> allCatalogVersionAttr = evaluateAsList(this.allCatalogVersionAttrExpression, file.getDocument());
        allCatalogVersionAttr
                .forEach(catalogVersionAttr -> reportIssue(catalogVersionAttr, "Mark CatalogVersion attributes as unique for Catalog aware types."));

    }
}
