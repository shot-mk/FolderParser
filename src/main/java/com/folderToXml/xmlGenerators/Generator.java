package com.folderToXml.xmlGenerators;

import com.folderToXml.dataHolder.FolderInfo;

/**
 * Generates xml document from container object
 * @see FolderInfo
 */
public interface Generator {
    public void generate(FolderInfo foldInfo, String outputPath);
}
