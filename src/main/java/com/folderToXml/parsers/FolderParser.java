package com.folderToXml.parsers;

import com.folderToXml.dataHolder.FileInfo;
import com.folderToXml.dataHolder.FolderInfo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;

/**
 * Собирает информацию о папке, вложеных папках и файлах
 */
public class FolderParser {
    /**
     * Calls a function that collect information about the folder
     *
     * @param path Folder path
     * @return Container-object with information about the folder
     */
    public static FolderInfo parse(String path) {
        return getFolderInfo(path);
    }

    /**
     * Collect information about this folder and write it to container-object
     *
     * @param path Folder path
     * @return Container-object for this folder
     * @see FolderInfo
     */
    private static FolderInfo getFolderInfo(String path) {
        File folder = new File(path);
        String name = folder.getName();
        File[] content = folder.listFiles();
        List<FileInfo> files = null;
        List<FolderInfo> folders = null;
        boolean isEmpty;
        if (content.length != 0) {
            files = new LinkedList<FileInfo>();
            folders = new LinkedList<FolderInfo>();
            for (int i = 0; i < content.length; i++) {
                if (content[i].isFile()) {
                    FileInfo newFile = null;
                    try {
                        newFile = getFileInfo(content[i].getPath());
                    } catch (IOException e) {
                        System.out.println("IOException " + e.getMessage());
                    }
                    files.add(newFile);
                }
                if (content[i].isDirectory()) {
                    FolderInfo newFolder = getFolderInfo(content[i].getPath());
                    folders.add(newFolder);
                }
            }
            isEmpty = false;
        } else {
            isEmpty = true;
        }
        return new FolderInfo(name, isEmpty, files, folders);
    }

    /**
     * Collect information about this file and write it to container-object
     *
     * @param fpath File path
     * @return Container-object for this file
     * @throws IOException if file with this path don't exist
     * @see FileInfo
     */
    private static FileInfo getFileInfo(String fpath) throws IOException {
        File file = new File(fpath);
        String path = fpath;
        String name;
        String type;
        String fullName = file.getName();
        int dotPosition = fullName.lastIndexOf(".") + 1;
        if (dotPosition == 0) {
            name = fullName;
        } else {
            name = fullName.substring(0, dotPosition - 1);
        }
        type = Files.probeContentType(file.toPath());
        long size = file.length();
        return new FileInfo(name, type, size);
    }


}
