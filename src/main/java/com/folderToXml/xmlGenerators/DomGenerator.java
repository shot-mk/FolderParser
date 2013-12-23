package com.folderToXml.xmlGenerators;

import com.folderToXml.dataHolder.FileInfo;
import com.folderToXml.dataHolder.FolderInfo;
import com.folderToXml.exceptions.GeneratorException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;

/**
 * Generates xml document from container-object using DOM model
 */
public class DomGenerator implements Generator {
    /**
     * Generates xml document from container-object  to output path
     *
     * @param foldInfo   This folder data
     * @param outputPath Path where to save xml document
     * @throws GeneratorException
     * @see FolderInfo
     */
    @Override
    public void generate(FolderInfo foldInfo, String outputPath) throws GeneratorException {
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
        } catch (Exception e) {
            throw new GeneratorException(e);
        }
    }

    /**
     * Generates current folder Element  and sets attributes
     *
     * @param fInfo current FolderInfo
     * @param doc   current document
     * @return folder Element
     * @see org.w3c.dom.Element
     * @see org.w3c.dom.Document
     */
    private Element folderGenerate(FolderInfo fInfo, Document doc) {
        Element folder = doc.createElement("dir");
        folder.setAttribute("name", fInfo.name);
        if (!fInfo.isEmpty) {
            for (FileInfo file : fInfo.files) {
                Element includedFile = doc.createElement("file");
                includedFile.setAttribute("name", file.name);
                includedFile.setAttribute("type", file.type);
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
