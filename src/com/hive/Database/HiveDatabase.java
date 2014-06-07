
package com.hive.Database;

import java.sql.*;
import org.apache.commons.codec.digest.DigestUtils;

public class HiveDatabase {

	private String DatabaseName, TableName, FieldName;
	private String DatabaseUrl = "jdbc:mysql://localhost/"; 	//默认为操作本机数据库
	private Connection Conn;
	
	public HiveDatabase() {
		setDatabaseName( "Hive" );
		setTableName( "UrlTable" );
		setFieldName( "Url" );
	}
	public HiveDatabase( String TableName ) {
		setDatabaseName( "Hive" );
		setTableName( TableName );
		setFieldName( "Url" );
	}
	public HiveDatabase( String DatabaseName, String TableName ) {
		setDatabaseName( DatabaseName );
		setTableName( TableName );
	}
	public HiveDatabase( String DatabaseName, String TableName, String FieldName ) {
		setDatabaseName( DatabaseName );
		setTableName( TableName );
		setFieldName( FieldName );
	}
	public boolean creatTable( String TableName ) {
		
		System.out.println("TableName = " + TableName);
		try {
			if ( isUniqueTableName( TableName ) ) {
				return false; 	//存在返回false；
			}
			else {
				String SQL = "CREATE TABLE " + TableName + " (Url VARCHAR(32) PRIMARY KEY)";
				Conn.prepareStatement(SQL).executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true; 	//建表成功返回true；
	}
	public boolean isUniqueTableName( String TableName ) {
		String SQL = "SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA='"+DatabaseName+"' AND TABLE_NAME='"+TableName+"'"; 	//检索是否存在改表
		ResultSet RS = null;
			try {
				System.out.println(SQL);
				System.out.println(Conn);
				RS = Conn.prepareStatement(SQL).executeQuery();
				if ( RS.next() ) {
					return true; 	//存在返回false；
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false;
	}
	
	public void setDatabaseName( String DatabaseName ) {
		this.DatabaseName = DatabaseName;
	}
	public void setTableName( String TableName ) {
		this.TableName = TableName;
	}
	public void setFieldName ( String FieldName ) {
		this.FieldName = FieldName;
	}
	public void setDatabaseUrl ( String DatabaseUrl ) {
		this.DatabaseUrl = DatabaseUrl;
	}
	public void ConnectionDB(String User, String Password) {  	//与数据库建立连接
		String Driver = "com.mysql.jdbc.Driver";
		DatabaseUrl = DatabaseUrl + DatabaseName;
		System.out.println("咋回事儿?");
		try {
			Class.forName(Driver);
			Conn = DriverManager.getConnection(DatabaseUrl, User, Password);
			System.out.println("Conn = " + Conn);
		} catch( SQLException e ) {
			//System.err.println("SQLExcepion " + e);
			e.printStackTrace();
		} catch( Exception e ) {
			e.printStackTrace();
		}
	}
	public void ConnectionDB( ) {
		ConnectionDB( "root", "linux" );
	}
	public void CutConnection() { 				//断开数据库连接
		try {
			if ( Conn == null ) {
				return;
			}
		} catch( Exception e ) {
			e.printStackTrace();
		} finally {
			try {
				Conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public boolean isUniqueURL( String Data ) { 		//判断数据库中是否包含Data信息
		
		String SQL = "SELECT * FROM " + TableName + " WHERE " + FieldName + " = '" + DigestUtils.md5Hex(Data) + "'";
		if ( FieldName == null ) {
			try {
				throw new Exception("Field 不能为 null, 你可以调用setFieldName(String)方法指定 或者在构造函数中增加参数");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ResultSet RS = null;
		try {
			RS = Conn.prepareStatement(SQL).executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if ( RS.next() ) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean insertUrl( String Url ) { 		//将Url插入数据库
		if ( isUniqueURL(Url) ) {
			return false;
		}
		String SQL = "INSERT INTO " + TableName + " (" + FieldName + ")values(?)";
		PreparedStatement PS = null;
		try {
			PS = Conn.prepareStatement(SQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			PS.setString(1, DigestUtils.md5Hex(Url));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int result = 0;
		try {
			result = PS.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if ( result > 0 )
			return true;
		return false;
	}
	 
	public boolean clearTable() { 							//删除默认表中的数据
		return clearTable( TableName );
	}
	public boolean clearTable( String TableName ) { 		//删除指定表中的数据
		try {
			String SQL = "TRUNCATE TABLE " + TableName;
			Conn.prepareStatement(SQL).executeUpdate();
			
			SQL = "SELECT * FROM " + TableName;
			ResultSet RS = Conn.prepareStatement(SQL).executeQuery();
			if ( RS.next() ) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	public boolean delTable() {
		return delTable( TableName );
	}
	public boolean delTable( String TableName ) {
		try {
			String SQL = "DROP TABLE " + TableName;
			Conn.prepareStatement(SQL).executeUpdate();
		} catch ( SQLException e ) {
			e.printStackTrace();
		}
		return (!this.isUniqueTableName(TableName));
	}
	
}
