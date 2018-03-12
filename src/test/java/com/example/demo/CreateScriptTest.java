package com.example.demo;

import com.sysware.entity.ImportVo;
import com.sysware.service.FileReader;

public class CreateScriptTest {
	public static void main(String[] args) {
		String serviceIp = "zengq1";
//		String filePath = "D:/fileRead/base";
		String filePath = "D:/fileRead/p2mDefault";
//		String filePath = "D:/fileRead/baseTest";
		String username = "pm20180201";
		String password = "pm20180201";
		String serviceName = "sysware";
		ImportVo vo = new ImportVo();
		vo.setIp(serviceIp);
		vo.setPath(filePath);
		vo.setPassword(password);
		vo.setUsername(username);
		vo.setServiceName(serviceName);
		FileReader.execute(vo);
	}
}
