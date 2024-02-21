package com.yunyangit.eye.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yunyangit.eye.dao.DictDao;
import com.yunyangit.eye.model.Dict;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 数据字典相关接口
 *
 */
@Api(tags = "数据字典")
@RestController
@RequestMapping("/api/dicts")
public class DictController {
	
	@Autowired
	private DictDao dictDao;
	
	@GetMapping("/getJob")
	@ApiOperation(value = "获取数据字典")
	public List<Dict> listByType(String lang) {
		String type = "job";
		return dictDao.listByType(type,lang);
	}
}
