package auto.deploy.dao.entity.sys;

import auto.deploy.dao.entity.Entity;

/**
 * 
 * @描述：数据字典表(实体类).
 * 
 * @作者：zhongjy
 * 
 * @时间: 2017-05-24
 */
public class SysDataDict extends Entity<SysDataDict> {

	private static final long serialVersionUID = 1L;

	/**
	 * 数据字典编码(类型)
	 */
	private String dictCode;
	/**
	 * 数据字典名称
	 */
	private String dictName;
	/**
	 * 数据字典-key
	 */
	private String dictKey;
	/**
	 * 数据字典-value
	 */
	private String dictValue;
	/**
	 * 是否激活
	 */
	private Integer isActive;

	public String getDictCode() {
		return dictCode;
	}

	public SysDataDict setDictCode(String dictCode) {
		this.dictCode = dictCode;
		return this;
	}

	public String getDictName() {
		return dictName;
	}

	public SysDataDict setDictName(String dictName) {
		this.dictName = dictName;
		return this;
	}

	public String getDictKey() {
		return dictKey;
	}

	public SysDataDict setDictKey(String dictKey) {
		this.dictKey = dictKey;
		return this;
	}

	public String getDictValue() {
		return dictValue;
	}

	public SysDataDict setDictValue(String dictValue) {
		this.dictValue = dictValue;
		return this;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public SysDataDict setIsActive(Integer isActive) {
		this.isActive = isActive;
		return this;
	}

}
