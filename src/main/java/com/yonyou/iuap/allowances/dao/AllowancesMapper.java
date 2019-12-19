package com.yonyou.iuap.allowances.dao;

import com.yonyou.iuap.allowances.model.Allowances;
import com.yonyou.iuap.baseservice.persistence.mybatis.mapper.GenericMapper;
import com.yonyou.iuap.mybatis.anotation.MyBatisRepository;

@MyBatisRepository
public interface AllowancesMapper extends GenericMapper<Allowances> {

}
