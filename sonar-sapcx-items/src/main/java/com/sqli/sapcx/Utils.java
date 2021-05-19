
package com.sqli.sapcx;

import com.sqli.sapcx.types.AttributeName;
import com.sqli.sapcx.types.NodeName;
import org.sonarsource.analyzer.commons.xml.XmlFile;
import org.sonarsource.analyzer.commons.xml.XmlTextRange;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utils {
    private Utils() {
        // utility class, forbidden constructor
    }

    public static String[] splitLines(String text) {
        return text.split("(\r)?\n|\r", -1);
    }

    /**
     * Check if element is self closing: &lt;foo ... /&gt;
     *
     * @param element element to check
     * @return true if element is self closing, false otherwise
     */
    public static boolean isSelfClosing(Element element) {
        XmlTextRange startLocation = XmlFile.startLocation(element);
        XmlTextRange endLocation = XmlFile.endLocation(element);
        return startLocation.getEndLine() == endLocation.getEndLine()
                && startLocation.getEndColumn() == endLocation.getEndColumn();
    }


}