package com.yonyou.iuap.allowances.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yonyou.iuap.allowances.constant.AllowanceTypeEnum;
import com.yonyou.iuap.allowances.constant.ExdeedsEnum;
import com.yonyou.iuap.allowances.constant.MonthEnum;
import com.yonyou.iuap.allowances.constant.PickTypeEnum;
import com.yonyou.iuap.allowances.constant.SexEnum;
import com.yonyou.iuap.baseservice.entity.AbsDrModel;
import com.yonyou.iuap.baseservice.entity.annotation.CodingEntity;
import com.yonyou.iuap.baseservice.entity.annotation.CodingField;
import com.yonyou.iuap.baseservice.entity.annotation.Reference;
import com.yonyou.iuap.baseservice.multitenant.entity.MultiTenant;
import com.yonyou.iuap.baseservice.statistics.support.StatFunctions;
import com.yonyou.iuap.baseservice.statistics.support.StatisticsField;
import com.yonyou.iuap.baseservice.support.condition.Condition;
import com.yonyou.iuap.baseservice.support.condition.Match;
import com.yonyou.iuap.baseservice.support.generator.GeneratedValue;
import com.yonyou.iuap.enumeration.entity.anno.EnumValue;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "IUAPD_ALLOWANCES")
@CodingEntity(codingField = "code")
public class Allowances extends AbsDrModel implements Serializable, MultiTenant {

	@Column(name = "TENANT_ID")
	@Condition(match = Match.EQ)
	private String tenantid;

	// ID
	@Id
	@GeneratedValue
	@Condition
	@StatisticsField(functions = { StatFunctions.count })
	protected String id;

	// 员工编号
	@Condition(match = Match.LIKE)
	@Column(name = "CODE")
	@CodingField(code = "iuapd_asval")
	private String code;

	// 姓名
	@Condition
	@Column(name = "NAME")
	private String name;

	// 性别, 枚举
	@EnumValue(value = SexEnum.class, des = "sexEnumValue")
	@Condition
	@Column(name = "SEX")
	private Integer sex;
	@Transient
	private String sexEnumValue;

	// 部门
	@Condition
	@Column(name = "DEPT")
	@Reference(code = "newdept", srcProperties = { "refname" }, desProperties = { "deptName" })
	private String dept;
	@Transient
	private String deptName;

	// 职级
	@Condition
	@Column(name = "POST_LEVEL")
	@Reference(code = "post_level", srcProperties = { "post_level" }, desProperties = { "levelName" })
	private String postLevel;
	@Transient
	private String levelName;

	// 工龄
	@Condition
	@Column(name = "SERVICE_YEARS")
	private Integer serviceYears;

	// 司龄
	@Condition
	@Column(name = "SERVICE_YEARS_COMPANY")
	private Integer serviceYearsCompany;

	// 年份
	@Condition(match = Match.EQ)
	@Column(name = "YEAR")
	private String year;

	// 月份，枚举
	@EnumValue(value = MonthEnum.class, des = "monthEnumValue")
	@Condition(match = Match.EQ)
	@Column(name = "MONTH")
	private Integer month;
	@Transient
	private String monthEnumValue;

	// 补贴类别，枚举
	@EnumValue(value = AllowanceTypeEnum.class, des = "allowanceTypeEnumValue")
	@Condition
	@Column(name = "ALLOWANCE_TYPE")
	private Integer allowanceType;
	@Transient
	private String allowanceTypeEnumValue;

	// 补贴标准
	@Condition
	@Column(name = "ALLOWANCE_STANDARD")
	private BigDecimal allowanceStandard;

	// 实际补贴
	@Condition
	@Column(name = "ALLOWANCE_ACTUAL")
	@StatisticsField(functions = { StatFunctions.sum })
	private BigDecimal allowanceActual;

	// 是否超标，枚举
	@EnumValue(value = ExdeedsEnum.class, des = "exdeedsEnumValue")
	@Condition(match = Match.EQ)
	@Column(name = "EXDEEDS")
	private Integer exdeeds;
	@Transient
	private String exdeedsEnumValue;

	// 申请时间
	@Condition
	@Column(name = "APPLY_TIME")
	private String applyTime;

	// 领取方式
	@EnumValue(value = PickTypeEnum.class, des = "pickTypeEnumValue")
	@Condition
	@Column(name = "PICK_TYPE")
	private Integer pickType;
	@Transient
	private String pickTypeEnumValue;

	// 领取时间
	@Condition
	@Column(name = "PICK_TIME")
	private String pickTime;

	// 备注
	@Condition
	@Column(name = "REMARK")
	private String remark;

	public String getTenantid() {
		return this.tenantid;
	}

	public void setTenantid(String tenantid) {
		this.tenantid = tenantid;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(Serializable id) {
		this.id = id.toString();
		super.id = id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getSexEnumValue() {
		return sexEnumValue;
	}

	public void setSexEnumValue(String sexEnumValue) {
		this.sexEnumValue = sexEnumValue;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getPostLevel() {
		return postLevel;
	}

	public void setPostLevel(String postLevel) {
		this.postLevel = postLevel;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public Integer getServiceYears() {
		return serviceYears;
	}

	public void setServiceYears(Integer serviceYears) {
		this.serviceYears = serviceYears;
	}

	public Integer getServiceYearsCompany() {
		return serviceYearsCompany;
	}

	public void setServiceYearsCompany(Integer serviceYearsCompany) {
		this.serviceYearsCompany = serviceYearsCompany;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public String getMonthEnumValue() {
		return monthEnumValue;
	}

	public void setMonthEnumValue(String monthEnumValue) {
		this.monthEnumValue = monthEnumValue;
	}

	public Integer getAllowanceType() {
		return allowanceType;
	}

	public void setAllowanceType(Integer allowanceType) {
		this.allowanceType = allowanceType;
	}

	public String getAllowanceTypeEnumValue() {
		return allowanceTypeEnumValue;
	}

	public void setAllowanceTypeEnumValue(String allowanceTypeEnumValue) {
		this.allowanceTypeEnumValue = allowanceTypeEnumValue;
	}

	public BigDecimal getAllowanceStandard() {
		return allowanceStandard;
	}

	public void setAllowanceStandard(BigDecimal allowanceStandard) {
		this.allowanceStandard = allowanceStandard;
	}

	public BigDecimal getAllowanceActual() {
		return allowanceActual;
	}

	public void setAllowanceActual(BigDecimal allowanceActual) {
		this.allowanceActual = allowanceActual;
	}

	public Integer getExdeeds() {
		return exdeeds;
	}

	public void setExdeeds(Integer exdeeds) {
		this.exdeeds = exdeeds;
	}

	public String getExdeedsEnumValue() {
		return exdeedsEnumValue;
	}

	public void setExdeedsEnumValue(String exdeedsEnumValue) {
		this.exdeedsEnumValue = exdeedsEnumValue;
	}

	public String getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}

	public Integer getPickType() {
		return pickType;
	}

	public void setPickType(Integer pickType) {
		this.pickType = pickType;
	}

	public String getPickTypeEnumValue() {
		return pickTypeEnumValue;
	}

	public void setPickTypeEnumValue(String pickTypeEnumValue) {
		this.pickTypeEnumValue = pickTypeEnumValue;
	}

	public String getPickTime() {
		return pickTime;
	}

	public void setPickTime(String pickTime) {
		this.pickTime = pickTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
