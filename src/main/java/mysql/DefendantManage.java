package mysql;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import itf.IDefendantManage;
import model.Case;
import model.Defendant;
import util.BaseException;
import util.DBUtil;
import util.DbException;

import java.text.NumberFormat;
public class DefendantManage implements IDefendantManage{

	public static void main(String[] args) throws BaseException {
		// TODO Auto-generated method stub
		//Case c = new Case(); //填入一些值
		//Defendant d = new Defendant("003", "A", "男", 0, null, null, null, null, null, null, 0, null); 
		//DefendantManage test = new DefendantManage();
		//test.addDefendant(d);
		//test.loadDefendant(c);
		//test.searchDefendant("002");
		//test.modifyDefendant(d);  
		//test.loadAll();
		//test.deleteDefendant(d);
		//begin.Util.defendantmanage.StatisticsDefendant("浙0902刑初00262号","Sex");
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
		Connection conn = null;
		String result1 = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select Did from defendant ";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			String did = null;
			while(rs.next()) {
			    did = rs.getString(1);
			}
			if(did !=null) {
				String sql1 = "insert into defendant(Did,Dname,Sex,Age,DateOfBrith,Education,Crime,PenaltyType,Sentence,PropertyPenaltyType,PropertyPenaltyAmount,Cid) values(?,?,?,?,?,?,?,?,?,?,?,?)";
				java.sql.PreparedStatement pst1 = conn.prepareStatement(sql1);
				int s=Integer.parseInt(did);  //String->int
				s=s+1; 
		        s=s==1000?1:s;  
		        result1 = s<999?(s<10?("00"+s):(s<100?"0"+s:""+s)):"001";  //字符串001自增
				pst1.setString(1, result1);
				pst1.setString(2, d.getDname());
				pst1.setString(3, d.getSex());
				pst1.setInt(4, d.getAge());
				pst1.setString(5, d.getDateOfBrith());
				pst1.setString(6, d.getEducation());
				pst1.setString(7, d.getCrime());
				pst1.setString(8, d.getPenaltyType());
				pst1.setString(9,d.getSentence());
				pst1.setString(10, d.getPropertyPenaltyType());
				pst1.setDouble(11, d.getPropertyPenaltyAmount());
				pst1.setString(12, d.getCid());
				pst1.execute();
				
			}else {
				String sql2 = "insert into defendant(Did,Dname,Sex,Age,DateOfBrith,Education,Crime,PenaltyType,Sentence,PropertyPenaltyType,PropertyPenaltyAmount,Cid) values(?,?,?,?,?,?,?,?,?,?,?,?)";
				java.sql.PreparedStatement pst2 = conn.prepareStatement(sql2);
				pst2.setString(1, "001");
				pst2.setString(2, d.getDname());
				pst2.setString(3, d.getSex());
				pst2.setInt(4, d.getAge());
				pst2.setString(5, d.getDateOfBrith());
				pst2.setString(6, d.getEducation());
				pst2.setString(7, d.getCrime());
				pst2.setString(8, d.getPenaltyType());
				pst2.setString(9,d.getSentence());
				pst2.setString(10, d.getPropertyPenaltyType());
				pst2.setDouble(11, d.getPropertyPenaltyAmount());
				pst2.setString(12, d.getCid());
				pst2.execute();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * loadAll
	 * @return
	 * @throws BaseException
	 *  @throws BaseException
	 * 要求  ：返回Defendant表里Cid=c.cid的所有值。
	 */
	public List<Defendant> loadDefendant(Case c)throws BaseException{
		// TODO Auto-generated method stub
		List<Defendant> result =new ArrayList<>();
		Connection conn=null;
		Defendant d = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from defendant where Cid = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, c.getCid());
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				d=new Defendant();
				d.setDid(rs.getString(1));
				d.setDname(rs.getString(2));
				d.setSex(rs.getString(3));
				d.setAge(rs.getInt(4));
				d.setDateOfBrith(rs.getString(5));
				d.setEducation(rs.getString(6));
				d.setCrime(rs.getString(7));
				d.setPenaltyType(rs.getString(8));
				d.setSentence(rs.getString(9));
				d.setPropertyPenaltyType(rs.getString(10));
				d.setPropertyPenaltyAmount(rs.getDouble(11));
				d.setCid(rs.getString(12));
				result.add(d);
			}
			
		}catch(SQLException e) {
				e.printStackTrace();
			}
		
		return result;
	}
	/**
	 * deleteDefendant d
	 * @param d
	 * @throws BaseException
	 * 要求:
	 * 根据d.did来查找要删除的数据
	 * 同时将数据库Defendant中该数据之后的数据的Did都减一，来使id连续
	 */
	@Override
	public void deleteDefendant(Defendant d) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "delete from defendant where Did = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, d.getDid());
			pst.execute();
			String sql1 = "update defendant set Did = Did-1 where Did > ?";
			java.sql.PreparedStatement pst1 = conn.prepareStatement(sql1);
			pst1.setString(1,d.getDid());
			pst1	.execute();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * searchDefendant
	 * @param dname
	 * @throws BaseException
	 * 要求：
	 * 如果did不存在,显示该被告编号不存在
	 * 存在的话,返回Defendant,返回该案件号下的嫌疑人 
	 */
	@Override
	public Defendant searchDefendant(String did) throws BaseException {
		// TODO Auto-generated method stub
		Defendant d = new Defendant();
		Connection conn=null;
		int count = 0;
		try {
			conn = DBUtil.getConnection();
			String sql = "select count(*) from defendant where Did = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, did);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				count = rs.getInt(1);
				
			}
			if(count == 0) {
				System.out.println("该被告编号不存在");
			}else {
				String sql1 = "select * from defendant";
				java.sql.PreparedStatement pst1 = conn.prepareStatement(sql1);
				ResultSet rs1 = pst1.executeQuery();
				while(rs.next()) {
					d.setDid(rs1.getString(1));
					d.setDname(rs1.getString(2));
					d.setSex(rs1.getString(3));
					d.setAge(rs1.getInt(4));
					d.setDateOfBrith(rs1.getString(5));
					d.setEducation(rs1.getString(6));
					d.setCrime(rs1.getString(7));
					d.setPenaltyType(rs1.getString(8));
					d.setSentence(rs1.getString(9));
					d.setPropertyPenaltyType(rs1.getString(10));
					d.setPropertyPenaltyAmount(rs1.getDouble(11));
					d.setCid(rs1.getString(12));
					
				}
				
			}
			
			
		  }catch(SQLException e) {
			e.printStackTrace();
		}
		
		return d;
	}
	/**
	 * modifyDefendant
	 * @param d
	 * @throws BaseException
	 * 要求：
	 * 判断数据库中是否有d.did的对象在，如果不存在,显示数据不存在
	 * 如果存在,就把那条数据的属性用d的属性替代掉
	 */
	@Override
	public void modifyDefendant(Defendant d) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn = null;
		int count = 0;
		try {
			conn = DBUtil.getConnection();
			String  sql = "select count(*) from defendant where Did = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, d.getDid());
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				count = rs.getInt(1);
				
			}
			if(count == 0) {
				System.out.println("该数据不存在");
			}else {
				String sql1 = "update defendant set Did = ?,Dname = ?,Sex = ?,Age = ?,DateOfBrith = ?,Education = ?,Crime = ?,PenaltyType = ?,Sentence = ?,PropertyPenaltyType = ?,PropertyPenaltyAmount = ?,Cid = ?  where Did = ?";
				java.sql.PreparedStatement pst1 = conn.prepareStatement(sql1);
				pst1.setString(1, d.getDid());
				pst1.setString(2, d.getDname());
				pst1.setString(3, d.getSex());
				pst1.setInt(4, d.getAge());
				pst1.setString(5, d.getDateOfBrith());
				pst1.setString(6, d.getEducation());
				pst1.setString(7, d.getCrime());
				pst1.setString(8, d.getPenaltyType());
				pst1.setString(9, d.getSentence());
				pst1.setString(10, d.getPropertyPenaltyType());
				pst1.setDouble(11, d.getPropertyPenaltyAmount());
				pst1.setString(12, d.getCid());
				pst1.setString(13, d.getDid());
				pst1.execute();
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public List<Defendant> loadAll() throws BaseException {
		// TODO Auto-generated method stub
		List<Defendant> result = new ArrayList<>();
		Defendant d = new Defendant();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from defendant";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				d.setDid(rs.getString(1));
				d.setDname(rs.getString(2));
				d.setSex(rs.getString(3));
				d.setAge(rs.getInt(4));
				d.setDateOfBrith(rs.getString(5));
				d.setEducation(rs.getString(6));
				d.setCrime(rs.getString(7));
				d.setPenaltyType(rs.getString(8));
				d.setSentence(rs.getString(9));
				d.setPropertyPenaltyType(rs.getString(10));
				d.setPropertyPenaltyAmount(rs.getDouble(11));
				d.setCid(rs.getString(12));
				result.add(d);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
		
	}
	public Map<String,Integer> StatisticsDefendantSex(Case c)
	{
		Map<String,Integer> result=new HashMap<String,Integer>();
		Connection conn=null;
		try
		{
			conn=DBUtil.getConnection();
			String sql="select Sex,count(*) from defendant where cid=? group by Sex";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			//pst.setString(1,condidant);			
			pst.setString(1,c.getCid());
			//pst.setString(2,condidant);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				
				String sex=rs.getString(1);
				Integer count=rs.getInt(2);
				System.out.println(sex+" "+count);
				result.put(sex,count);
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
		for (String key : result.keySet()) {  //通过foreach方法来遍历

		       System.out.println("key= "+ key + " and value= " + result.get(key));
		      }
		return result;
	}
	public Map<String,Integer> StatisticsDefendantEducation(Case c)
	{
		Map<String,Integer> result=new HashMap<String,Integer>();
		Connection conn=null;
		try
		{
			conn=DBUtil.getConnection();
			String sql="select Education,count(*) from defendant where cid=? group by Education";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			//pst.setString(1,condidant);			
			pst.setString(1,c.getCid());
			//pst.setString(2,condidant);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				
				String education=rs.getString(1);
				Integer count=rs.getInt(2);
				System.out.println(education+" "+count);
				result.put(education,count);
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
		for (String key : result.keySet()) {  //通过foreach方法来遍历

		       System.out.println("key= "+ key + " and value= " + result.get(key));
		      }
		return result;
	}
	public Map<String,Integer> StatisticsDefendantPenaltyType(Case c)
	{
		Map<String,Integer> result=new HashMap<String,Integer>();
		Connection conn=null;
		try
		{
			conn=DBUtil.getConnection();
			String sql="select PenaltyType,count(*) from defendant where cid=? group by PenaltyType";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			//pst.setString(1,condidant);			
			pst.setString(1,c.getCid());
			//pst.setString(2,condidant);
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				
				String education=rs.getString(1);
				Integer count=rs.getInt(2);
				System.out.println(education+" "+count);
				result.put(education,count);
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
		for (String key : result.keySet()) {  //通过foreach方法来遍历

		       System.out.println("key= "+ key + " and value= " + result.get(key));
		      }
		return result;
	}
	public List<Defendant> vagueSearch(String name,String Cid)throws BaseException{
		  Connection conn = null;
		  List<Defendant> result = new ArrayList<>();
		  try {
			  conn = DBUtil.getConnection();
			  String sql = "select * from defendant where Dname like ? and Cid=?";  // %写到？里面
			  java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			  pst.setString(1, "%"+name+"%");  //模糊查询注意点
			  pst.setString(2,Cid);
			  ResultSet rs = pst.executeQuery();
			  while(rs.next()) {
				    Defendant d = new Defendant();  //输出多个Defendant
				    d.setDid(rs.getString(1));
					d.setDname(rs.getString(2));
					d.setSex(rs.getString(3));
					d.setAge(rs.getInt(4));
					d.setDateOfBrith(rs.getString(5));
					d.setEducation(rs.getString(6));
					d.setCrime(rs.getString(7));
					d.setPenaltyType(rs.getString(8));
					d.setSentence(rs.getString(9));
					d.setPropertyPenaltyType(rs.getString(10));
					d.setPropertyPenaltyAmount(rs.getDouble(11));
					d.setCid(rs.getString(12));
					result.add(d);
			  }
		  }catch(SQLException e) {
			  e.printStackTrace();
		  }
		  for(Defendant defendant:result) {
			  System.out.println("Did is: "+defendant.getDid()+" "+"Dname is: "+defendant.getDname()+" "+"Sex is: "+defendant.getSex()+" "+"Age is: "+defendant.getAge()+" "+"DateOfBrith is: "+defendant.getDateOfBrith()+" "+"Education is: "+defendant.getEducation()+" "+"Crime is: "+defendant.getCrime()+" "+"PenaltyType is: "+defendant.getPenaltyType()+" "+"Sentence is: "+defendant.getSentence()+" "+"PropertyPenaltyType is: "+defendant.getPropertyPenaltyType()+" "+"PropertyPenaltyAmount is: "+defendant.getPropertyPenaltyAmount()+" "+"Cid is: "+defendant.getCid());
		  }
		return result;
	}

}
