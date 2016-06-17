package com.swu.wechat.tool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;


/**
 * 
 *  数据库访问帮助类
 * @author Administrator
 * 
 */
public class DbHelp {
	private static final String Driver = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/wechat";
	private static final String USER = "root";
	private static final String PASS = "123456";

	/**
	 * 
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		Connection cn = null;
		try {
			Class.forName(Driver);
			cn = DriverManager.getConnection(URL, USER, PASS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cn;
	}

	/**
	 * 
	 * 
	 * @param sql
	 *            insert update delete
	 * @param paras
	 * @return 
	 */
	public static int executeUpdata(String sql, Object... arr) {
		int a = 0;
		Connection cn = null;
		PreparedStatement pstm = null;
		cn = getConnection();
		try {
			pstm = cn.prepareStatement(sql);
			for (int i = 0; i < arr.length; i++) {
				pstm.setObject(i + 1, arr[i]);
			}
			a = pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstm.close();
				cn.close();
			} catch (SQLException ex) {

			}
		}
		return a;

	}

	/**
	 * 
	 * 
	 * @param sql
	 *            select
	 * @param paras
	 * @return
	 *        rowset
	 */
	public static CachedRowSet executeQuery(String sql, Object... arr) {
		ResultSet rs = null;
		Connection cn = null;
		PreparedStatement pstm = null;
		CachedRowSet  cr=null;
		cn = getConnection();
		try {
			pstm = cn.prepareStatement(sql);
			for (int i = 0; i < arr.length; i++) {
				pstm.setObject(i + 1, arr[i]);
			}
			cr = new com.sun.rowset.CachedRowSetImpl();
			rs = pstm.executeQuery();
			cr.populate(rs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				pstm.close();
				cn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return cr;

	}

}
