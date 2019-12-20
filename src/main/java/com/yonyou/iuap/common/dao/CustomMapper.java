package com.yonyou.iuap.common.dao;

import com.yonyou.iuap.mvc.type.SearchParams;
import com.yonyou.iuap.mybatis.anotation.MyBatisRepository;
import com.yonyou.iuap.mybatis.type.PageResult;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Map;

@MyBatisRepository
public interface CustomMapper {
	@SuppressWarnings("rawtypes")
	PageResult<Map> selectAllByPage(
			@Param("page") PageRequest pageRequest,
			@Param("condition") SearchParams searchParams, 
			@Param("whereStatements") List<Map<String, Object>> whereParams);
}
