package com.yonyou.iuap.allowances.service;

import static com.yonyou.iuap.baseservice.intg.support.ServiceFeature.LOGICAL_DEL;
import static com.yonyou.iuap.baseservice.intg.support.ServiceFeature.MULTI_TENANT;
import static com.yonyou.iuap.baseservice.intg.support.ServiceFeature.REFERENCE;
import static com.yonyou.iuap.baseservice.intg.support.ServiceFeature.REMOTE_REFERENCE;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import cn.hutool.core.date.DateUtil;

import com.yonyou.iuap.allowances.dao.AllowancesMapper;
import com.yonyou.iuap.allowances.model.Allowances;
import com.yonyou.iuap.baseservice.intg.service.GenericIntegrateService;
import com.yonyou.iuap.baseservice.intg.support.ServiceFeature;
import com.yonyou.uap.busilog.annotation.LogConfig;
import com.yonyou.iuap.mvc.type.SearchParams;
import com.yonyou.iuap.mybatis.type.PageResult;

@Service
public class AllowancesService extends GenericIntegrateService<Allowances> {

	private AllowancesMapper mapper;

	@Autowired
	public void setMapper(AllowancesMapper mapper) {
		this.mapper = mapper;
		super.setGenericMapper(mapper);
	}

	@Override
	protected ServiceFeature[] getFeats() {
		return new ServiceFeature[] { REFERENCE, MULTI_TENANT, LOGICAL_DEL,
				REMOTE_REFERENCE };
	}

	private static final String DATEFORMAT = "yyyy-MM-dd HH:mm:ss";

	@LogConfig(busiCode = "allowances_saveMultiple", busiName = "员工津贴", operation = "员工津贴批量添加", templateId = "allowances_saveMultiple")
	public void saveMultiple(List<Allowances> listData) {
		for (Allowances allowances : listData) {
			String now = DateUtil.format(new Date(), DATEFORMAT);
			allowances.setApplyTime(now);
		}
		super.saveBatch(listData);
	}

	@LogConfig(busiCode = "allowances_updateMultiple", busiName = "员工津贴", operation = "员工津贴批量修改", templateId = "allowances_updateMultiple")
	public void updateMultiple(List<Allowances> listData) {
		super.saveBatch(listData);
	}

	@Override
	@LogConfig(busiCode = "allowances_deleteBatch", busiName = "员工津贴", operation = "员工津贴删除", templateId = "allowances_deleteBatch")
	public int deleteBatch(List<Allowances> list) {
		return super.deleteBatch(list);
	}

	@Override
	@LogConfig(busiCode = "allowances_insertSelective", busiName = "员工津贴", operation = "员工津贴保存", templateId = "allowances_insertSelective")
	public Allowances insertSelective(Allowances entity) {
		String now = DateUtil.format(new Date(), DATEFORMAT);
		entity.setApplyTime(now);
		return super.insertSelective(entity);
	}

	@Override
	@LogConfig(busiCode = "allowances_updateSelective", busiName = "员工津贴", operation = "员工津贴修改", templateId = "allowances_updateSelective")
	public Allowances updateSelective(Allowances entity) {
		return super.updateSelective(entity);

	}

}
