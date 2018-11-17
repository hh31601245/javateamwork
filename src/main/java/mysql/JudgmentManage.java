package mysql;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import itf.IJudgmentManage;
import model.Judgment;
import util.BusinessException;
import util.DBUtil;
import util.DbException;
public class JudgmentManage implements IJudgmentManage{

	@Override
	public void addJudgment(Judgment j){
		// TODO Auto-generated method stub
		Connection conn=null;
		try
		{
			conn=DBUtil.getConnection();
			String sql="select * from Judgment where title=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,j.getTitle());
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				try {
					throw new BusinessException("该审判书已经存在");
				} catch (BusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			sql="insert into Judgment(title,content) values(?,?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1,j.getTitle());
			pst.setString(2,j.getContent());
			pst.execute();
			pst.close();
			rs.close();
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
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
		}
		
	}

	@Override
	public void deleteJudgment(String title) {
		// TODO Auto-generated method stub
		Connection conn=null;
		try
		{
			conn=DBUtil.getConnection();
			conn.setAutoCommit(false);
			String sql="delete  from Cases where cid=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,title);
			pst.execute();
			String sql2="delete from Defendant where cid=?";
			java.sql.PreparedStatement pst2=conn.prepareStatement(sql2);
			pst2.setString(1,title);
			pst2.execute();
			String sql1="delete from Judgment where title=?";
			java.sql.PreparedStatement pst1=conn.prepareStatement(sql1);
			pst1.setString(1,title);
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

	@Override
	public void modifyJudgment(Judgment j) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> loadAll(){
		// TODO Auto-generated method stub
		List<String> result=new ArrayList<String>();
		String title=null;
		Connection conn=null;
		try
		{
			conn=DBUtil.getConnection();
			String sql="select * from Judgment";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next()==false)
			{
				result=null;
				return result;
			}
			sql="select * from Judgment";
			pst=conn.prepareStatement(sql);
			rs=pst.executeQuery();
			while(rs.next())
			{
				title=rs.getString(1);
				result.add(title);
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
	}

	@Override
	public Judgment searchJudgment(String title) {
		// TODO Auto-generated method stub
		Judgment result=new Judgment();
		Connection conn=null;
		try
		{
			conn=DBUtil.getConnection();
			String sql="select * from Judgment where title=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,title);
			java.sql.ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				result.setTitle(title);
				result.setContent(rs.getString(2));
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
	}

}
