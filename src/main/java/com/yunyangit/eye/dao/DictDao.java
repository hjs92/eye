package com.yunyangit.eye.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.yunyangit.eye.model.Dict;

@Mapper
public interface DictDao {
	@Select("select * from sys_dict where type = #{type} and lang = #{lang}")
	List<Dict> listByType(@Param("type") String type, @Param("lang")  String lang);
}
