package com.yonyou.iuap.allowances.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yonyou.iuap.allowances.model.Allowances;
import com.yonyou.iuap.allowances.service.AllowancesService;
import com.yonyou.iuap.base.web.BaseController;
import com.yonyou.iuap.baseservice.statistics.service.StatCommonService;
import com.yonyou.iuap.enumeration.utils.EntityEnumUtils;
import com.yonyou.iuap.mvc.constants.RequestStatusEnum;
import com.yonyou.iuap.mvc.type.SearchParams;
import com.yonyou.iuap.i18n.MessageSourceUtil;

@Controller
@RequestMapping(value = "/query_allowances")
public class QueryAllowancesController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(QueryAllowancesController.class);

	private static final String KEY1 = "ja.all.con1.0001";
	private static final String MSG1 = "查询数据异常";

	@Autowired
	private StatCommonService statCommonService;

	@Autowired
	private AllowancesService service;

	private static final String MODELCODE = Allowances.class.getSimpleName();

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Object list(PageRequest pageRequest, @RequestBody Map<String, Object> searchMap) {
		try {
			SearchParams searchParams = new SearchParams();
			searchParams.setSearchMap(searchMap);
			if (pageRequest.getPageSize() == 1) {
				Integer allCount = Integer.MAX_VALUE - 1;
				pageRequest = new PageRequest(pageRequest.getPageNumber(), allCount, pageRequest.getSort());
			}
			Page<Map> page = this.service.selectAllByPageWithWhere(pageRequest, searchParams, MODELCODE);
			EntityEnumUtils.fillDynamicListToMap(page.getContent(), Allowances.class);
			return this.buildSuccess(page);
		} catch (Exception exp) {
			logger.error(MessageSourceUtil.getMessage(KEY1, MSG1), exp);
			return this.buildError("msg", MessageSourceUtil.getMessage(KEY1, MSG1), RequestStatusEnum.FAIL_FIELD);
		}
	}

	@RequestMapping(value = "/distinct", method = RequestMethod.POST)
	@ResponseBody
	public Object distinct(@RequestBody Map<String, Object> searchMap) {
		try {
			SearchParams searchParams = new SearchParams();
			Map<String, Object> resultMap = new HashMap<>();
			searchParams.setSearchMap(searchMap);
			@SuppressWarnings("rawtypes")
			List<Map> list = this.statCommonService.findDistinct(searchParams, MODELCODE);
			EntityEnumUtils.fillDynamicListToMap(list, Allowances.class);
			resultMap.put("content", list);
			return buildSuccess(list);
		} catch (Exception exp) {
			logger.error(MessageSourceUtil.getMessage(KEY1, MSG1), exp);
			return this.buildError("msg", MessageSourceUtil.getMessage(KEY1, MSG1), RequestStatusEnum.FAIL_FIELD);
		}
	}
}
