package xmlGenerators;

import DataHolder.FileInfo;
import DataHolder.FolderInfo;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class DomGenerator implements Generator {

    @Override
    public void generate(FolderInfo foldInfo, String outputPath) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            doc.appendChild(folderGenerate(foldInfo, doc));
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            Source s = new DOMSource(doc);
            Result res = new StreamResult(new FileOutputStream(outputPath));
            transformer.transform(s, res);

        }  catch (ParserConfigurationException e) {
            e.printStackTrace();
        }  catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }  catch (FileNotFoundException e) {
            e.printStackTrace();
        }  catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    private Element folderGenerate(FolderInfo fInfo, Document doc) {
        Element folder = doc.createElement("dir");
        folder.setAttribute("name", fInfo.name);
        if(!fInfo.isEmpty){
            for (FileInfo file : fInfo.files) {
                Element includedFile = doc.createElement("file");
                includedFile.setAttribute("name" , file.name);
                includedFile.setAttribute("type" , file.type);
                includedFile.setAttribute("size", file.size + "b");
                folder.appendChild(includedFile);
            }
            for (FolderInfo folderInfo : fInfo.folders) {
                Element includedFolder = folderGenerate(folderInfo, doc);
                folder.appendChild(includedFolder);
            }
        }
        return folder;
    }
}
