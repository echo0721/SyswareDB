package com.sysware.service;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.util.CollectionUtils;

import com.sysware.entity.ImportVo;

/**
 * sql脚本执行 编码为GB2312(ANSI)
 * 
 * @author zengq
 *
 */
public class FileReader {
	static Logger logger = LoggerFactory.getLogger(FileReader.class);

	public static List<Map<String, String>> execute(ImportVo vo) {
		StringBuilder jdbcUrl = new StringBuilder(
				"jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS_LIST =(ADDRESS = (PROTOCOL = TCP)(HOST = ");
		jdbcUrl.append(vo.getIp()).append(")(PORT = ").append(vo.getPort()).append(")))(CONNECT_DATA =(SERVICE_NAME = ")
				.append(vo.getServiceName()).append(")))");
		return execute(vo.getPath(), jdbcUrl.toString(), vo.getUsername(), vo.getPassword());
	}

	public static List<Map<String, String>> execute(String filePath, String jdbcUrl, String username, String password) {
		logger.info("脚本执行开始！");

		List<Map<String, String>> errors = new ArrayList<>();
		List<String> sqlFiles = getSqlFiles(filePath);
		if (!CollectionUtils.isEmpty(sqlFiles)) {

			for (String sqlFile : sqlFiles) {
				Map<String, String> errorInfo = new HashMap<>();
				FileSystemResource rs = new FileSystemResource(sqlFile);
				try {
					Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
					EncodedResource resource = new EncodedResource(rs, "GB2312");
					ScriptUtils.executeSqlScript(connection, resource);
					errorInfo.put("fileName", sqlFile);
					errorInfo.put("msg", "执行成功！");
					logger.info("fileName", sqlFile);
					logger.info("msg", "执行成功！");
					errors.add(errorInfo);
				} catch (SQLException e) {
					logger.info(e.getMessage());
					logger.info("数据库链接失败！");
				} catch (ScriptException e) {
					// e.printStackTrace();
					Throwable mostSpecificCause = e.getMostSpecificCause();
					StringBuilder message = new StringBuilder("sql【");
					message.append(sqlFile).append("】文件执行失败.").append("错误信息:").append(mostSpecificCause.getMessage());
					logger.info(message.toString());
					logger.info(e.getMessage());
					errorInfo.put("fileName", sqlFile);
					errorInfo.put("msg", message.toString());
					errorInfo.put("sqlMsg", e.getMessage());
					errors.add(errorInfo);
				}

			}

		}
		return errors;
	}

	/**
	 * 获取目录下的sql文件.未读取子目录
	 * 
	 * @param filePath
	 * @return
	 */
	public static List<String> getSqlFiles(String filePath) {
		File file = new File(filePath);
		List<String> fileNames = new ArrayList<>();
		if (file.isDirectory()) {
			String[] files = file.list();
			if (files != null && files.length > 0) {
				for (String fileName : files) {
					if (fileName.endsWith(".sql")) {
						fileName = filePath + "/" + fileName;
						fileNames.add(fileName);
					}
				}
			}
		}
		
		if(fileNames.size() >0){
			Collections.sort(fileNames, new Comparator<String>() {
				@Override
				public int compare(String file1,String file2) {
					return file1.compareTo(file2);
				}
			});
		}
		
		
		return fileNames;
	}
}
