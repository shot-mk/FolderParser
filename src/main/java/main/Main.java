package main;

import com.folderToXml.dataHolder.FolderInfo;
import com.folderToXml.parsers.FolderParser;
import com.folderToXml.xmlGenerators.Generator;
import com.folderToXml.xmlGenerators.JaxbGenerator;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String args[]) throws ParserConfigurationException, TransformerException, FileNotFoundException {
        File folder = new File("D:/1");
        FolderParser parser = new FolderParser();
        FolderInfo fInfo = parser.parse("D:/Музыка");
        Generator generator = new JaxbGenerator();
        generator.generate(fInfo, "D:/test1.xml");
    }
}
