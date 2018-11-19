package mysql;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//import cn.edu.zucc.personplan.util.DbException;
import itf.ICaseManage;
import model.*;
import util.BaseException;
import util.DBUtil;
import util.DbException;
import mysql.DefendantManage;
public class CaseManage implements ICaseManage{

	//private static final String CourtName = null;
	public static void main(String[] args) throws BaseException { //测试用
		// TODO Auto-generated method stub
		Case c = new Case(); //填入一些值
		Defendant d=new Defendant("1", "a", null, 0, null, null, null, null, null, null, 0,null); 
		CaseManage test = new CaseManage();
		//test.addCase(c);    // 然后像这样的感觉测试知道能用
		//test.loadAll();
		//test.searchCase("003");
		//test.modifyCase(c);
		
	}
	/**
	 * addCase
	 * @param c
	 * @return
	 * @throws BaseException
	 * 先判断c.cid在数据库Case中是否存在，存在的话，就输出该案件已存在的信息
	 * 不存在的话，就存入数据库
	 */
	@Override
	public void addCase(Case c) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn = null;
		String a = c.getCid();
		//System.out.println(a);
		try {
			conn = DBUtil.getConnection();
		    
			String sql = "select count(*) from cases where Cid =? ";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1,a);
			ResultSet rs = pst.executeQuery();
			int count = 0;
			while(rs.next()) {
				count = rs.getInt(1);
			}
			//System.out.println(count);
			if(count == 0) {
					String sql1 = "insert into cases(Cid,CourtName,NumberPeople,MinAge,FirstDefendant,DrugTypeAndNumberOrUnit,DrugPrice,Cyear) values(?,?,?,?,?,?,?,?)";
					java.sql.PreparedStatement pst1 = conn.prepareStatement(sql1);
					pst1.setString(1, c.getCid());
					pst1.setString(2, c.getCourtName());
					pst1.setInt(3, c.getNumberPeople());
					pst1.setInt(4, c.getMinAge());
					pst1.setString(5, c.getFirstDefendant());
					pst1.setString(6, c.getDrugTypeAndNumberOrUnit());
					pst1.setString(7, c.getDrugPrice());
					pst1.setString(8, c.getCyear());
					pst1.execute();		
					
			}else {
				String cid = c.getCid();
				String courtname = c.getCourtName();
				int numberpeople = c.getNumberPeople();
				int minage = c.getMinAge();
				String firstdefendant = c.getFirstDefendant();
				String drugtypeandnumberorunit = c.getDrugTypeAndNumberOrUnit();
				String drugprice = c.getDrugPrice();
				String cyear = c.getCyear();
				System.out.println("Cid:"+cid+" "+"DrugPrice:"+courtname+" "+"NumberPeople:"+numberpeople+" "+"MinAge:"+minage+" "+"FirstDefendant:"+firstdefendant+" "+"DrugTypeAndNumberOrUnit:"+drugtypeandnumberorunit+" "+"DrugPrice:"+drugprice+" "+"Cyear:"+cyear);
				
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
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
		List<Case> result = new ArrayList<>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select Cid,CourtName,NumberPeople,MinAge,FirstDefendant,DrugTypeAndNumberOrUnit,DrugPrice,Cyear from cases ";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				Case s = new Case();
				s.setCid(rs.getString(1));
				s.setCourtName(rs.getString(2));
				s.setNumberPeople(rs.getInt(3));
				s.setMinAge(rs.getInt(4));
				s.setFirstDefendant(rs.getString(5));
				s.setDrugTypeAndNumberOrUnit(rs.getString(6));
				s.setDrugPrice(rs.getString(7));
				s.setCyear(rs.getString(8));
				result.add(s);
				
				
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * deleteCase
	 * @param c
	 * @throws BaseException
	 * 要求:
	 * ，用searchDefendant(Aname)的方法找到list的列表然后用deleteDefendant()的方法删掉他们
	 * 然后再删除数据删除数据
	 */
	@Override
	public void deleteCase(Case c) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn = null;
		String cid = c.getCid();
		try {
			conn = DBUtil.getConnection();
			
			String sql = "delete from defendant where Cid = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, c.getCid());
			pst.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		DefendantManage d = new DefendantManage();
		Defendant list1 = d.searchDefendant(c.getCid());
		d.deleteDefendant(list1);
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
		//List<Case> result = new ArrayList<>();
		Connection conn = null;
		Case a = new Case();
		try {
		conn = DBUtil.getConnection();
		String sql = "select count(*) from cases where Cid =?";
		java.sql.PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, cid);
		ResultSet rs = pst.executeQuery();	
		int count = 0;
		while(rs.next()) {
			count = rs.getInt(1);
		}
			if(count == 0) {
				System.out.println("该案件编号不存在");
			}
			else {
				String sql1 = "select * from cases where Cid =? ";
				java.sql.PreparedStatement pst1 = conn.prepareStatement(sql1);
				pst1.setString(1, cid);
				ResultSet rs1 = pst1.executeQuery();
				while(rs1.next()) {
				a.setCid(cid);
				a.setCourtName(rs1.getString(2));
				a.setNumberPeople(rs1.getInt(3));
				a.setMinAge(rs1.getInt(4));
				a.setFirstDefendant(rs1.getString(5));
				a.setDrugTypeAndNumberOrUnit(rs1.getString(6));
				a.setDrugPrice(rs1.getString(7));
				a.setCyear(rs1.getString(8));
				}
				
			}
				
	   }catch(SQLException e) {
		        e.printStackTrace();
	  }
		return a;
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
		Connection conn = null;
		int count = 0;
		try {
			conn = DBUtil.getConnection();
			String  sql = "select count(*) from cases where Cid = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, c.getCid());
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				count = rs.getInt(1);
				
			}
			if(count == 0) {
				System.out.println("该数据不存在");
			}else {
				String sql1 = "update cases set Cid = ?,CourtName = ?,NumberPeople = ?,MinAge = ?,FirstDefendant = ?,DrugTypeAndNumberOrUnit = ?,DrugPrice = ?,Cyear = ? where Cid = ?";
				java.sql.PreparedStatement pst1 = conn.prepareStatement(sql1);
				pst1.setString(1, c.getCid());
				pst1.setString(2, c.getCourtName());
				pst1.setInt(3, c.getNumberPeople());
				pst1.setInt(4, c.getMinAge());
				pst1.setString(5, c.getFirstDefendant());
				pst1.setString(6, c.getDrugTypeAndNumberOrUnit());
				pst1.setString(7, c.getDrugPrice());
				pst1.setString(8, c.getCyear());
				pst1.setString(9, c.getCid());
				pst1.execute();
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		

	}
	/*public Map<String,Integer> StatisticsCase(Case c)
	{
		Map<String,Integer> result=null;
		Connection conn=null;
		try
		{
			conn=DBUtil.getConnection();
			String sql="select ?,count(*) from Defendant where cid=? group by ?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);		
			pst.setString(1,c.getCid());
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				result.put(rs.getString(1),rs.getInt(2));
			}
		}catch(SQLException e)
		{
			e.printStackTrace();
			try {
				throw new DbException(e);
			} catch (DbException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
	}*/
}
