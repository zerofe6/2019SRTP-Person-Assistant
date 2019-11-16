package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.*;

public class DatabaseUtil {

	private static Connection mConnection;//静态私密对象

	/**
	 * 获取数据库连接
	 * 
	 * @return 唯一数据库连接
	 */
	private static Connection getConnection() {
		if (mConnection == null) {
			String url = "jdbc:mysql://localhost:3306/first_mysql_test?characterEncoding=utf8&serverTimezone=UTC"; // 数据库的Url
			try {
				Class.forName("com.mysql.jdbc.Driver"); // java反射，固定写法
				System.out.println("数据库驱动加载成功！");
				mConnection = DriverManager.getConnection(url, "root", "qwe123");
				LogUtil.log("连接数据库成功");//打印出来
			} catch (ClassNotFoundException e) {
				System.out.println("连接数据库失败！");
				e.printStackTrace();
			} catch (SQLException e) {
				System.out.println("SQLException: " + e.getMessage());
				System.out.println("SQLState: " + e.getSQLState());
				System.out.println("VendorError: " + e.getErrorCode());
			}
		}
		return mConnection;
	}

	/**
	 * 查询操作
	 * 
	 * @param querySql
	 *            查询操作SQL语句
	 * @return 查询
	 * @throws SQLException
	 */
	public static ResultSet query(String querySql) throws SQLException {
		Statement stateMent = null;
		try {
			stateMent = (Statement) getConnection().createStatement();
			System.out.println("获取statement对象成功！");
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			System.out.println("获取statement对象失败！");
		}
		return stateMent.executeQuery(querySql);
	}

	/**
	 * 插入、更新、删除操作
	 * 
	 * @param insertSql
	 *            插入操作的SQL语句
	 * @return
	 * @throws SQLException
	 */
	public static int update(String insertSql) throws SQLException {
		Statement stateMent = (Statement) getConnection().createStatement();
		return stateMent.executeUpdate(insertSql);
	}

	/**
	 * 关闭数据库连接
	 */
	public static void closeConnection() {
		if (mConnection != null) {
			try {
				mConnection.close();
				mConnection = null;
			} catch (SQLException e) {
				LogUtil.log("数据库关闭异常：[" + e.getErrorCode() + "]" + e.getMessage());
			}
		}
	}
}
