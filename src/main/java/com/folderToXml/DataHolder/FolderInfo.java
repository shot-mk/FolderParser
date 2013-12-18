package com.folderToXml.DataHolder;
import java.util.List;

public class FolderInfo {
    final public String name;
    final public String path;
    final public boolean isEmpty;
    final public List<FileInfo> files;
    final public List<FolderInfo> folders;

    public FolderInfo(String name, String path, boolean empty, List<FileInfo> files, List<FolderInfo> folders) {
        this.name = name;
        this.path = path;
        isEmpty = empty;
        this.files = files;
        this.folders = folders;
    }
}
