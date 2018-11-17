package itf;

import java.util.List;

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
	 * 要求：返回所有被告的信息
	 * 要求用列表返回所有Defendant信息
	 */
	public List<Defendant> loadAll()throws BaseException;
	/**
	 * deleteDefendant d
	 * @param d
	 * @throws BaseException
	 * 要求:
	 * 同时将数据库Defendant中该数据之后的数据的Did都减一
	 */
	public void deleteDefendant(Defendant d)throws BaseException;
	/**
	 * searchDefendant
	 * @param dname
	 * @throws BaseException
	 * 要求：
	 * 如果did不存在,显示该被告编号不存在
	 * 存在的话,返回Defendant,返回该案件号下的嫌疑人  
	 */
	public Defendant searchDefendant(String did)throws BaseException;
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
