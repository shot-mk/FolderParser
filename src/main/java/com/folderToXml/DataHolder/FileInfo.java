package com.folderToXml.dataHolder;
import javax.xml.bind.annotation.*;
@XmlRootElement
public class FileInfo {
    @XmlAttribute
    public final String name;
    @XmlAttribute
    public final String type;
    @XmlTransient
    public final String path;
    @XmlAttribute
    public final long size;

    public FileInfo(String name, String type, String path, long size) {
        this.name = name;
        this.type = type;
        this.path = path;
        this.size = size;
    }

    public FileInfo(){
        throw new NullPointerException(); //no-arg constructor for jaxb
    }
}
