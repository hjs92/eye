package com.yunyangit.eye.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.yunyangit.eye.model.HistoricalResults;
import com.yunyangit.eye.model.UserEvaluate;

@Mapper
public interface HistoricalResultsDao {

	@Options(useGeneratedKeys = true, keyProperty = "id")
	@Insert("insert into historicalResults (pid, files, dateTime, userId, type) values (#{pid},#{files},getdate(),#{userId},#{type}) ")
	int save(HistoricalResults historicalResults);
	
	int count(@Param("params") Map<String, Object> params);
	
	List<HistoricalResults> list(@Param("params") Map<String, Object> params, @Param("offset") Integer offset,
			@Param("limit") Integer limit);
	
	@Options(useGeneratedKeys = true, keyProperty = "id")
	@Insert("insert into userEvaluate (struuid, content, level) values(#{struuid},#{content},#{level})")
	int saveEvaluate(UserEvaluate userEvaluate);
	
	@Select("select * from userEvaluate where struuid =  #{struuid} ")
	UserEvaluate getEvaluate(@Param("struuid") String struuid);
	
	@Select("select content from resultsDetails where struuId = #{str_uuid} and language = #{lang} ")
	String getDiagnoseResult(@Param("str_uuid") String str_uuid, @Param("lang")String lang);
	
	@Insert("insert into resultsDetails (struuId, language, content) values (#{str_uuid},#{lang},#{content})")
	int addDiagnoseResult(@Param("str_uuid") String str_uuid, @Param("lang")String lang, @Param("content")String content);
	
}
