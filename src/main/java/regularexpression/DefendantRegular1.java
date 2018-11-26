package regularexpression;
import itf.IDefendantRegular;
import model.Case;
import model.Defendant;
import mysql.DefendantManage;
import poi.LoadWord;
import util.BaseException;
import mysql.CaseManage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import begin.Util;
public class DefendantRegular1 implements IDefendantRegular{
    
	public String getCid(String title)
	{
		String result=title;
		return result;
	}
	@Override
	public String getDid(String title) {  //引用数据库
		// TODO Auto-generated method stub
		List<Defendant> defendantlist=new ArrayList<Defendant>();
		try {
			defendantlist=Util.defendantmanage.loadDefendant(Util.casemanage.searchCase(title));
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String Did=String.valueOf(defendantlist.size()+1);
		return Did;
	}

	@Override
	public String getDname(String defendantword) {
		// TODO Auto-generated method stub
		String result=null;
		Pattern p=Pattern.compile("被告人[\u4e00-\u9fa5]+");
		Matcher m=p.matcher(defendantword);
		while(m.find())
		{
			//System.out.println(m.group());
			result=m.group().split("被告人")[1];
			break;
		}
		return result;
	}

	@Override
	public String getSex(String defendantword) {
		// TODO Auto-generated method stub
		String result=null;
		Pattern p=Pattern.compile("[男女]");
		Matcher m=p.matcher(defendantword);
		while(m.find())
		{
			result=m.group();
		}
		return result;
	}

	@Override
	public int getAge(String defendantword) {
		// TODO Auto-generated method stub
		String Birthday=this.getDateOfBrith(defendantword).split("年")[0];
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        String nowyear=sdf.format(date);
		int result=Integer.valueOf(nowyear)-Integer.valueOf(Birthday)+1;
		return result;
	}

	@Override
	public String getDateOfBrith(String defendantword) {
		// TODO Auto-generated method stub
		String result=null;
		Pattern p=Pattern.compile("\\d{4}年\\d+月\\d+日[(出生)|(生)]+");
		Matcher m=p.matcher(defendantword);
		while(m.find())
		{
			if(m.group().contains("出生"))
			{
				result=m.group().split("出生")[0];
			}
			else
			{
				result=m.group().split("生")[0];
			}
		}
		return result;
	}

	@Override
	public String getEducation(String defendantword) {
		// TODO Auto-generated method stub
		String result=null;
		Pattern p=Pattern.compile("(小学文化)|(中学文化)|(高中文化)|(大学本科)|(专科)|(硕士)|(研究生)|(博士)");
		Matcher m=p.matcher(defendantword);
		while(m.find())
		{
			result=m.group();
		}
		return result;
	}

	@Override
	public String getCrime(String defendantword) {  //有问题
		// TODO Auto-generated method stub
		String result=null;
		
		defendantword=defendantword.split("被告人")[2];
		//System.out.println(defendantword);
		Pattern p=Pattern.compile("犯[\\u4e00-\\u9fa5]+罪");
		Matcher m=p.matcher(defendantword);
		while(m.find())
		{
			//System.out.println(m.group());
			//System.out.println(m.group());
			if(result==null)
			{
				result=m.group().split("犯")[1];
			}
			else
			{
				result=result+" / "+m.group().split("犯")[1];
			}
		}
		return result;
	}

	@Override
	public String getPenaltyType(String defendantword) {  //有问题
		// TODO Auto-generated method stub
		String result=null;
		defendantword=defendantword.split("被告人")[2];
		//System.out.println(word);
		if(defendantword.contains("数罪并罚"))
		{
			Pattern p=Pattern.compile("决定执行[\\u4e00-\\u9fa5]+刑");
			Matcher m=p.matcher(defendantword);
			while(m.find())
			{
				//System.out.println(m.group());
				result=m.group().split("决定执行")[1];
				break;
			}
		}
		else
		{
			Pattern p=Pattern.compile("判处[\\u4e00-\\u9fa5]+刑");
			Matcher m=p.matcher(defendantword);
			while(m.find())
			{
				//System.out.println(m.group());
				result=m.group().split("判处")[1];
				break;
			}
		}
		return result;
	}

	@Override
	public String getSentence(String defendantword) {  //有问题
		// TODO Auto-generated method stub
		String result=null;
		defendantword=defendantword.split("被告人")[2];
		//System.out.println(word);
		if(defendantword.contains("数罪并罚"))
		{
			Pattern p=Pattern.compile("决定执行[\\u4e00-\\u9fa5]+");
			Matcher m=p.matcher(defendantword);
			while(m.find())
			{
				//System.out.println(m.group());
				result=m.group().split("刑")[1];
				break;
			}
		}
		else
		{
			Pattern p=Pattern.compile("判处[\\u4e00-\\u9fa5]+");
			Matcher m=p.matcher(defendantword);
			while(m.find())
			{
				//System.out.println(m.group());
				result=m.group().split("刑")[1];
				break;
			}
		}
		return result;
	}

	@Override
	public String getPropertyPenaltyType(String defendantword) {  //有问题
		// TODO Auto-generated method stub
		String result=null;
		defendantword=defendantword.split("被告人")[2];
		if(defendantword.contains("数罪并罚"))
		{
			String word1=defendantword.split("数罪并罚")[1];
			Pattern p=Pattern.compile("并处[\\u4e00-\\u9fa5]+人民币");
			Matcher m=p.matcher(word1);
			while(m.find())
			{
				//System.out.println(m.group());
				result=m.group().split("并处")[1].split("人民币")[0];
				break;
			}
		}
		else
		{
			Pattern p=Pattern.compile("并处[\\u4e00-\\u9fa5]+人民币");
			Matcher m=p.matcher(defendantword);
			while(m.find())
			{
				//System.out.println(m.group());
				result=m.group().split("并处")[1].split("人民币")[0];
				break;
			}
		}
		return result;
	}

	@Override
	public double getPropertyPenaltyAmount(String defendantword) {  //有问题
		// TODO Auto-generated method stub
		double result=0;
		defendantword=defendantword.split("被告人")[2];
		if(defendantword.contains("数罪并罚"))
		{
			String word1=defendantword.split("数罪并罚")[1];
			Pattern p=Pattern.compile("并处[\\u4e00-\\u9fa5]+人民币[\\d\\u4e00-\\u9fa5]+");
			Matcher m=p.matcher(word1);
			while(m.find())
			{
				//System.out.println(m.group());
				result=chineseNumber2Double(m.group().split("人民币")[1]);
				break;
			}
		}
		else
		{
			Pattern p=Pattern.compile("并处[\\u4e00-\\u9fa5]+人民币[\\d\\u4e00-\\u9fa5]+");
			Matcher m=p.matcher(defendantword);
			while(m.find())
			{
				//System.out.println(m.group());
				result=chineseNumber2Double(m.group().split("人民币")[1]);
				break;
			}
		}
		return result;
	}
	 private static double chineseNumber2Double(String chineseNumber){
	        double result = 0;
	        int temp = 1;//存放一个单位的数字如：十万
	        int count = 0;//判断是否有chArr
	        char[] cnArr = new char[]{'一','二','三','四','五','六','七','八','九'};
	        char[] chArr = new char[]{'十','百','千','万','亿'};
	        for (int i = 0; i < chineseNumber.length(); i++) {
	            boolean b = true;//判断是否是chArr
	            char c = chineseNumber.charAt(i);
	            for (int j = 0; j < cnArr.length; j++) {//非单位，即数字
	                if (c == cnArr[j]) {
	                    if(0 != count){//添加下一个单位之前，先把上一个单位值添加到结果中
	                        result += temp;
	                        temp = 1;
	                        count = 0;
	                    }
	                    // 下标+1，就是对应的值
	                    temp = j + 1;
	                    b = false;
	                    break;
	                }
	            }
	            if(b){//单位{'十','百','千','万','亿'}
	                for (int j = 0; j < chArr.length; j++) {
	                    if (c == chArr[j]) {
	                        switch (j) {
	                        case 0:
	                            temp *= 10;
	                            break;
	                        case 1:
	                            temp *= 100;
	                            break;
	                        case 2:
	                            temp *= 1000;
	                            break;
	                        case 3:
	                            temp *= 10000;
	                            break;
	                        case 4:
	                            temp *= 100000000;
	                            break;
	                        default:
	                            break;
	                        }
	                        count++;
	                    }
	                }
	            }
	            if (i == chineseNumber.length() - 1) {//遍历到最后一个字符
	                result += temp;
	            }
	        }
	        return result;
	    }
	public static void main(String[] args)
	{
	/*	List<String> defendantwords=new ArrayList<String>();
		CaseRegular caseregular=new CaseRegular();
		defendantwords=LoadWord.LoadDefendantWord();
		for(String defendantword:defendantwords)
		{
			DefendantRegular defendantregular=new DefendantRegular();
			System.out.println(defendantregular.getPropertyPenaltyAmount(defendantword));
			System.out.println("------------------");
		}*/
	}

}
