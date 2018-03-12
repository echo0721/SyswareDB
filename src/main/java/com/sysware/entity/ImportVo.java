package com.sysware.entity;

import io.swagger.annotations.ApiModelProperty;

public class ImportVo {
	
	@ApiModelProperty(value="数据库ip",name="ip",required=true)
	private String ip;//数据库ip
	
	@ApiModelProperty(hidden=true)
	private String serviceName = "sysware";//连接名 默认为sysware
	
	@ApiModelProperty(value="数据库用户名",name="username",required=true)
	private String username;//数据库用户名
	
	@ApiModelProperty(value="数据库密码",name="password",required=true)
	private String password;//数据库密码
	
	@ApiModelProperty(value="sql存放文件夹目录",name="path",required=true)
	private String path;//文件夹目录
	
	@ApiModelProperty(hidden=true)
	private Integer port = 1521;//Oracle端口
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Integer getPort() {
		if(port == null){
			return 1521;
		}
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	
}
