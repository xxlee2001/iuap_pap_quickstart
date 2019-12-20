package com.yonyou.iuap.allowances.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.yonyou.iuap.allowances.dao.AllowancesMapper;
import com.yonyou.iuap.common.service.CustomService;
import com.yonyou.iuap.mvc.type.SearchParams;

@Service
public class AllowancesService {

	@Autowired
	private AllowancesMapper mapper;

	@Autowired
	private CustomService service;

	@SuppressWarnings("rawtypes")
	public Page<Map> selectAllByPageWithWhere(PageRequest pageRequest, SearchParams searchParams, String modelCode) {
		return service.selectAllByPageWithWhere(mapper, pageRequest, searchParams, modelCode);
	}

}
