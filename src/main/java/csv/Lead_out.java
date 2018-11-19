package csv;

import java.io.IOException;
import java.nio.charset.Charset;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import model.Case;

public class Lead_out {
	public static void write(){

        String filePath = "Case.csv";
        String filePath1="Defendant.csv";
        try {
            // 创建CSV写对象
            CsvWriter csvWriter = new CsvWriter(filePath,',', Charset.forName("GBK"));
            //CsvWriter csvWriter = new CsvWriter(filePath);

            // 写表头"案件号","年份","法院名","涉案人数","最小年龄","第一被告","毒品种类和数量或单位","毒品单价"
            String[] headers = model.Case.TABLE_TITLE;
            for(int i=0;i<Case.caselist.size();i++)
            {
            	Case cases=Case.caselist.get(i);
            	String[] content= {}
            }
            csvWriter.writeRecord(headers);
            csvWriter.writeRecord(content);
            csvWriter.writeRecord(content1);
            csvWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	public static void main(String[] args)
	{
		Lead_out.write();
	}
}
