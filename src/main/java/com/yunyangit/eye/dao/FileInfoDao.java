package com.yunyangit.eye.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yunyangit.eye.model.FileInfo;

@Mapper
public interface FileInfoDao {

	@Insert("insert into uploadRecord (dateTime, state, downloadLink1, downloadLink2, userId,type) values(getdate(),#{state},#{downloadLink1},null,#{user.id},#{type})")
	int save(FileInfo fileInfo);
	
	int update(FileInfo fileInfo);
	
	int count(@Param("params") Map<String, Object> params);
	
	List<FileInfo> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset,
			@Param("limit") Integer limit);
}
