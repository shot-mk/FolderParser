package main;

import com.folderToXml.dataHolder.FolderInfo;
import com.folderToXml.exceptions.GeneratorException;
import com.folderToXml.parsers.FolderParser;
import com.folderToXml.xmlGenerators.Generator;
import com.folderToXml.xmlGenerators.JaxbGenerator;

import java.io.File;

public class Main {
    public static void main(String args[])  {
        File folder = new File("testfolder");
        FolderParser parser = new FolderParser();
        FolderInfo fInfo = parser.parse("testfolder");
        Generator generator = new JaxbGenerator();
        try {
            generator.generate(fInfo, "/home/nikita/test1.xml");
        } catch (GeneratorException e) {
            e.printStackTrace();
        }
    }
}
