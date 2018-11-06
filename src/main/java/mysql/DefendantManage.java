package mysql;
import java.util.List;

import itf.IDefendantManage;
import model.Case;
import model.Defendant;
import model.Relation;
import util.BaseException;
public class DefendantManage implements IDefendantManage{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Case c=new Case("001", "1", 0, 0, null, null, null, null, null, 0, null, null); //填入一些值
		Defendant d=new Defendant("1", "a", null, 0, null, null, null, null, null, null, 0,null); //
		Relation r=new Relation("1", "a", "001", "1");  
		DefendantManage test=new DefendantManage();
		//test.addDefendant(d);  然后像这样的感觉测试知道能用
	}
	/**
	 * addDefendant
	 * @param d
	 * @throws BaseException
	 * 要求：
	 * 将d的Did编号等于Defendant数据库中数据个数加一。
	 * 存入数据库
	 */
	@Override
	public void addDefendant(Defendant d) throws BaseException {
		// TODO Auto-generated method stub
		
	}
	/**
	 * loadAll
	 * @return
	 * @throws BaseException
	 * 要求：
	 * 通过查找Defendant表中Cid=c.cid的值然后输出
	 */
	@Override
	public List<Defendant> loadDefendant(Case c) throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * deleteDefendant d
	 * @param d
	 * @throws BaseException
	 * 要求:
	 * 删除的同时，要遍历调用deleteRelation(Relation r) 把r.Aname等于d.dname的relation删掉
	 * 同时将数据库Defendant中该数据之后的数据的Did都减一，来使id连续
	 */
	@Override
	public void deleteDefendant(Defendant d) throws BaseException {
		// TODO Auto-generated method stub
		
	}
	/**
	 * searchDefendant
	 * @param dname
	 * @throws BaseException
	 * 要求：
	 * 如果dname不存在,显示该被告编号不存在
	 * 存在的话,返回List<Defendant> 
	 */
	@Override
	public List<Defendant> searchDefendant(String dname) throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * modifyDefendant
	 * @param d
	 * @throws BaseException
	 * 要求：
	 * 判断数据库中是否有d.did的对象在，如果不存在,显示数据不存在
	 * 如果存在,就把那条数据的属性用c的属性替代掉
	 */
	@Override
	public void modifyDefendant(Defendant d) throws BaseException {
		// TODO Auto-generated method stub
		
	}

}
