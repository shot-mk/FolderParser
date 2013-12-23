package com.folderToXml.xmlGenerators;

import com.folderToXml.dataHolder.FolderInfo;
import com.folderToXml.exceptions.GeneratorException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;

/**
 * Generates xml document from container-object using JAXB model
 */
public class JaxbGenerator implements Generator {

    /**
     * Generates xml document from container-object  to output path
     *
     * @param foldInfo   This folder data
     * @param outputPath Path where to save xml document
     * @throws GeneratorException
     * @see FolderInfo
     */
    public void generate(FolderInfo foldInfo, String outputPath) throws GeneratorException {
        try {
            File file = new File(outputPath);
            JAXBContext jaxbContext = JAXBContext.newInstance(FolderInfo.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(foldInfo, file);

        } catch (Exception e) {
            throw new GeneratorException(e);
        }
    }

}
