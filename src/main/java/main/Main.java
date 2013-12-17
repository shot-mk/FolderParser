package main;

import DataHolder.FileInfo;
import DataHolder.FolderInfo;
import com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import parsers.FolderParser;
import xmlGenerators.DomGenerator;
import xmlGenerators.Generator;
import xmlGenerators.SaxGenerator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

public class Main {
    public static void main(String args[]) throws ParserConfigurationException, TransformerException, FileNotFoundException {
        File folder= new File("D:/test");
        FolderParser parser = new FolderParser();
        FolderInfo fInfo = parser.parse("D:/MyMovies");
        Generator generator = new SaxGenerator();
        generator.generate(fInfo,"D:/test1.xml");






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
