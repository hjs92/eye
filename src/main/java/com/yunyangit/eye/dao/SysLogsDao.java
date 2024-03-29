package com.yunyangit.eye.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import com.yunyangit.eye.model.SysLogs;


@Mapper
public interface SysLogsDao {

	@Options(useGeneratedKeys = true, keyProperty = "id")
	@Insert("insert into sys_logs(userId,module, flag, remark, createTime) values(#{user.id}, #{module}, #{flag}, #{remark}, getdate())")
	int save(SysLogs sysLogs);

	int count(@Param("params") Map<String, Object> params);

	List<SysLogs> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset,
			@Param("limit") Integer limit);

	@Delete("delete from sys_logs where createTime <= #{time}")
	int deleteLogs(String time);
}
