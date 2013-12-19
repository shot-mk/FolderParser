package com.folderToXml.dataHolder;
import java.util.List;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "dir")
public class FolderInfo {
    @XmlAttribute
    final public String name;
    @XmlTransient
    final public String path;
    @XmlTransient
    final public boolean isEmpty;
    @XmlElement(name = "file")
    final public List<FileInfo> files;
    @XmlElement(name = "dir")
    final public List<FolderInfo> folders;

    public FolderInfo(String name, String path, boolean empty, List<FileInfo> files, List<FolderInfo> folders) {
        this.name = name;
        this.path = path;
        isEmpty = empty;
        this.files = files;
        this.folders = folders;
    }

    public FolderInfo(){
        throw new NullPointerException(); //no-arg constructor for jaxb
    }
}
