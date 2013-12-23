package com.folderToXml.xmlGenerators;

import com.folderToXml.dataHolder.FileInfo;
import com.folderToXml.dataHolder.FolderInfo;
import com.folderToXml.exceptions.GeneratorException;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * Generates xml document from container-object using SAX model
 */
public class SaxGenerator implements Generator {

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
            File f = new File(outputPath);
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            StreamResult result = new StreamResult(bw);
            SAXTransformerFactory tFactory = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
            TransformerHandler handler = tFactory.newTransformerHandler();
            Transformer transformer = handler.getTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            handler.setResult(result);
            handler.startDocument();
            folderGenerator(handler, foldInfo);
            handler.endDocument();
        } catch (Exception e) {
            throw new GeneratorException(e);
        }
    }

    /**
     * Recursive function for writing information to handler
     *
     * @param handler handler where to write information
     * @param fInfo   object-model, from where to write information
     * @return handler with written information about folder
     * @throws SAXException
     */
    private TransformerHandler folderGenerator(TransformerHandler handler, FolderInfo fInfo) throws SAXException {
        AttributesImpl nameAtt = new AttributesImpl();
        nameAtt.addAttribute("", "name", "name", "CDATA", fInfo.name);
        handler.startElement("", "dir", "dir", nameAtt);
        for (FileInfo file : fInfo.files) {
            AttributesImpl fileAtt = new AttributesImpl();
            if (file.name != null) {
                fileAtt.addAttribute("", "name", "name", "CDATA", file.name);
            }
            fileAtt.addAttribute("", "size", "size", "CDATA", file.size + "b");
            if (file.type != null) {
                fileAtt.addAttribute("", "type", "type", "CDATA", file.type);
            }
            handler.startElement("", "file", "file", fileAtt);
            handler.endElement("", "file", "file");
        }
        for (FolderInfo folder : fInfo.folders) {
            folderGenerator(handler, folder);
        }
        handler.endElement("", "dir", "dir");
        return handler;
    }
}
