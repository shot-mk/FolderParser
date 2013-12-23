package com.folderToXml.dataHolder;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * File data holder model
 */
public class FileInfo {
    /**
     * File name
     */
    @XmlAttribute
    public final String name;
    /**
     * MIME - type of the file
     */
    @XmlAttribute
    public final String type;
    /**
     * File size bytes
     */
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

    /**
     * Method check if  objects are equals
     *
     * @param File input object
     * @return {code true} if objects are equal, {code false} if objects not equal
     */
    public boolean equals(Object File) {
        FileInfo otherFile = (FileInfo) File;
        if (!this.name.equals(otherFile.name)) {
            return false;
        }
        if (this.size != otherFile.size) {
            return false;
        }
        if (this.type != null && otherFile.type != null) {
            if (!this.type.equals(otherFile.type)) {
                return false;
            }
        }
        return true;
    }
}
