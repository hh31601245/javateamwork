package regularexpression;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import begin.Util;
import itf.ICaseRegular;
import poi.LoadWord;
public class CaseRegular implements ICaseRegular{

	@Override
	public String getCid(String title) {
		// TODO Auto-generated method stub
		String result=title;
		return result;
	}
	@Override
	public String getCyear(String word) {
		// TODO Auto-generated method stub
		String result=null;
		Pattern p=Pattern.compile("（[\\d]+）浙\\d+刑初\\d+号"); //括号里写正则表达式
		Matcher m= p.matcher(word);  //括号里是目标字符串
		while(m.find())
		{
			//System.out.println(m.group());
			result=m.group().split("浙")[0];
			break;
		}
		
		return result;
	}
	@Override
	public String getCourtName(String word) {
		// TODO Auto-generated method stub
		String result=null;
		Pattern p=Pattern.compile("公诉机关[\\u4e00-\\u9fa5]+");
		Matcher m=p.matcher(word);
		while(m.find())
		{
			//System.out.println(m.group());
			result=m.group().split("公诉机关")[1];
			break;
		}
		
		return result;
	}

	@Override
	public int getNumberPeople(String url) {
		// TODO Auto-generated method stub
		int result=0;
		List<String> word=LoadWord.LoadDefendantWord(url);
		result=word.size();
		return result;
	}

	@Override
	public int getMinAge(String url) {
		// TODO Auto-generated method stub
		int result=0;
		int count=0;
		String minbirthday= new String();
		List<String> words=LoadWord.LoadDefendantWord(url);
		for(String word:words)
		{
			Pattern p=Pattern.compile("\\d{4}年\\d+月\\d+日[(出生)|(生)]+");
			Matcher m=p.matcher(word);
			while(m.find())
			{
				String birthday=m.group();
				String year=birthday.split("年")[0];
//				String month=birthday.split("年")[1].split("月")[0];
//				String day=birthday.split("年")[1].split("月")[1].split("日")[0];
				if(count==0)
				{
				   minbirthday=year;
//				   minbirthday[1]=month;
//				   minbirthday[2]=day;
				}
				else
				{
					if(Integer.valueOf(minbirthday)<Integer.valueOf(year))
					{
						minbirthday=year;
//						minbirthday[1]=month;
//						minbirthday[2]=day;
					}
				}
				count++;
			}
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        String nowyear=sdf.format(date);
		result=Integer.valueOf(nowyear)-Integer.valueOf(minbirthday)+1;
		return result;
	}

	@Override
	public String getFirstDefendant(String url) {
		// TODO Auto-generated method stub
		String result=null;
		List<String> words=LoadWord.LoadDefendantWord(url);
		String word=words.get(0);
		Pattern p=Pattern.compile("被告人[\u4e00-\u9fa5]+");
		Matcher m=p.matcher(word);
		while(m.find())
		{
			//System.out.println(m.group());
			result=m.group().split("被告人")[1];
			break;
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
	@Override
	public String getDrugTypeAndNumberOrUnit(String word) {
		// TODO Auto-generated method stub
		String result=null;
		String word1;
		int count=0;
		word = word.split("经审理查明")[1];
		if(word.contains("上述事实"))
		{
			word1=word.split("上述事实")[0];
		}
		else
		{
			word1=word.split("以上事实")[0];
		}
		Pattern p=Pattern.compile("将[\\d\\u4e00-\\u9fa5、，.]+");
		Matcher m=p.matcher(word1);
		while(m.find())
		{
			//System.out.println(m.group());
			String content=m.group().split("将")[1].split("贩卖")[0]+"共"+m.group().split("人民币")[1];
			
			if(content.contains("片剂"))
			{
				content=unitConvert(content,word);  //将片剂的单位转成克
			}
			if(count==0)
			{
				result=content;
			}
			//System.out.println(m.group());
			else
			{
				if(result.contains(content)==false)
				{
			        result=result+"/"+content;
				}
			}
			count++;
		}
		//System.out.println(word1);
//		Pattern p=Pattern.compile("被告人[\\u4e00-\\u9fa5]+犯[\\u4e00-\\u9fa5]+罪");
//		Matcher m=p.matcher(word);
//		while(m.find())
//		{
//			//System.out.println(m.group());
//			result=m.group().split("犯")[1];
//			break;
//		}
		return result;
	}
    public String unitConvert(String content,String word)
    {
    	//String result=null;
		String word1;
		String oldnumber=null;
		String newnumber=null;
		int count=0;
		Pattern p=Pattern.compile("[\\d]粒");
		Matcher m=p.matcher(content);
		while(m.find())
		{
			oldnumber=m.group();
			//System.out.println(oldnumber);
		}
		if(word.contains("上述事实"))
		{
			word1=word.split("上述事实")[0];
		}
		else
		{
			word1=word.split("以上事实")[0];
		}
		p=Pattern.compile("每粒[\\d\\u4e00-\\u9fa5.]+");
		m=p.matcher(word1);
		while(m.find())
		{
			Pattern p1=Pattern.compile("[\\d.]+");
			Matcher m1=p1.matcher(m.group());
			//System.out.println(m.group());
			while(m1.find())
			{
				newnumber=String.valueOf(Integer.valueOf(oldnumber.split("粒")[0])*Double.valueOf(m1.group()));
				
			}
			newnumber=newnumber+"克";
			//System.out.println(newnumber);
			content=content.replace(oldnumber,newnumber);
		}
		
		return content;
    }
	@Override
	public String getDrugPrice(String word) {
		// TODO Auto-generated method stub
		String result=null;
		double price=0;
		double number=0;
		double sumprice=0;
		String[] sumPrices=this.getDrugTypeAndNumberOrUnit(word).split("/");
		//System.out.println(this.getDrugTypeAndNumberOrUnit().split("/")[0]);
		for(int i=0;i<sumPrices.length;i++)
		{
			//System.out.println(sumPrices[i]);
			Pattern p=Pattern.compile("[\\d.]+[克包小袋粒]+");
			Matcher m=p.matcher(sumPrices[i]);
			while(m.find())
			{
				//System.out.println(m.group());
				Pattern p1=Pattern.compile("[\\d.]+");
				Matcher m1=p1.matcher(m.group());
				while(m1.find())
				{
					number=Double.valueOf(m1.group());
					//System.out.println(Double.valueOf(m1.group()));
				}
				
			}
			
			p=Pattern.compile("共[\\d.]+");
			m=p.matcher(sumPrices[i]);
			while(m.find())
			{
				sumprice=sumprice+Double.valueOf(m.group().split("共")[1]);
			}
			price=sumprice/number;
			//System.out.println(number+" "+sumprice+" "+price);
			if(i==0)
			{
				result=sumPrices[i].split("共")[0]+"的单价为"+String.valueOf(price)+"人民币";
			}
			else
			{
				result=result+" / "+sumPrices[i].split("共")[0]+"的单价为"+String.valueOf(price)+"人民币";
			}
		}
		
		return result;
	}
     public static void main(String[] args)
     {
    	 CaseRegular c=new CaseRegular();
     }
     
}
