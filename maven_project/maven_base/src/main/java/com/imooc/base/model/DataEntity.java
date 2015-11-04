package com.imooc.base.model;

/**
 * 2012-6-19
 */

import java.util.Date;

/**
 * 数据实体基类
 * @author FengMy
 * @since 2012-6-19下午03:19:07
 */
public abstract class DataEntity extends CoreEntity {
	private static final long serialVersionUID = 2764148395385508786L;
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 编码
	 */
	private String number;
	
	/**
	 * 拼音
	 */
	private String simplePinyin;
	
	/**
	 * 描述
	 */
	private String description;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	
	/**
	 * 最后更新时间
	 */
	private Date lastUpdateTime;
	
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
		
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param lastUpdateTime the lastUpdateTime to set
	 */
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	/**
	 * @return the lastUpdateTime
	 */
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	
	


	public void setSimplePinyin(String simplePinyin) {
		this.simplePinyin = simplePinyin;
	}

	public String getSimplePinyin() {
		return simplePinyin;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
