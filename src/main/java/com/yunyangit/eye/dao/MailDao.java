package com.yunyangit.eye.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.yunyangit.eye.model.Mail;

@Mapper
public interface MailDao {

	@Select("select * from sys_mail  where id = #{id}")
	Mail getById(String id);
}
