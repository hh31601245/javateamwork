package itf;

import java.util.List;

import model.Relation;
import util.BaseException;

public interface IRelationManage {
	/**
	 * addRelation
	 * @param r
	 * @throws BaseException
	 * 要求：
	 * 将r的Rid编号等于Relation数据库中数据个数加一。
	 * 存入数据库
	 */
	public void addRelation(Relation r) throws BaseException;
	/**
	 * loadAll
	 * @return
	 * @throws BaseException
	 * 要求：返回所有关系的信息
	 * 要求用列表返回所有Relation信息
	 */
	public List<Relation> loadAll()throws BaseException;
	/**
	 * deleteRelation
	 * @param r
	 * @throws BaseException
	 * 要求:
	 * 删除的同时将数据库Defendant中该数据之后的数据的Rid都减一
	 */
	public void deleteRelation(Relation d)throws BaseException;
	/**
	 * searchRelation
	 * @param rid
	 * @throws BaseException
	 * 要求：
	 * 遍历Relation，输出所有返回Aname或者Bname等于name的数据
	 * 不存在的话，返回提示信息
	 */
	public List<Relation> searchRelation(String name)throws BaseException;
	/**
	 * modifyRelation
	 * @param r
	 * @throws BaseException
	 * 要求：
	 * 判断数据库中是否有存在相同的关系（即r.Aname和r.Bname都相同的对象在），如果不存在,显示数据不存在
	 * 如果存在,就把那条数据的属性用r的属性替代掉
	 */
	public void modifyRelation(Relation r)throws BaseException; 
}
