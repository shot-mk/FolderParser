package com.folderToXml.xmlGenerators;

import com.folderToXml.dataHolder.FolderInfo;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: shot
 * Date: 19.12.13
 * Time: 22:40
 * To change this template use File | Settings | File Templates.
 */
public class JaxbGenerator implements Generator {
    public void generate(FolderInfo foldInfo, String outputPath){
        try {
            File file = new File(outputPath);
            JAXBContext jaxbContext = JAXBContext.newInstance(FolderInfo.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(foldInfo, file);

        } catch (JAXBException e) {
            System.out.println("JAXBException Exception: " + e.getMessage());
        }

    }


}
