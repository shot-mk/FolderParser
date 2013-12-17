package parsers;

import DataHolder.FileInfo;
import DataHolder.FolderInfo;
import java.io.File;
import java.util.LinkedList;
import java.util.List;


public class FolderParser {
    public FolderInfo parse(String path) {
        return getFolderInfo(path);
    }

    public FolderInfo getFolderInfo(String path) {
        File folder = new File(path);
        String name = folder.getName();
        File[] content = folder.listFiles();
        List<FileInfo> files = null;
        List<FolderInfo> folders = null;
        boolean isEmpty;
        if(content.length != 0 ) {
            files = new LinkedList<FileInfo>();
            folders = new LinkedList<FolderInfo>();
            for (int i = 0; i < content.length; i++) {
                if(content[i].isFile()){
                    FileInfo newFile = getFileInfo(content[i].getPath());
                    files.add(newFile);
                }
                if(content[i].isDirectory()){
                    FolderInfo newFolder = getFolderInfo(content[i].getPath());
                    folders.add(newFolder);
                }
            }
            isEmpty = false;
        } else {
            isEmpty = true;
        }
        return new FolderInfo(name, path, isEmpty, files, folders);
    }

    public FileInfo getFileInfo(String fpath) {
        File file = new File(fpath);
        String path = fpath;
        String name;
        String type;
        String fullName = file.getName();
        int dotPosition = fullName.lastIndexOf(".") + 1;
        if(dotPosition == -1){
            name = fullName;
            type = "Unknown";
        } else {
            name = fullName.substring(0, dotPosition - 1);
            type = fullName.substring(dotPosition);
        }
        long size = file.length();
        return new FileInfo(name, type, path, size);
    }

}
