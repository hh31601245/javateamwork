package util;

import java.sql.Connection;
import java.sql.SQLException;

public class DBUtil {
	private static final String jdbcUrl="jdbc:mysql://localhost:3306/knowledgemap?useUnicode=true&characterEncoding=utf-8&useSSL=true";
	//private static final String jdbcUrl="jdbc:jdts:sqlserver://localhost:1433/personplan"; sqlserver  .. 
	//private static final String jdbcUrl="jdbc:odbc:personplan"; jdbc-odbc
	//private static final String jdbcUrl="jdbc:oracle:thin:useername/password@//x.x.x.1:1521/personplan";
	private static final String dbUser="root";
	//private static final String dbUser="sa";
	private static final String dbPwd="123456";
	static{
		try {
			//Class.forName("net.sourceforge.jtds.jdbc.Driver");
			//Class.forName("sum.jdbc.odbc.jdbcOdbcDriver");
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection getConnection() throws java.sql.SQLException{
		return java.sql.DriverManager.getConnection(jdbcUrl, dbUser, dbPwd);
	}
	public static void main(String[] args)
	{
		try {
			Connection conn=DBUtil.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
