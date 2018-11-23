package csv;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;

import model.Case;
import model.Defendant;
import util.BaseException;

public class Lead_out {
	public static void write(){

        String filePath = "Case.csv";
        String filePath1="Defendant.csv";
        try {
            // 创建CSV写对象
            CsvWriter csvWriter = new CsvWriter(filePath,',', Charset.forName("GBK"));
            //CsvWriter csvWriter = new CsvWriter(filePath);
            CsvWriter csvWriter1 = new CsvWriter(filePath1,',', Charset.forName("GBK"));
            // 写表头"案件号","年份","法院名","涉案人数","最小年龄","第一被告","毒品种类和数量或单位","毒品单价"
            String[] headers = model.Case.TABLE_TITLE;
            csvWriter.writeRecord(headers);
            //"编号","人名","性别","年龄","出生年月日",
            //"学历","罪名","刑罚种类","刑期","财产刑种类","财产刑金额","案件号"
            String[] headers1=model.Defendant.TABLE_TITLE;
            csvWriter1.writeRecord(headers1);
            for(int i=0;i<Case.caselist.size();i++)
            {
            	Case cases=Case.caselist.get(i);
            	String[] content= {cases.getCid(),cases.getCyear(),cases.getCourtName(),String.valueOf(cases.getNumberPeople())
            			,String.valueOf(cases.getMinAge()),cases.getFirstDefendant(),cases.getDrugTypeAndNumberOrUnit(),
            			cases.getDrugPrice()};
            	csvWriter.writeRecord(content);
            	List<Defendant> defendantlist=null;
				try {
					defendantlist = begin.Util.defendantmanage.loadDefendant(cases);
				} catch (BaseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	for(Defendant defendant:defendantlist)
            	{
            		String[] content1= {defendant.getDid(),defendant.getDname(),defendant.getSex()
            				,String.valueOf(defendant.getAge()),defendant.getDateOfBrith(),defendant.getEducation()
            				,defendant.getCrime(),defendant.getPenaltyType(),defendant.getSentence()
            				,defendant.getPropertyPenaltyType(),String.valueOf(defendant.getPropertyPenaltyAmount())
            				,defendant.getCid()};
            		csvWriter1.writeRecord(content1);
            	}
            }
            csvWriter.close();
            csvWriter1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	public static void main(String[] args)
	{
		Lead_out.write();
	}
}
