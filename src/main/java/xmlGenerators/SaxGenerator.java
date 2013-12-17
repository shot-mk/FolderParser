package xmlGenerators;

import DataHolder.FileInfo;
import DataHolder.FolderInfo;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaxGenerator implements Generator {

    @Override
    public void generate(FolderInfo foldInfo, String outputPath) {
        try {
            File f = new File(outputPath);
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            StreamResult result = new StreamResult(bw);
            SAXTransformerFactory tFactory = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
            TransformerHandler handler = tFactory.newTransformerHandler();
            Transformer transformer = handler.getTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            handler.setResult(result);
            handler.startDocument();
            folderGenerator(handler,foldInfo);
            handler.endDocument();
        } catch (TransformerConfigurationException e) {
            System.out.println("Transformer Configuration Exception: " + e.getMessage());
        }
        catch (SAXException e) {
            System.out.println("SAX Exception: " + e.getMessage());
        }
        catch (IOException e) {
            System.out.println("IO Exception: " + e.getMessage());
        }
    }

    private TransformerHandler folderGenerator(TransformerHandler handler, FolderInfo fInfo) throws SAXException {
        AttributesImpl nameAtt = new AttributesImpl();
        nameAtt.addAttribute("","name","name","CDATA",fInfo.name);
        handler.startElement("","dir","dir",nameAtt);
        for (FileInfo file : fInfo.files) {
            AttributesImpl fileAtt = new AttributesImpl();
            fileAtt.addAttribute("","name","name","CDATA",file.name);
            fileAtt.addAttribute("","size","size","CDATA",file.size+"b");
            fileAtt.addAttribute("","type","type","CDATA",file.type);
            handler.startElement("","file","file",fileAtt);
            handler.endElement("","file","file");
        }
        for (FolderInfo folder : fInfo.folders) {
            folderGenerator(handler,folder);
        }
        handler.endElement("","dir","dir");
        return handler;
    }
}
