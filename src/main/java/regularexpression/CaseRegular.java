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
	public String getCyear(String word,String url) {
		// TODO Auto-generated method stub
		String result=null;
		word=word.replaceAll(" ","");
		Pattern p=Pattern.compile("（[\\d]+）浙\\d+刑初\\d+号"); //括号里写正则表达式
		Matcher m= p.matcher(url);  //括号里是目标字符串
		while(m.find())
		{
			//System.out.println(m.group());
			result=m.group().split("（")[1].split("）浙")[0];
			break;
		}
		result=result+"年";
		word=word.split("如不服本判决")[1].split("书记员")[0];
		p=Pattern.compile("[一二三四五六七八九十]+月[一二三四五六七八九十]+日");
		m=p.matcher(word);
		while(m.find())
		{
			String strmouth=m.group().split("月")[0];
			int  mounth=(int)this.chineseNumber2Double(strmouth);
			String strday=m.group().split("月")[1].split("日")[0];
			int day=(int)this.chineseNumber2Double(strday);
			result=result+String.valueOf(mounth)+"月"+String.valueOf(day)+"日";
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
		word1=word1.replaceAll(" ","");
		Pattern p=Pattern.compile("[,，;；。）}\\b以]([^,，;；。）}\\b\\d]*)([\\d\\s.]+|[一二三四五六七八九十]+)(元|人民币)([^\\d.一二三四五六七八九十]*)([\\d\\s.]+|[一二三四五六七八九十]+)(微克|毫克|克|粒|小包|包|公斤|千克|斤|吨|袋|小袋)([^,，;；()（）{}]+)(甲基苯丙胺|冰毒|大麻|可卡因|海洛因|吗啡|卡西酮|鸦片|K粉|摇头丸|杜冷丁|古柯|咖啡因|三唑仑|羟基丁酸)");
		Matcher m=p.matcher(word1);
		while(m.find())
		{
			result=m.group();
		}
		/*Pattern p=Pattern.compile("将[\\d\\u4e00-\\u9fa5、，.]+");
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
//		}*/
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
    	 String[] title= {"D:\\java高级\\舟山\\（2016）浙0902刑初00262号.doc"
    			 ,"D:\\java高级\\舟山\\（2016）浙0903刑初00252号.doc",
    			 "D:\\java高级\\舟山\\（2017）浙0902刑初110号.doc",
    			 "D:\\java高级\\舟山\\（2017）浙0902刑初218号.doc",
    			 "D:\\java高级\\舟山\\（2017）浙0902刑初239号.doc",
    			 "D:\\java高级\\舟山\\（2017）浙0902刑初244号.doc",
    			 "D:\\java高级\\舟山\\（2017）浙0902刑初250号.doc",
    			 "D:\\java高级\\舟山\\（2017）浙0902刑初382号.doc",
    			 "D:\\java高级\\舟山\\（2017）浙0903刑初21号.doc",
    			 "D:\\java高级\\舟山\\（2017）浙0903刑初29号.doc",
    			 "D:\\java高级\\舟山\\（2017）浙0903刑初31号.doc",
    			 "D:\\java高级\\舟山\\（2017）浙0903刑初281号.doc",
    			 "D:\\java高级\\舟山\\（2017）浙0903刑初323号.doc",
    			 "D:\\java高级\\舟山\\（2017）浙0903刑初366号.doc",
    			 "D:\\java高级\\舟山\\（2017）浙0921刑初53号.doc",
    			 "D:\\java高级\\舟山\\（2017）浙0921刑初91号.doc",
    			 "D:\\java高级\\舟山\\（2017）浙0921刑初114号.doc",
    			 "D:\\java高级\\舟山\\（2017）浙0922刑初13号.docx",
    			 "D:\\java高级\\舟山\\（2017）浙0922刑初32号.docx",
    			 "D:\\java高级\\舟山\\（2017）浙0922刑初47号.docx",
    			 "D:\\java高级\\舟山\\（2017）浙0922刑初50号.docx"
    			 };
    	 String[] word=new String[title.length];
    	 try {
    		 for(int i=0;i<title.length;i++)
			     word[i]=poi.LoadWord.LoadAllWord(title[i]);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	 CaseRegular c=new CaseRegular();
    	 for(int i=0;i<title.length;i++)
    	 System.out.println(title[i]+" "+c.getCyear(word[i],title[i]));
     }
     
}
