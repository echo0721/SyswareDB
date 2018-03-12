package com.sysware;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import com.sysware.entity.ImportVo;
import com.sysware.service.FileReader;

@SpringBootApplication
public class SyswareDbUitilApplication {
	private static final Log logger = LogFactory.getLog(SyswareDbUitilApplication.class);
	public static void main(String[] args) {
		/**
		 * 如果传了参数，当做普通工具程序。
		 * 如果没有参数，则启动web程序 
		 * 参数顺序为 
		 * args[0] serviceIp 必填
		 * args[1] path      必填
		 * args[2] username  必填
		 * args[3] password  必填
		 * args[4] serviceName
		 */
		if(args !=null && args.length >=4){
			ImportVo vo = new ImportVo();
			String serviceIp      =  args[0];
			String path           =  args[1];
			String username       =  args[2];
			String password       =  args[3];
			if(args.length>=5){
				if(args[4]!=null &&args[4]!=""){
					vo.setServiceName(args[4]);
				}
			}
			vo.setIp(serviceIp);
			vo.setPath(path);
			vo.setPassword(password);
			vo.setUsername(username);
			long begin = System.currentTimeMillis();
			
			FileReader.execute(vo);
			
			long end = System.currentTimeMillis();
			
			logger.info("总耗时："+(end - begin)/1000 +"秒");
			 
		}else{
			SpringApplication.run(SyswareDbUitilApplication.class, args);
		}
		
	}
}
