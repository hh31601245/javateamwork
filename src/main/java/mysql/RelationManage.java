package mysql;
import java.util.List;

import itf.IRelationManage;
import model.Case;
import model.Defendant;
import model.Relation;
import util.BaseException;
public class RelationManage implements IRelationManage{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Case c=new Case("001", "1", 0, 0, null, null, null, null, null, 0, null, null); //填入一些值
		Defendant d=new Defendant("1", "a", null, 0, null, null, null, null, null, null, 0,null); //
		Relation r=new Relation("1", "a", "001", "1");  
		RelationManage test=new RelationManage();
		//test.addRelation(r);  然后像这样的感觉测试知道能用
	}
	/**
	 * addRelation
	 * @param r
	 * @throws BaseException
	 * 要求：
	 * 将r的Rid编号等于Relation数据库中数据个数加一。
	 * 存入数据库
	 */
	@Override
	public void addRelation(Relation r) throws BaseException {
		// TODO Auto-generated method stub
		
	}
	/**
	 * loadAll
	 * @return
	 * @throws BaseException
	 * 要求：返回所有关系的信息
	 * 要求用列表返回所有Relation信息
	 */
	@Override
	public List<Relation> loadAll() throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * deleteRelation
	 * @param r
	 * @throws BaseException
	 * 要求:
	 * 删除的同时将数据库Defendant中该数据之后的数据的Rid都减一
	 */
	@Override
	public void deleteRelation(Relation d) throws BaseException {
		// TODO Auto-generated method stub
		
	}
	/**
	 * searchRelation
	 * @param rid
	 * @throws BaseException
	 * 要求：
	 * 遍历Relation，输出所有Aname或者Bname等于name的数据
	 * 不存在的话，返回提示信息
	 */
	@Override
	public List<Relation> searchRelation(String name) throws BaseException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * modifyRelation
	 * @param r
	 * @throws BaseException
	 * 要求：
	 * 判断数据库中是否有存在相同的关系（即r.Aname和r.Bname都相同的对象在），如果不存在,显示数据不存在
	 * 如果存在,就把那条数据的属性用r的属性替代掉
	 */
	@Override
	public void modifyRelation(Relation r) throws BaseException {
		// TODO Auto-generated method stub
		
	}

}
