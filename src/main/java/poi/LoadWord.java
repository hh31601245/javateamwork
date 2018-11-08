package poi;
import model.Defendant;
import model.URL;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
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
		//	InputStream is = new FileInputStream(new File(URL.WordURL));
			InputStream is = new FileInputStream(new File("D:\\java高级\\舟山\\（2016）浙0902刑初00262号.doc"));
			WordExtractor ex = new WordExtractor(is);  
            String text2003 = ex.getText();  
            //System.out.println(text2003);
            result=text2003;
			
		}catch(Exception e1)
		{
			//OPCPackage opcPackage = POIXMLDocument.openPackage(URL.WordURL); 
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
	public static List<String> LoadDefendantWord()
	{
		List<String> result1=new ArrayList<String>();
		
		try {
			String word=LoadWord.LoadAllWord();
			String[] wordlist=word.split("舟山市定海区人民检察院")[1].split("\n");
			String[] wordlist2=word.split("判决如下")[1].split("\n");
			for(int i=1;i<wordlist.length;i++)
			{
				if(wordlist[i].contains("被告人"))
				{
					String defendant1=null;
					String defendant2=null;
					String defendant=null;
					//System.out.println(wordlist[i]+"\n"+wordlist2[i]);
					//System.out.println("-------------");
					defendant1=wordlist[i];
					defendant2=wordlist2[i];
					System.out.println(wordlist[i]+"\n"+wordlist2[i]);
					defendant=defendant1+defendant2;
					result1.add(defendant);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result1;
	}
	public static void main(String[] args)
	{
          List<String> result=new ArrayList<String>();
          result=LoadWord.LoadDefendantWord();
          for(String a:result)
          {
        	  System.out.println(a);
          }
	}
}
