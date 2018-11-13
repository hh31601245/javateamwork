package mysql;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.util.BusinessException;
import cn.edu.zucc.personplan.util.DBUtil2;
import util.DBUtil;
import itf.IRelationManage;
import model.Case;
import model.Defendant;
import model.Relation;
import util.BaseException;
import util.DbException;
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
		Connection conn=null;
		
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
		List<Relation> result=new ArrayList<Relation>();
		Connection conn=null;
		try
		{
			conn=DBUtil.getConnection();
			String sql="select * from relation";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				Relation r=new Relation();
				r.setRid(rs.getString(1));
				r.setAname(rs.getString(2));
				r.setBname(rs.getString(3));
				r.setRelate(rs.getString(4));
				result.add(r);
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
			throw new DbException(e);
		}finally
		{
			if(conn!=null)
			{
				try
				{
					conn.close();
				}catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	/**
	 * deleteRelation
	 * @param r
	 * @throws BaseException
	 * 要求:
	 * 删除的同时将数据库Defendant中该数据之后的数据的Rid都减一
	 */
	@Override
	public void deleteRelation(Relation r) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn=null;
		try
		{
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql="delete from relation where Rid=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,r.getRid());
			pst.execute();
			String sql1="update relation set Rid=Rid-1 where Rid>?";
			java.sql.PreparedStatement pst1=conn.prepareStatement(sql1);
			pst1.setString(1,r.getRid());
			pst1.execute();
		}catch(Exception e)
		{
			try
			{
				conn.rollback();
			}catch(SQLException e1)
			{
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally
		{
			try
			{
			conn.commit();
			
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
			if(conn!=null)
			{
				try
				{
					conn.close();
				}catch(SQLException e1)
				{
					e1.printStackTrace();
					
				}
			}
		}
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
