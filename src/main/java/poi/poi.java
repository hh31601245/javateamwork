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
import org.apache.poi.hssf.model.Workbook;
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

public class poi {
	public static String readAndWriterTest3() throws IOException{
		/*OPCPackage opcPackage = POIXMLDocument.openPackage("D:\\java高级\\舟山\\（016）浙0902刑初00262号.docx");
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
		}*/
		//org.apache.poi.ss.usermodel.Workbook book=null;
		String result=null;
		try
		{
			InputStream is = new FileInputStream(new File("D:\\java高级\\舟山\\（2016）浙0902刑初00262号.doc"));
			WordExtractor ex = new WordExtractor(is);  
            String text2003 = ex.getText();  
            //System.out.println(text2003);
            result=text2003;
			
		}catch(Exception ex)
		{
			OPCPackage opcPackage = POIXMLDocument.openPackage("D:\\java高级\\舟山\\（2016）浙0902刑初00262号.doc");  
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
            //System.out.println(text2007);  
		   result=text2007;
		}
		return result;
		//String a=book.toString();
		//System.out.println(a);
	}
	public static void main(String[] args)
	{
		try {
			String word=poi.readAndWriterTest3();
			String[] wordlist=word.split("舟山市定海区人民检察院")[1].split("\n");
			String[] wordlist2=word.split("判决如下")[1].split("\n",-1);
			System.out.println(wordlist2.length);
			for(int i=1;i<wordlist.length;i++)
			{
				if(wordlist[i].contains("被告人"))
				{
					System.out.println(wordlist[i]+"\n"+wordlist2[i]);
					System.out.println("-------------");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
