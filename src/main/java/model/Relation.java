package model;

/**
 * 
 * @author Administrator
 *	表3名：Relation 两个节点的联系
	用来存一些联系，用在neo4j中，像案件和被告人，还有犯人
	看是否同乡，同岁，同样无业，让后在“经审理查明”之后，“上述事实”或者“以上事实”之间部分的相关内容  被告人[\A-\Z\d.、（） ，\u4e00-\u9fa5]+将[\d.、（） ，\u4e00-\u9fa5]+
	Rid  编号
	Aname 节点A  
	Bname 节点B
	Relate  联系

 */

public class Relation {
	public static final String[] TABLE_TITLE={"编号","节点A","节点B","两者联系"};
	/**
	 * 请自行根据javabean的设计修改本函数代码，col表示界面表格中的列序号，0开始
	 */
	public String getCell(int col){
		if(col==0) {
			return this.getRid();
		} else if(col==1) {
			return this.getAname();
		}else if(col==2) {
			return this.getBname();
		} else if(col==3) {
			return this.getRelate();
		} else {
			return "";
		}
	}
	private String Rid;
	private String Aname;
	private String Bname;
	private String Relate;
	public Relation(String Rid,String Aname,String Bname,String Relate)
	{
		this.Rid=Rid;
		this.Aname=Aname;
		this.Bname=Bname;
		this.Relate=Relate;
	}
	public String getRid() {
		return Rid;
	}
	public void setRid(String rid) {
		Rid = rid;
	}
	public String getAname() {
		return Aname;
	}
	public void setAname(String aname) {
		Aname = aname;
	}
	public String getBname() {
		return Bname;
	}
	public void setBname(String bname) {
		Bname = bname;
	}
	public String getRelate() {
		return Relate;
	}
	public void setRelate(String relate) {
		Relate = relate;
	}
	
}
