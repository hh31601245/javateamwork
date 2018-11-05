package model;

/**
 * 
 * @author Administrator
 *	表1名： case
	属性：
	Cid  案件号     浙\d+刑初\d+号
	CourtName 法院名   公诉机关[\u4e00-\u9fa5]+
	NumberPeople 涉案人数   count(判决如下之后的文章的被告人之和)
	MinAge 最小年龄      max（\d{4}年\d+月\d+日出生）
	FirstDefendant 第一被告   判决如下之后的文章的被告人的第一个被告人[\u4e00-\u9fa5]+
	Crime 罪名             判决如下之后的文章通过回车分成好几段文字，选第一段，被告人XX犯[\u4e00-\u9fa5]罪
	PenaltyType 刑罚种类  判决如下之后的文章通过回车分成好几段文字，选第一段，if(有数罪并罚)，决定执行[\u4e00-\u9fa5]+（这里的还包括了刑期）else判处[\u4e00-\u9fa5]+
	Sentence  刑期  判决如下之后的文章通过回车分成好几段文字，选第一段，if(有数罪并罚)，决定执行[\u4e00-\u9fa5]+（这里的还包括了刑期）else判处[\u4e00-\u9fa5]+
	
	PropertyPenaltyType 财产刑种类   判决如下之后的文章通过回车分成好几段文字，选第一段，if(有数罪并罚) 取数罪并罚后的文字，并处[\u4e00-\u9fa5]+人民币 else 并处[\u4e00-\u9fa5]+ 人民币
	PropertyPenaltyAmount 财产刑金额  判决如下之后的文章通过回车分成好几段文字，选第一段，if(有数罪并罚) 取数罪并罚后的文字，并处[\u4e00-\u9fa5]+人民币[\d\u4e00-\u9fa5]+ else 并处[\u4e00-\u9fa5]+ 人民币[\d\u4e00-\u9fa5]+
	DrugTypeAndNumberOrUnit 毒品种类和数量或单位  “经审理查明”之后，“上述事实”或者“以上事实”之间部分的相关内容，将[\d.、（） ，\u4e00-\u9fa5]+ 然后在根据得到的长句子在正则一下
	DrugPrice 毒品单价  总价/数量

 */
public class Case {
	public static final String[] TABLE_TITLE={"案件号","法院名","涉案人数","最小年龄","第一被告","罪名",
			                                   "刑罚种类","刑期","财产刑种类","财产刑金额",
			                                   "毒品种类和数量或单位","毒品单价"};
	/**
	 * 请自行根据javabean的设计修改本函数代码，col表示界面表格中的列序号，0开始
	 * 
	 */
	public String getCell(int col){
		if(col==0) {
			return this.getCid();
		} else if(col==1) {
			return this.getCourtName();
		} else if(col==2) {
			return String.valueOf(this.getNumberPeople());
		} else if(col==3) {
			return String.valueOf(this.getMinAge());
		} else if(col==4) {
			return this.getFirstDefendant();
		} else if(col==5) {
			return this.getCrime();
		} else if(col==6) {
			return this.getPenaltyType();
		} else if(col==7) {
			return this.getSentence();
		} else if(col==8) {
			return this.getPropertyPenaltyType();
		} else if(col==9) {
			return String.valueOf(this.getPropertyPenaltyAmount());
		} else if(col==10) {
			return this.getDrugTypeAndNumberOrUnit();
		} else if(col==11) {
			return this.getDrugPrice();
		} else {
			return "";
		}
	}
	private String Cid;
	private String CourtName;
	private int NumberPeople;
	private int MinAge;
	private String FirstDefendant;
	private String Crime;
	private String PenaltyType;
	private String Sentence;
	private String PropertyPenaltyType;
	private double PropertyPenaltyAmount;
	private String DrugTypeAndNumberOrUnit;
	private String DrugPrice;
	public String getCid() {
		return Cid;
	}
	public void setCid(String cid) {
		Cid = cid;
	}
	public String getCourtName() {
		return CourtName;
	}
	public void setCourtName(String courtName) {
		CourtName = courtName;
	}
	public int getNumberPeople() {
		return NumberPeople;
	}
	public void setNumberPeople(int numberPeople) {
		NumberPeople = numberPeople;
	}
	public int getMinAge() {
		return MinAge;
	}
	public void setMinAge(int minAge) {
		MinAge = minAge;
	}
	public String getFirstDefendant() {
		return FirstDefendant;
	}
	public void setFirstDefendant(String firstDefendant) {
		FirstDefendant = firstDefendant;
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
	public String getDrugTypeAndNumberOrUnit() {
		return DrugTypeAndNumberOrUnit;
	}
	public void setDrugTypeAndNumberOrUnit(String drugTypeAndNumberOrUnit) {
		DrugTypeAndNumberOrUnit = drugTypeAndNumberOrUnit;
	}
	public String getDrugPrice() {
		return DrugPrice;
	}
	public void setDrugPrice(String drugPrice) {
		DrugPrice = drugPrice;
	}
	

}
