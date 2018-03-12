package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBCreateTest {
	public static void main(String[] args) {
		 String sql = "CREATE or replace and compile java source named mytimestamp as \n  "+
		"	public class MyTimestamp                                           \n          "+
		"	{                                                                    \n        "+
		"	  public static String getTimestamp()                                 \n       "+
		"	   {                                                                  \n       "+
		"	    return String.valueOf(System.currentTimeMillis());                 \n      "+
		"	   }                                                                    \n     "+
		"	}                                                                          ";
		 
		 
		 executeSql(sql);                                                                            
//		 String javasource = "CREATE or replace and compile java source named mytimestamp  ";
//		 executeSql(javasource);                                                                            
	}
	
	/**
	 * 测试sql执行
	 * @param sql
	 */
	public static void executeSql(String sql){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		String targetIp = "zengq1";// 主机名或者ip
		Integer port = 1521;// Oracle端口
		String serviceName = "sysware";// 默认连接名
		String username = "pm20180201";// 用户名
		String password = "pm20180201";// 密码
		StringBuilder jdbcUrl = new StringBuilder(
				"jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS_LIST =(ADDRESS = (PROTOCOL = TCP)(HOST = ");
		jdbcUrl.append(targetIp).append(")(PORT = ").append(port).append(")))(CONNECT_DATA =(SERVICE_NAME = ")
				.append(serviceName).append(")))");
		
		try {
			Connection connection = DriverManager.getConnection(jdbcUrl.toString(), username, password);
			Statement statement = connection.createStatement();
			boolean execute = statement.execute(sql);
			System.out.println(execute);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
