package trendJudge;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.lucene.queryparser.flexible.standard.parser.ParseException;

import model.Case;
import model.Defendant;
import util.BaseException;

public class agetrendjudge {
	public static Map<String,String> judgetrend(List<Case> caselist)
	{
		Map<String,String> result=new TreeMap<String,String>();  //用treeMap就自动排序了
		//result=null; 
		String year;
		String age;
		for(Case c:caselist)
		{
			String New=c.getCyear().replaceAll("年","-");
			New=New.replaceAll("月","-");
			New=New.replaceAll("日","");
			String newyear=New.split("-")[0];
			System.out.println(New);
			System.out.println(newyear);
			System.out.println(New.split("-")[1]);
			System.out.println(New.split("-")[2]);
			String newmonth=String.format("%02d",Integer.valueOf(New.split("-")[1]));
			String newday=String.format("%02d",Integer.valueOf(New.split("-")[2]));
			newyear=newyear+"-"+newmonth+"-"+newday;
			if(result!=null&&result.containsKey(newyear))
			{
				int oldage=Integer.valueOf(result.get(newyear));
				if(c.getMinAge()<oldage)
				{
					result.put(newyear,String.valueOf(c.getMinAge()));
				}
			}
			else
			{
				result.put(newyear,String.valueOf(c.getMinAge()));
			}
			
		}
		/*TreeSet<String> yearlist = new TreeSet<String>();
		for(String k1:result.keySet())
		{
			
			System.out.println(k1+" "+result.get(k1));
			yearlist.add(k1);
		}*/
		/*List<Entry<String,String>> list=new ArrayList<>(result.entrySet());
		Collections.sort(list,new Comparator<Entry<String,String>>(){

            @Override
            public int compare(Entry<String,String> o1, Entry<String, String> o2) {

                //return (int) (o1.getValue().getId()-o2.getValue().getId());
                    //*对比两个字符串
                //return o1.getValue().getName().compareTo(o2.getValue().getName());
                    //*将时间转化成毫秒并比较
                //return String.valueOf(o1.getValue().getBirthday().getTime()).compareTo(String.valueOf(o2.getValue().getBirthday().getTime()));
                    //*比较时间中的“日”    
                //return o1.getValue().getBirthday().getDate()-o2.getValue().getBirthday().getDate();
                //*比较时间 
                return o1.getKey().compareTo(o2.getKey());  

            }

        });*/
		/*Map<String,String> result1=new TreeMap<String,String>();
		//result1=null;
		for(String k:yearlist)
		{
			System.out.println(k+" "+result.get(k));
			String min=result.get(k);
			result1.put(k,min);
		}
		for(String k2:result1.keySet())
		{
			System.out.println(k2+" "+result1.get(k2));
		}*/
		return result;
	}
	public static String conclusion(Map<String,String> a)
	{
		String[] age={"<18","18~30岁","30~40岁","40~50岁",">=50岁"};
		Integer[] count= {0,0,0,0,0};
		for(int i=0;i<model.Case.caselist.size();i++)
		{
			try {
				List<Defendant> defendantlist=begin.Util.defendantmanage.loadDefendant(model.Case.caselist.get(i));
				for(Defendant d:defendantlist)
				{
					if(d.getAge()>=50)
					{
						count[4]++;
					}else if(d.getAge()>=40)
					{
						count[3]++;
					}else if(d.getAge()>=30)
					{
						count[2]++;
					}else if(d.getAge()>=18)
					{
						count[1]++;
					}else
					{
						count[0]++;
					}
				}	
			} catch (BaseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		int maxage=count[0];
		for(int i=0;i<5;i++)
		{
			if(count[i]>maxage)
			{
				maxage=count[i];
			}
		}
		String someresult=null;
		if(maxage==count[0])
		{
			someresult="年龄在18岁以下的人最多(详情看neo4j)";
		}
		else if(maxage==count[1])
		{
			someresult="年龄在18到30岁的人最多(详情看neo4j)";
		}
		else if(maxage==count[2])
		{
			someresult="年龄在30到40岁的人最多(详情看neo4j)";
		}
		else if(maxage==count[3])
		{
			someresult="年龄在40到50岁的人最多(详情看neo4j)";
		}
		else if(maxage==count[4])
		{
			someresult="年龄在50以上的人最多(详情看neo4j)";
		}
		int baseage=0;
		int count1=0;
		int i=0;
		for(String key:a.keySet())
		{
			if(i==0)
			{
				baseage=Integer.valueOf(a.get(key));
				i++;
			}
			else
			{
				//count=count+(Integer.valueOf(a.get(key))-baseage);
				count1=count1+(Integer.valueOf(a.get(key))-baseage);
				baseage=Integer.valueOf(a.get(key));
			}
		}
		if(count1>0)
		{
			return "犯罪的最小年龄总体呈上升趋势(详情查看折线图)a"+someresult;
		}
		else if(count1<0)
		{
			return "犯罪的最小年龄总体呈下降趋势(详情查看折线图)a"+someresult;
		}
		else
		{
			return "犯罪的最小年龄总体趋势不变(详情查看折线图)a"+someresult;
		}
	}
}
