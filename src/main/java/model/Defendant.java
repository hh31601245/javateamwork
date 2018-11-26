package model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Administrator
*	表2名：Defendant 被告人
	属性：
	Did 编号
	Dname  人名   根据case表的涉案人数，来取第一个被告人出现起的下面X段，然后被告人[\u4e00-\u9fa5]+
	Sex 性别  男|女
	Age年龄   \d{4}年\d+月\d+日((出生)|(生)) 减一下
	DateOfBrith出生年月日   \d{4}年\d+月\d+日((出生)|(生))
	Hometown  出生地   出生于[\u4e00-\u9fa5]
	Education学历  是否无业  无业如果有，就写无业，否则写有业
	Crime 罪名    判决如下之后的文章被告人XX犯[\u4e00-\u9fa5]罪
	PenaltyType 刑罚种类  判决如下之后的文章通过回车分成好几段文字，if(有数罪并罚)，决定执行[\u4e00-\u9fa5]+（这里的还包括了刑期）else判处[\u4e00-\u9fa5]+
	Sentence  刑期  判决如下之后的文章通过回车分成好几段文字，，if(有数罪并罚)，决定执行[\u4e00-\u9fa5]+（这里的还包括了刑期）else判处[\u4e00-\u9fa5]+
	PropertyPenaltyType 财产刑种类  判决如下之后的文章通过回车分成好几段文字，if(有数罪并罚) 取数罪并罚后的文字，并处[\u4e00-\u9fa5]+人民币 else 并处[\u4e00-\u9fa5]+ 人民币
	PropertyPenaltyAmount 财产刑金额  判决如下之后的文章通过回车分成好几段文字，if(有数罪并罚) 取数罪并罚后的文字，并处[\u4e00-\u9fa5]+人民币[\d\u4e00-\u9fa5]+ else 并处[\u4e00-\u9fa5]+ 人民币[\d\u4e00-\u9fa5]+
    Cid  案件号
 */
public class Defendant {
	public static final String[] TABLE_TITLE={"编号","人名","性别","年龄","出生年月日",
			                                  "学历","罪名","刑罚种类","刑期","财产刑种类","财产刑金额","案件号"};
	/**
	 * 请自行根据javabean的设计修改本函数代码，col表示界面表格中的列序号，0开始
	 */
	public String getCell(int col){
		if(col==0) {
			return this.getDid();
		} else if(col==1) {
			return this.getDname();
		} else if(col==2) {
			return this.getSex();
		} else if(col==3) {
			return String.valueOf(this.getAge());
		} else if(col==4) {
			return this.getDateOfBrith();
		} else if(col==5) {
			return this.getEducation();
		} else if(col==6) {
			return this.getCrime();
		} else if(col==7) {
			return this.getPenaltyType();
		} else if(col==8) {
			return this.getSentence();
		} else if(col==9) {
			return this.getPropertyPenaltyType();
		} else if(col==10) {
			return String.valueOf(this.getPropertyPenaltyAmount());
		}else if(col==11)
		{
			return this.getCid();
		}
		else {
			return "";
		}
	}
	private String Did;
	private String Dname;
	private String Sex;
	private int Age;
	private String DateOfBrith;
	private String Education;
	private String Crime;
	private String PenaltyType;
	private String Sentence;
	private String PropertyPenaltyType;
	private double PropertyPenaltyAmount;
	private String Cid;
	public Defendant()
	{}
	public Defendant(String Did,String Dname,String Sex,int Age,String DateOfBrith,
	         String Education,String Crime,String PenaltyType,String Sentence,
	         String PropertyPenaltyType,double PropertyPenaltyAmount,String Cid)
	{
	this.Did=Did;
	this.Dname=Dname;
	this.Sex=Sex;
	this.Age=Age;
	this.DateOfBrith=DateOfBrith;
	this.Education=Education;
	this.Crime=Crime;
	this.PenaltyType=PenaltyType;
	this.Sentence=Sentence;
	this.PropertyPenaltyType=PropertyPenaltyType;
	this.PropertyPenaltyAmount=PropertyPenaltyAmount;
	this.Cid=Cid;
	}
	public String getCid() {
		return Cid;
	}
	public void setCid(String cid) {
		Cid = cid;
	}
	public String getDid() {
		return Did;
	}
	public void setDid(String did) {
		Did = did;
	}
	public String getDname() {
		return Dname;
	}
	public void setDname(String dname) {
		Dname = dname;
	}
	public String getSex() {
		return Sex;
	}
	public void setSex(String sex) {
		Sex = sex;
	}
	public int getAge() {
		return Age;
	}
	public void setAge(int age) {
		Age = age;
	}
	public String getDateOfBrith() {
		return DateOfBrith;
	}
	public void setDateOfBrith(String dateOfBrith) {
		DateOfBrith = dateOfBrith;
	}
	public String getEducation() {
		return Education;
	}
	public void setEducation(String education) {
		Education = education;
	}
	public String getCrime() {
		return Crime;
	}
	public void setCrime(String crime) {
		Crime = crime;
	}
	public String getPenaltyType() {
		return PenaltyType;
	}
	public void setPenaltyType(String penaltyType) {
		PenaltyType = penaltyType;
	}
	public String getSentence() {
		return Sentence;
	}
	public void setSentence(String sentence) {
		Sentence = sentence;
	}
	public String getPropertyPenaltyType() {
		return PropertyPenaltyType;
	}
	public void setPropertyPenaltyType(String propertyPenaltyType) {
		PropertyPenaltyType = propertyPenaltyType;
	}
	public double getPropertyPenaltyAmount() {
		return PropertyPenaltyAmount;
	}
	public void setPropertyPenaltyAmount(double propertyPenaltyAmount) {
		PropertyPenaltyAmount = propertyPenaltyAmount;
	}
}
