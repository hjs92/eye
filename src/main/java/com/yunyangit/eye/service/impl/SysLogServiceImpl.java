package com.yunyangit.eye.service.impl;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.yunyangit.eye.dao.SysLogsDao;
import com.yunyangit.eye.model.SysLogs;
import com.yunyangit.eye.model.SysUser;
import com.yunyangit.eye.service.SysLogService;



@Service
public class SysLogServiceImpl implements SysLogService {

	private static final Logger log = LoggerFactory.getLogger("adminLogger");

	@Autowired
	private SysLogsDao sysLogsDao;

	/**
	 * 将该方法改为异步,用户由调用者设置
	 */
	@Async
	@Override
	public void save(SysLogs sysLogs) {
//		SysUser user = UserUtil.getLoginUser();
		if (sysLogs == null || sysLogs.getUser() == null || sysLogs.getUser().getId() == null) {
			return;
		}

//		sysLogs.setUser(user);
		if (sysLogs.getRemark() == null){
			sysLogs.setRemark("默认");
		}
		sysLogsDao.save(sysLogs);
	}

	@Async
	@Override
	public void save(Long userId, String module, Boolean flag, String remark) {
		SysLogs sysLogs = new SysLogs();
		sysLogs.setFlag(flag);
		sysLogs.setModule(module);
		sysLogs.setRemark(remark);

		SysUser user = new SysUser();
		user.setId(userId);
		sysLogs.setUser(user);
		sysLogsDao.save(sysLogs);

	}

	@Override
	public void deleteLogs() {
		Date date = DateUtils.addMonths(new Date(), -3);
		String time = DateFormatUtils.format(date, DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.getPattern());

		int n = sysLogsDao.deleteLogs(time);
		log.info("删除{}之前日志{}条", time, n);
	}
}
