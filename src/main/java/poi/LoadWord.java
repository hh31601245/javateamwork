package poi;
import model.Defendant;
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
	public static String LoadAllWord(String title) throws IOException{
        String result=null;
		try
		{
		//	InputStream is = new FileInputStream(new File("E:\java高级\舟山\\（2016）浙0902刑初00262号.doc"));
			InputStream is = new FileInputStream(new File(title));
			WordExtractor ex = new WordExtractor(is);  
            String text2003 = ex.getText();  
            //System.out.println(text2003);
            result=text2003;
			
		}catch(Exception e1)
		{
			//OPCPackage opcPackage = POIXMLDocument.openPackage(URL.WordURL); 
			OPCPackage opcPackage = POIXMLDocument.openPackage(title); 
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
	public static List<String> LoadDefendantWord(String title)
	{
		List<String> result1=new ArrayList<String>();
		String defendant=null;
		try {
			String word=LoadWord.LoadAllWord(title);
			if(word.contains("检察院")==false||word.contains("判决如下：")==false||word.contains("、")==false||word.contains("被告人")==false)
			{
				return null;
			}
			String[] wordlist=word.split("检察院")[1].split("\n");
			
			String word2=word.split("判决如下：")[1];
			String[] wordlist2=word2.split("、");
			/*for(int i=1;i<wordlist.length;i++)
			{
					System.out.println(i);
					System.out.println(wordlist[i]);
					System.out.println("#####");
	
			}
			for(int i=0;i<wordlist2.length;i++)
			{
					System.out.println(i);
					System.out.println(wordlist2[i]);
					System.out.println("#####");
	
			}*/
			int j=1;
			for(int i=1;i<wordlist.length;i++)
			{
				
				if(wordlist[i].contains("被告人"))
				{
					//System.out.println(i);
					//System.out.println(wordlist[i]+"\n"+wordlist2[i-1]);
					//System.out.println("#####");
					defendant=wordlist[i]+wordlist2[j];
					result1.add(defendant);
					j++;
				}
				else
				{
					
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
        result = LoadWord.LoadDefendantWord("E:\\java高级\\舟山\\（2017）浙0922刑初50号.docx");
          for(String a:result)
          {
        	  System.out.println(a);
        	  System.out.println("---------");
          }
	}
}
