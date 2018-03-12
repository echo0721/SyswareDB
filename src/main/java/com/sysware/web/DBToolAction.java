package com.sysware.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sysware.entity.ImportVo;
import com.sysware.service.FileReader;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/dbTool")
public class DBToolAction {
		static Map<String, ImportVo> hisrotys = Collections.synchronizedMap(new HashMap<String, ImportVo>());

	    @ApiOperation(value="导入历史列表", notes="")
	    @RequestMapping(value={""}, method=RequestMethod.GET)
	    public List<ImportVo> getRoleList() {
	        List<ImportVo> r = new ArrayList<ImportVo>(hisrotys.values());
	        return r;
	    }
	    @ApiOperation(value="导入数据库脚本", notes="根据目录信息导入脚本")
	    @RequestMapping(value="", method=RequestMethod.POST)
	    public List<Map<String, String>> postRole(@RequestBody 
	    	    @ApiParam(name = "ImportVo", value = "数据库导入信息", required = true)
	    		ImportVo vo) {
	     
	    	List<Map<String, String>> execute = FileReader.execute(vo);
	    	hisrotys.put(vo.getPath(), vo);
	        return execute;
	    }
}
