package com.yonyou.iuap.common.service;

import com.yonyou.iuap.baseservice.persistence.mybatis.ext.utils.EntityUtil;
import com.yonyou.iuap.baseservice.persistence.mybatis.ext.utils.FieldUtil;
import com.yonyou.iuap.baseservice.ref.dao.mapper.RefCommonMapper;
import com.yonyou.iuap.baseservice.statistics.support.ParamProcessResult;
import com.yonyou.iuap.baseservice.statistics.util.SearchParamUtil;
import com.yonyou.iuap.common.dao.CustomMapper;
import com.yonyou.iuap.mvc.type.SearchParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;
import java.lang.reflect.Field;
import java.util.*;

@SuppressWarnings("deprecation")
@Service
public class CustomService {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(CustomService.class);

	@Autowired
	RefCommonMapper mapper;

	@SuppressWarnings("rawtypes")
	public Page<Map> selectAllByPageWithWhere(CustomMapper customMapper, PageRequest pageRequest,
			SearchParams searchParams, String modelCode) {

		ParamProcessResult ppr = SearchParamUtil.processServiceParams(searchParams, modelCode);
		if (ppr.getSort() != null) {
			pageRequest = new PageRequest(pageRequest.getPageNumber(), pageRequest.getPageSize(), ppr.getSort());
		}

		if (ppr.getGroupStatements() == null || ppr.getGroupStatements().size() == 0) {
			Set<String> allFields = new HashSet<>();
			List<String> groupFields = new ArrayList<>();
			Class<?> entityClazz = ppr.getStateModel().getmClass();
			for (Field f : EntityUtil.getFields(entityClazz)) {
				if (f.getAnnotation(Transient.class) == null) {
					allFields.add(FieldUtil.getColumnName(f) + " as \"" + f.getName() + "\"");
					groupFields.add(f.getName());
				}
			}
			ppr.setGroupFields(groupFields);
			ppr.setGroupStatements(allFields);
		}
		Page<Map> page = customMapper.selectAllByPage(pageRequest, searchParams, ppr.getWhereStatements()).getPage();
		SearchParamUtil.processSelectList(page.getContent(), ppr, mapper);

		return page;

	}

}
