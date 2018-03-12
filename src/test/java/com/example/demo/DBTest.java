package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBTest {
	public static void main(String[] args) {
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
		String sql = "INSERT INTO file_import_test t (t.id,t.name,t.age) VALUES ('10','张三',20)";
		 
		
		
		
		
		String func = "create or replace function getSrcTaskTemplateVersionId(relatedtasktemplateid in varchar2) "+
"\n   return varchar2 as                                                                                      "+
"\n   srcTaskTemplateVersionId varchar2(400);                                                                 "+
"\n   count_number             number;                                                                        "+
"\n                                                                                                           "+
"\n begin                                                                                                     "+
"\n   --函数定义：研发模板版本场景中，依据P2M端任务的relatedtasktemplateid属性值，获取其对应的活动模板版本对象 ID "+
"\n   --注意：若当前任务对应流程模板版本对象，因为限定了活动模板的类型，当前函数获取为空。                    "+
"\n                                                                                                           "+
"\n   --情况0: relatedtasktemplateid为空的情况未逻辑显性化                                                    "+
"\n                                                                                                           "+
"\n   --情况1：在P2M端应用活动模板版本对象生成的任务                                                          "+
"\n   if srcTaskTemplateVersionId is null then                                                                "+
"\n     select count(1)                                                                                       "+
"\n       into count_number                                                                                   "+
"\n       from dm_dt_version v1, dm_dt_task_object t1                                                         "+
"\n      where v1.id = t1.id                                                                                  "+
"\n        and t1.modeltypeid = 'taskModel'                                                                   "+
"\n        and t1.id = relatedtasktemplateid;                                                                 "+
"\n     if count_number = 1 then                                                                              "+
"\n       srcTaskTemplateVersionId := relatedtasktemplateid;                                                  "+
"\n     end if;                                                                                               "+
"\n   end if;                                                                                                 "+
"\n                                                                                                           "+
"\n   --情况2：在P2M端通过应用流程模板版本对象时，内部子活动模板生成的任务                                    "+
"\n   if srcTaskTemplateVersionId is null then                                                                "+
"\n     select count(1)                                                                                       "+
"\n       into count_number                                                                                   "+
"\n       from dm_dt_link l2, dm_dt_version v2, dm_dt_task_object t2                                          "+
"\n      where l2.srcstandardid = v2.id                                                                       "+
"\n        and v2.id = t2.id                                                                                  "+
"\n        and t2.modeltypeid = 'taskModel'                                                                   "+
"\n        and l2.id = relatedtasktemplateid;                                                                 "+
"\n     if count_number = 1 then                                                                              "+
"\n       select l.srcstandardid                                                                              "+
"\n         into srcTaskTemplateVersionId                                                                     "+
"\n         from dm_dt_link l                                                                                 "+
"\n        where l.id = relatedtasktemplateid;                                                                "+
"\n     end if;                                                                                               "+
"\n   end if;                                                                                                 "+
"\n                                                                                                           "+
"\n   --返回                                                                                                  "+
"\n   return(srcTaskTemplateVersionId);                                                                       "+
"\n                                                                                                           "+
"\n end getSrcTaskTemplateVersionId;                                                                          "+
" " ;
		
		
		
		String createSql = ""+
//				"set define off \n"
//				+ "spool 8.log \n"+ 
				"prompt \n"
				+ " prompt Creating table SYS_DYNAMIC_LAYOUT \n"
				+ " prompt ================================= \n"
				+ "prompt \n"
				+ " create table SYS_DYNAMIC_LAYOUT ( id VARCHAR2(40) not null, sort VARCHAR2(40), url VARCHAR2(100), description VARCHAR2(200), height VARCHAR2(40) ) ;"+
	"	comment on column SYS_DYNAMIC_LAYOUT.id is '主键';                                           "+
	"	comment on column SYS_DYNAMIC_LAYOUT.sort is '排序';                                         "+
	"	comment on column SYS_DYNAMIC_LAYOUT.url is '路径';                                          "+
	"	comment on column SYS_DYNAMIC_LAYOUT.description is '描述';                                  "+
	"	comment on column SYS_DYNAMIC_LAYOUT.height is '外部div高度';                                "+
	"	alter table SYS_DYNAMIC_LAYOUT add constraint SYS_DYNAMICLAYOUT_PIRM primary key (ID);   \n    "+
//	"	spool off"	     ;                                                                            
		"";
		
		try {
			Connection connection = DriverManager.getConnection(jdbcUrl.toString(), username, password);
			Statement statement = connection.createStatement();
			
			boolean execute = statement.execute(createSql);
			System.out.println(execute);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	}
}
