package com.folderToXml.dataHolder;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * File data holder model
 */
@XmlRootElement
public class FileInfo {
    /** File name  */
    @XmlAttribute
    public final String name;
    /** MIME - type of the file */
    @XmlAttribute
    public final String type;
    /** File size bytes */
    @XmlAttribute
    public final long size;

    public FileInfo(String name, String type, long size) {
        this.name = name;
        this.type = type;
        this.size = size;
    }

    public FileInfo() {
        throw new NullPointerException(); //no-arg constructor for jaxb
    }
}
