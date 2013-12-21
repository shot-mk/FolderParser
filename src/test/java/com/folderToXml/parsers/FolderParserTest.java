package com.folderToXml.parsers;

import com.folderToXml.dataHolder.FileInfo;
import com.folderToXml.dataHolder.FolderInfo;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class FolderParserTest {
    @Test
    public void testParse() throws Exception {
        List<FileInfo> rootFolderFiles= new LinkedList<FileInfo>();
        rootFolderFiles.add(new FileInfo("testtxt", "text/plain", 5303));
        List<FolderInfo> rootIncludedFolders = new LinkedList<FolderInfo>();
        List<FileInfo> includedFolder1Files = new LinkedList<>();
        includedFolder1Files.add(new FileInfo("testpdf", null, 9172));
        FolderInfo includedTestFolder1 = new FolderInfo("includedTestFolder1", false, includedFolder1Files, null);
        List<FileInfo> includedFolder2Files = new LinkedList<>();
        includedFolder2Files.add(new FileInfo("testhtml", "text/html", 60398));
        FolderInfo includedTestFolder2 = new FolderInfo("includedTestFolder2", false, includedFolder2Files, null);
        rootIncludedFolders.add(includedTestFolder1);
        rootIncludedFolders.add(includedTestFolder2);
        FolderInfo expected = new FolderInfo("testfolder", false, rootFolderFiles, rootIncludedFolders);
        FolderInfo actual = FolderParser.parse("src/test/resources/testfolder");
        assertTrue(expected.equals(actual));
    }


}
