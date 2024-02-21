package com.yunyangit.eye.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yunyangit.eye.dao.SysLogsDao;
import com.yunyangit.eye.model.SysLogs;
import com.yunyangit.eye.page.table.PageTableHandler;
import com.yunyangit.eye.page.table.PageTableHandler.CountHandler;
import com.yunyangit.eye.page.table.PageTableHandler.ListHandler;
import com.yunyangit.eye.page.table.PageTableRequest;
import com.yunyangit.eye.page.table.PageTableResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "日志")
@RestController
@RequestMapping("/api/logs")
public class SysLogsController {

	@Autowired
	private SysLogsDao sysLogsDao;

	@GetMapping
	@ApiOperation(value = "日志列表")
	public PageTableResponse list(PageTableRequest request) {
		return new PageTableHandler(new CountHandler() {

			@Override
			public int count(PageTableRequest request) {
				return sysLogsDao.count(request.getParams());
			}
		}, new ListHandler() {

			@Override
			public List<SysLogs> list(PageTableRequest request) {
				return sysLogsDao.list(request.getParams(), request.getOffset(), request.getLimit());
			}
		}).handle(request);
	}

}
