package com.yunyangit.eye.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yunyangit.eye.annotation.LogAnnotation;
import com.yunyangit.eye.dao.HistoricalResultsDao;
import com.yunyangit.eye.model.UserEvaluate;

/**
 * 评价相关接口
 *
 */
@Api(tags = "用户评价")
@RestController
@RequestMapping("/api/Evaluate")
public class EvaluateController {
	
	@Autowired
	private HistoricalResultsDao historicalResultsDao;

	@LogAnnotation
	@PostMapping("/saveEvaluate")
	@ApiOperation(value = "提交评价")
	public void saveEvaluate(@RequestBody UserEvaluate userEvaluate){
		historicalResultsDao.saveEvaluate(userEvaluate);
	}
	
	@GetMapping("/getEvaluate")
	@ApiOperation(value = "获取评价")
	public UserEvaluate getEvaluate(String struuid){
		return historicalResultsDao.getEvaluate(struuid);
	}
	
}
