package com.folderToXml.xmlGenerators;

import com.folderToXml.dataHolder.FileInfo;
import com.folderToXml.dataHolder.FolderInfo;
import com.folderToXml.exceptions.GeneratorException;
import com.thoughtworks.xstream.XStream;

import java.io.FileWriter;

/**
 * Generates xml document from container-object using Xstream
 */
public class XstreamGenerator implements Generator {


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
        XStream xstream = new XStream();
        xstream.processAnnotations(FolderInfo.class);
        xstream.alias("dir", FolderInfo.class);
        xstream.alias("file", FileInfo.class);
        xstream.addImplicitCollection(FolderInfo.class, "folders");
        xstream.addImplicitCollection(FolderInfo.class, "files");
        xstream.useAttributeFor(FolderInfo.class, "name");
        xstream.useAttributeFor(FileInfo.class, "name");
        xstream.useAttributeFor(FileInfo.class, "type");
        xstream.useAttributeFor(FileInfo.class, "size");
        FileWriter writer = null;
        try {
            writer = new FileWriter(outputPath);
            writer.write("<?xml version=\"1.1\" encoding=\"UTF-8\" standalone=\"no\"?>\n");
        } catch (Exception e) {
            throw new GeneratorException(e);
        }
        xstream.toXML(foldInfo, writer);
    }
}
