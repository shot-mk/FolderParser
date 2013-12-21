package com.folderToXml.xmlGenerators;

import com.folderToXml.dataHolder.FolderInfo;
import com.folderToXml.exceptions.GeneratorException;

/**
 * Generates xml document from container object
 * @throws GeneratorException
 * @see FolderInfo
 */
public interface Generator {
    public void generate(FolderInfo foldInfo, String outputPath) throws GeneratorException;
}
