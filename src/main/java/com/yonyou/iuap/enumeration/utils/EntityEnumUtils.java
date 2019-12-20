package com.yonyou.iuap.enumeration.utils;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yonyou.iuap.baseservice.entity.Model;
import com.yonyou.iuap.baseservice.persistence.support.QueryFeatureExtension;
import com.yonyou.iuap.enumeration.utils.EnumValueUtils;
import com.yonyou.iuap.mvc.type.SearchParams;

@Service
public class EntityEnumUtils implements QueryFeatureExtension<Model> {

	@SuppressWarnings(value = { "unchecked" })
	@Override
	public List<Model> afterListQuery(List<Model> list) {
		Object obj = list.get(0);
		Class<?> modelClass = obj.getClass();
		return EnumValueUtils.i18nEnumEntityKeyToValue(list, modelClass);
	}

	@SuppressWarnings(value = { "rawtypes" })
	@Override
	public SearchParams prepareQueryParam(SearchParams searchParams,
			Class modelClass) {
		return searchParams;
	}

	@SuppressWarnings(value = { "unchecked", "rawtypes" })
	public static List<Map> fillDynamicListToMap(List<Map> list,
			Class modelClass) {
		return EnumValueUtils.i18nEnumMapKeyToValue(list, modelClass);
	}

	@SuppressWarnings(value = { "unchecked", "rawtypes" })
	public static <T> List<T> fillDynamicListToEntity(List<T> list,
			Class modelClass) {
		return EnumValueUtils.i18nEnumEntityKeyToValue(list, modelClass);
	}
}
