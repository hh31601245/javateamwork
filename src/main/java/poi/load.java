package poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hpsf.DocumentSummaryInformation;
//import org.apache.poi.hwpf.HWPFDocument;
//import org.apache.poi.hwpf.extractor.WordExtractor;
//import org.apache.poi.hwpf.model.TextPieceTable;
//import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.xmlbeans.XmlException;

import model.URL;

public class load {
	public static void readAndWriterTest3() throws IOException{
		OPCPackage opcPackage = POIXMLDocument.openPackage(URL.WordURL);
		POIXMLTextExtractor extractor = null;
		try {
			extractor = new XWPFWordExtractor(opcPackage);
		} catch (XmlException | OpenXML4JException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		String text2007 = extractor.getText();
		System.out.println(text2007);  
		Pattern p=Pattern.compile("\\d{4}");
		Matcher m=p.matcher(text2007);
		while(m.find())
		{
			System.out.println(m.group());
		}
	}
	public static void main(String[] args)
	{
		try {
			load.readAndWriterTest3();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

