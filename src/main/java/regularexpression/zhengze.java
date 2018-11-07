package regularexpression;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class zhengze {
		public static void main(String[] args)
		{
			Pattern p=Pattern.compile("(\\w+)(&&)"); //括号里写正则表达式
			Matcher m= p.matcher("fssdsdf&&12346");  //括号里是目标字符串
			//System.out.println(m.matches());  //matches()匹配的是整个区域
	        // System.out.println(m.find());  //这个匹配了第一个符合要求的区域 fssdsdf,find()是查找是否有与这个正则表达式匹配的下一个子字符串
	        // System.out.println(m.group());   //这个是显示find的子字符串
	         //System.out.println(m.find());   //这个匹配了第二个符合要求的区域12346
	         //System.out.println(m.group());
	         //System.out.println(m.find());  //接下来就没有符合要求的了
	       //  System.out.println(m.group());
	         //System.out.println(m.find());
	       //  System.out.println(m.group());
	         while(m.find())
	         {
	        	 System.out.println(m.group());//如果正则表达是有（）（）好几个捕获组。group（1）就是第一个（）里的正则,不写或0就是整个的表达式
	             System.out.println(m.group(2));
	             System.out.println("---------");
	         }
	         
	         Pattern p1=Pattern.compile("[0-9]");
	         Matcher m1=p1.matcher("aa465**456sf**sfssf123");
	         //替换
	         String str=m1.replaceAll("#");  //降符合正则表达式的变成#
	         System.out.println(str);
	         
		}
}
