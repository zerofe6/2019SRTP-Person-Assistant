package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.*;

public class DatabaseUtil {

	private static Connection mConnection;//��̬˽�ܶ���

	/**
	 * ��ȡ���ݿ�����
	 * 
	 * @return Ψһ���ݿ�����
	 */
	private static Connection getConnection() {
		if (mConnection == null) {
			String url = "jdbc:mysql://localhost:3306/first_mysql_test?characterEncoding=utf8&serverTimezone=UTC"; // ���ݿ��Url
			try {
				Class.forName("com.mysql.jdbc.Driver"); // java���䣬�̶�д��
				System.out.println("���ݿ��������سɹ���");
				mConnection = DriverManager.getConnection(url, "root", "qwe123");
				LogUtil.log("�������ݿ�ɹ�");//��ӡ����
			} catch (ClassNotFoundException e) {
				System.out.println("�������ݿ�ʧ�ܣ�");
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
	 * ��ѯ����
	 * 
	 * @param querySql
	 *            ��ѯ����SQL���
	 * @return ��ѯ
	 * @throws SQLException
	 */
	public static ResultSet query(String querySql) throws SQLException {
		Statement stateMent = null;
		try {
			stateMent = (Statement) getConnection().createStatement();
			System.out.println("��ȡstatement����ɹ���");
		} catch (Exception e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			System.out.println("��ȡstatement����ʧ�ܣ�");
		}
		return stateMent.executeQuery(querySql);
	}

	/**
	 * ���롢���¡�ɾ������
	 * 
	 * @param insertSql
	 *            ���������SQL���
	 * @return
	 * @throws SQLException
	 */
	public static int update(String insertSql) throws SQLException {
		Statement stateMent = (Statement) getConnection().createStatement();
		return stateMent.executeUpdate(insertSql);
	}

	/**
	 * �ر����ݿ�����
	 */
	public static void closeConnection() {
		if (mConnection != null) {
			try {
				mConnection.close();
				mConnection = null;
			} catch (SQLException e) {
				LogUtil.log("���ݿ�ر��쳣��[" + e.getErrorCode() + "]" + e.getMessage());
			}
		}
	}
}
