package com.folderToXml.xmlGenerators;

import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.Difference;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public final class Tools {
    public static void showXmlDiff(Diff diff) {
        DetailedDiff detDiff = new DetailedDiff(diff);
        List differences = detDiff.getAllDifferences();
        for (Object object : differences) {

            Difference difference = (Difference) object;
            System.out.println("***********************");
            System.out.println(difference);
            System.out.println("***********************");
        }
    }

    public static String getResourceAsString(String path) throws IOException {
        FileInputStream fin = new FileInputStream(path);
        byte[] str = new byte[fin.available()];
        fin.read(str);
        String result = new String(str);
        return result;
    }
}