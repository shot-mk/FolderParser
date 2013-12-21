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
        //FolderParser parser = new FolderParser();
        FolderInfo fInfo = FolderParser.parse("src/test/resources/testfolder");
        Generator generator = new JaxbGenerator();
        System.out.println(fInfo.folders.get(0).files.get(0).type);
        try {
            generator.generate(fInfo, "D:/test1.xml");
        } catch (GeneratorException e) {
            e.printStackTrace();
        }
    }
}
