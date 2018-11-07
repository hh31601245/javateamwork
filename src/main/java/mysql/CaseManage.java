package mysql;
import java.util.List;

import itf.ICaseManage;
import model.*;
import util.BaseException;
public class CaseManage implements ICaseManage{

	public static void main(String[] args) { //测试用
		// TODO Auto-generated method stub
		Case c=new Case("001", "1", 0, 0, null, null, null, null, null, 0, null, null); //填入一些值
		Defendant d=new Defendant("1", "a", null, 0, null, null, null, null, null, null, 0,null); //
		Relation r=new Relation("1", "a", "001", "1");  
		new CaseManage();
		
	}
	/**
	 * addCase
	 * @param c
	 * @return
	 * @throws BaseException
	 * 先判断c.cid在数据库Case中是否存在，存在的话，就输出该案件已存在的错误信息
	 * 不存在的话，就存入数据库
	 */
	@Override
	public void addCase(Case c) throws BaseException {
		// TODO Auto-generated method stub
		
	}
	/**
	 * loadAll
	 * @return
	 * @throws BaseException
	 * 要求：返回所有案件的信息
	 * 要求用列表返回所有Case信息
	 */
	@Override
	public List<Case> loadAll() throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * deleteCase
	 * @param c
	 * @throws BaseException
	 * 要求:
	 * 先要遍历relation这个数据库，找到Bname是否有等于c.cid的，有的话，获取所有对应的Aname的值,删掉对应的relation
	 * ，用searchDefendant(Aname)的方法找到list的列表然后用deleteDefendant()的方法删掉他们
	 * 然后再删除数据删除数据
	 */
	@Override
	public void deleteCase(Case c) throws BaseException {
		// TODO Auto-generated method stub
		
	}
	/**
	 * searchCase
	 * @param cid
	 * @throws BaseException
	 * 要求：
	 * 如果cid不存在,显示该案件编号不存在
	 * 存在的话,返回整个Case类 
	 */
	@Override
	public Case searchCase(String cid) throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * modifyCase
	 * @param c
	 * @throws BaseException
	 * 要求：
	 * 判断数据库中是否有c.cid的对象在，如果不存在,显示数据不存在
	 * 如果存在,就把那条数据的属性用c的属性替代掉
	 */
	@Override
	public void modifyCase(Case c) throws BaseException {
		// TODO Auto-generated method stub
		
	}

}
