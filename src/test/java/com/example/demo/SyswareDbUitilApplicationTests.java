package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sysware.entity.ImportVo;
import com.sysware.service.FileReader;

public class SyswareDbUitilApplicationTests {

	@Test
	public void contextLoads() {
		String path = "D:/fileRead";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		String targetIp = "zengq1";// 主机名或者ip
		Integer port = 1521;// Oracle端口
		String serviceName = "sysware";// 默认连接名
		String username = "p2m_20170730";// 用户名
		String password = "p2m_20170730";// 密码
		StringBuilder jdbcUrl = new StringBuilder(
				"jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS_LIST =(ADDRESS = (PROTOCOL = TCP)(HOST = ");
		jdbcUrl.append(targetIp).append(")(PORT = ").append(port).append(")))(CONNECT_DATA =(SERVICE_NAME = ")
				.append(serviceName).append(")))");
		String filePath = "D:/fileRead";
		String sql = "INSERT INTO file_import_test t (t.id,t.name,t.age) VALUES ('10','张三',20)";
		
		
		try {
			Connection connection = DriverManager.getConnection(jdbcUrl.toString(), username, password);
			Statement statement = connection.createStatement();
			boolean execute = statement.execute(sql);
			System.out.println(execute);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test
	public void testCreate(){
		String  serviceIp="zengq1"               ;
		String  filePath="D:/fileRead/create"    ;
		String  username="pm20180201"            ;
		String  password="pm20180201"            ;
		String  serviceName="sysware"            ;
		ImportVo vo = new ImportVo();
		vo.setIp(serviceIp);
		vo.setPath(filePath);
		vo.setPassword(password);
		vo.setUsername(username);
		vo.setServiceName(serviceName);
		FileReader.execute(vo);
	}
	
}
