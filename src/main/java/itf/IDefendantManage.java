package itf;

import java.util.List;

import model.Case;
import model.Defendant;
import util.BaseException;

public interface IDefendantManage {

	/**
	 * addDefendant
	 * @param d
	 * @throws BaseException
	 * 要求：
	 * 将d的Did编号等于Defendant数据库中数据个数加一。
	 * 存入数据库
	 */
	public void addDefendant(Defendant d) throws BaseException;
	/**
	 * loadAll
	 * @return
	 * @throws BaseException
	 * 要求：
	 * 通过查找Defendant表中Cid=c.cid的值然后输出
	 */
	public List<Defendant> loadDefendant(Case c)throws BaseException;
	/**
	 * deleteDefendant d
	 * @param d
	 * @throws BaseException
	 * 要求:
	 * 删除的同时，要遍历调用deleteRelation(Relation r) 把r.Aname等于d.dname的relation删掉
	 * 同时将数据库Defendant中该数据之后的数据的Did都减一
	 */
	public void deleteDefendant(Defendant d)throws BaseException;
	/**
	 * searchDefendant
	 * @param dname
	 * @throws BaseException
	 * 要求：
	 * 如果dname不存在,显示该被告编号不存在
	 * 存在的话,返回List<Defendant> 
	 */
	public List<Defendant> searchDefendant(String dname)throws BaseException;
	/**
	 * modifyDefendant
	 * @param d
	 * @throws BaseException
	 * 要求：
	 * 判断数据库中是否有d.did的对象在，如果不存在,显示数据不存在
	 * 如果存在,就把那条数据的属性用c的属性替代掉
	 */
	public void modifyDefendant(Defendant d)throws BaseException; 
}
