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


    /**
     * Method check if  objects are equals
     * @param Info input object
     * @return {code true} if objects are equal, {code false} if objects not equal
     */
    public boolean equals(Object Info){
        FolderInfo otherInfo = (FolderInfo)Info;
        if(!this.name.equals(otherInfo.name)){
            return false;
        }
        if(this.isEmpty != otherInfo.isEmpty){
            return false;
        }
        if(this.files.size() != otherInfo.files.size()) {
            return false;
        }
        if(!this.isEmpty) {
            for (int i = 0; i < this.files.size(); i++) {
                if(!this.files.get(i).equals(otherInfo.files.get(i))) {
                    return false;
                }
            }
        }
        if(this.folders != null && otherInfo.folders != null) {
            if(this.folders.size() != otherInfo.folders.size()) {
                return false;
            }
        }
        if(this.folders != null && otherInfo.folders != null){
            for (int i = 0; i < this.folders.size(); i++) {
                if(!this.folders.get(i).equals(otherInfo.folders.get(i))){
                    return false;
                }
            }
        }
        return true;
    }
}
