package com.folderToXml.dataHolder;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

/**
 * Folder data holder model
 */
@XmlRootElement(name = "dir")
public class FolderInfo {
    /**Folder name  */
    @XmlAttribute
    final public String name;
    /**If  {@code true} - folder is empty, {@code false} - folder isn't empty */
    @XStreamOmitField
    @XmlTransient
    final public boolean isEmpty;
    /**Files list in this folder
     *@see FileInfo
     */
    @XmlElement(name = "file")
    final public List<FileInfo> files;
    /**Folders list in this folder */
    @XmlElement(name = "dir")
    final public List<FolderInfo> folders;

    public FolderInfo(String name, boolean empty, List<FileInfo> files, List<FolderInfo> folders) {
        this.name = name;
        isEmpty = empty;
        this.files = files;
        this.folders = folders;
    }

    public FolderInfo() {
        throw new NullPointerException(); //no-arg constructor for jaxb
    }
}
