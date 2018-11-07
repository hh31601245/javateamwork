package poi;
import model.URL;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hpsf.DocumentSummaryInformation;
//import org.apache.poi.hssf.model.Workbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.hwpf.model.TextPieceTable;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.xmlbeans.XmlException;

public class LoadWord {
	public static String LoadAllWord() throws IOException{
         String result=null;
		try
		{
			InputStream is = new FileInputStream(new File(URL.WordURL));
			WordExtractor ex = new WordExtractor(is);  
            String text2003 = ex.getText();  
            System.out.println(text2003);
            result=text2003;
			
		}catch(Exception e1)
		{
			OPCPackage opcPackage = POIXMLDocument.openPackage(URL.WordURL);  
            POIXMLTextExtractor extractor=null;
			try {
				extractor = new XWPFWordExtractor(opcPackage);
			} catch (XmlException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (OpenXML4JException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
            String text2007 = extractor.getText();  
            System.out.println(text2007);
            result=text2007;
		}
		return result;
		//String a=book.toString();
		//System.out.println(a);
	}
	public static void main(String[] args)
	{
		try {
			LoadWord.LoadAllWord();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
