package itf;

import util.BaseException;

import java.util.List;

import model.Case;
public interface ICaseManage {
	/**
	 * addCase
	 * @param c
	 * @return
	 * @throws BaseException
	 * 先判断c.cid在数据库Case中是否存在，存在的话，就输出该案件已存在的错误信息
	 * 不存在的话，就存入数据库
	 */
	public void addCase(Case c) throws BaseException;
	/**
	 * loadAll
	 * @return
	 * @throws BaseException
	 * 要求：返回所有案件的信息
	 * 要求用列表返回所有Case信息
	 */
	public List<Case> loadAll()throws BaseException;
	/**
	 * deleteCase
	 * @param c
	 * @throws BaseException
	 * 要求:
	 * ，用searchDefendant(Aname)的方法找到list的列表然后用deleteDefendant()的方法删掉他们
	 * 然后再删除数据删除数据
	 */
	public void deleteCase(Case c)throws BaseException;
	/**
	 * searchCase
	 * @param cid
	 * @throws BaseException
	 * 要求：
	 * 如果cid不存在,显示该案件编号不存在
	 * 存在的话,返回整个Case类 
	 */
	public Case searchCase(String cid)throws BaseException;
	/**
	 * modifyCase
	 * @param c
	 * @throws BaseException
	 * 要求：
	 * 判断数据库中是否有c.cid的对象在，如果不存在,显示数据不存在
	 * 如果存在,就把那条数据的属性用c的属性替代掉
	 */
	public void modifyCase(Case c)throws BaseException; 
}
