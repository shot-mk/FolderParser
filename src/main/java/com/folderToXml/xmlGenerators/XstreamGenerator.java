package com.folderToXml.xmlGenerators;

import com.folderToXml.dataHolder.FileInfo;
import com.folderToXml.dataHolder.FolderInfo;
import com.thoughtworks.xstream.XStream;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Generates xml document from container-object using Xstream
 * @see FolderInfo
 */
public class XstreamGenerator implements Generator {


    /**
     * Generates xml document from container-object  to output path
     * @see FolderInfo
     * @param foldInfo This folder data
     * @param outputPath Path where to save xml document
     */
    @Override
    public void generate(FolderInfo foldInfo, String outputPath) {
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
        } catch (IOException e) {
            System.out.println("IOException." + e.getMessage());
        }
        xstream.toXML(foldInfo, writer);
    }
}
