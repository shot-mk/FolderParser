package com.folderToXml.xmlGenerators;

import com.folderToXml.dataHolder.FolderInfo;
import com.folderToXml.parsers.FolderParser;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class GeneratorTest {
    @Before
    public void setUp() throws Exception {
        XMLUnit.setIgnoreComments(true);
        XMLUnit.setIgnoreWhitespace(true);
    }

    @Test
    public void testDomGenerator() throws Exception {
        FolderInfo fInfo = FolderParser.parse("src/test/resources/testfolder");
        Generator generator = new DomGenerator();
        generator.generate(fInfo, "src/test/resources/GeneratorResults/domXml.xml");
        String expected = Tools.getResourceAsString("src/test/resources/Patterns/DomPattern.xml");
        String actual = Tools.getResourceAsString("src/test/resources/GeneratorResults/domXml.xml");
        Diff diff = new Diff(actual, expected);
        Tools.showXmlDiff(diff);
        assertTrue(diff.similar());

    }

    @Test
    public void testSaxGenerator() throws Exception {
        FolderInfo fInfo = FolderParser.parse("src/test/resources/testfolder");
        Generator generator = new SaxGenerator();
        generator.generate(fInfo, "src/test/resources/GeneratorResults/saxXml.xml");
        String expected = Tools.getResourceAsString("src/test/resources/Patterns/SaxPattern.xml");
        String actual = Tools.getResourceAsString("src/test/resources/GeneratorResults/saxXml.xml");
        Diff diff = new Diff(actual, expected);
        Tools.showXmlDiff(diff);
        assertTrue(diff.similar());
    }

    @Test
    public void testJaxbGenerator() throws Exception {
        FolderInfo fInfo = FolderParser.parse("src/test/resources/testfolder");
        Generator generator = new JaxbGenerator();
        generator.generate(fInfo, "src/test/resources/GeneratorResults/jaxbXml.xml");
        String expected = Tools.getResourceAsString("src/test/resources/Patterns/JaxbPattern.xml");
        String actual = Tools.getResourceAsString("src/test/resources/GeneratorResults/jaxbXml.xml");
        Diff diff = new Diff(actual, expected);
        Tools.showXmlDiff(diff);
        assertTrue(diff.similar());
    }

    @Test
    public void testXstreamGenerator() throws Exception {
        FolderInfo fInfo = FolderParser.parse("src/test/resources/testfolder");
        Generator generator = new XstreamGenerator();
        generator.generate(fInfo, "src/test/resources/GeneratorResults/xstreamXml.xml");
        String expected = Tools.getResourceAsString("src/test/resources/Patterns/XstreamPattern.xml");
        String actual = Tools.getResourceAsString("src/test/resources/GeneratorResults/xstreamXml.xml");
        Diff diff = new Diff(actual, expected);
        Tools.showXmlDiff(diff);
        assertTrue(diff.similar());
    }

}
