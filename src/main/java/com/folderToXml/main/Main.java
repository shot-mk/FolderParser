package com.folderToXml.main;

import com.folderToXml.DataHolder.FolderInfo;
import com.folderToXml.parsers.FolderParser;
import com.folderToXml.xmlGenerators.DomGenerator;
import com.folderToXml.xmlGenerators.Generator;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;

public class Main {
    public static void main(String args[]) throws ParserConfigurationException, TransformerException, FileNotFoundException {
        File folder= new File("/home/nikita/.pki");
        FolderParser parser = new FolderParser();
        FolderInfo fInfo = parser.parse("/home/nikita/.pki");
        Generator generator = new DomGenerator();
        generator.generate(fInfo,"/home/nikita/test1.xml");






//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder builder = factory.newDocumentBuilder();
//        Document doc = builder.newDocument();
//
//
//
//        Element ele = doc.createElement("Movies");
//        Element childElement = doc.createElement("Movie");
//        childElement.setAttribute("Type", "BollyWood");
//        childElement.setAttribute("Name", "Lagaan");
//        childElement.setAttribute("Actor", "Aamir Khan");
//        ele.appendChild(childElement);
//        doc.appendChild(ele);
//
//
//
//        TransformerFactory tFactory = TransformerFactory.newInstance();
//        Transformer transformer = tFactory.newTransformer();
//        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
//        Source s = new DOMSource(doc);
//        Result res = new StreamResult(new FileOutputStream("D:/test.xml"));
//        transformer.transform(s, res);






    }
}
