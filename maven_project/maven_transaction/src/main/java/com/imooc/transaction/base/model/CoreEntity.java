package com.imooc.transaction.base.model;


import java.io.Serializable;
import java.util.UUID;

/**
 * 实体超类
 * @author FengMy
 * @since 2012-6-19下午03:08:32
 */
public abstract class CoreEntity implements Serializable {
	private static final long serialVersionUID = 7950905616077388683L;
	/**
	 * ID
	 */
	private String id;
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * 是否记录历史数据
	 * @return
	 */
	public boolean isRecordHistory(){
		return false;
	}
	
	/**
	 * 返回数据记录的表名,为了提高查询速度，建议单独建表
	 * @return
	 */
	public String getRecordTableName(){
		return "T_LOG_DATAHISTORY";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null || !(obj instanceof CoreEntity)){
			return false;
		}
		if(this.id==null){
			return ((CoreEntity)obj).getId() == null;
		}
		return this.id.equals(((CoreEntity)obj).getId());
	}
	
	/**
	 * 给ID设置UUID
	 */
	public void setUUID(){		
		this.id = UUID.randomUUID().toString();
	}
}
