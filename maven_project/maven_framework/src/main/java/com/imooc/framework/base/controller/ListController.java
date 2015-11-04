package com.imooc.framework.base.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.imooc.base.annotation.Dependence;
import com.imooc.base.common.Pagination;
import com.imooc.base.common.StringUtils;
import com.imooc.base.model.CoreEntity;
import com.imooc.base.service.BaseService;
import com.imooc.base.utils.DateUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 列表界面Controller
 * @author FengMy
 * @since 2012-6-26下午04:53:54
 */
public abstract class ListController extends BaseController {
	
	/**
	 * 列表数据
	 * @param pagination
	 * @param response
	 */
	@RequestMapping(value="listData")
	@Dependence(method="list")
	public void listData(Pagination<?> pagination,HttpServletResponse response){
		pagination = queryExecutor.execQuery(getListMapper(), pagination, getListDataParam());
		afterFetchListData(pagination);
		outPrint(response, JSONObject.fromObject(pagination, getDefaultJsonConfig()));
	}
	
	/**
	 * 获取列表数据后
	 * @param pagination
	 */
	protected void afterFetchListData(Pagination<?> pagination){
	}
	
	@RequestMapping(value="listAllData")
	@Dependence(method="list")
	public void listAllData(HttpServletResponse response){
		List<Object> datalist = queryExecutor.execQuery(getListMapper(), getListDataParam(),Object.class);
		outPrint(response, JSONArray.fromObject(datalist, getDefaultJsonConfig()));
	}
	
	@RequestMapping(value="list")
	public String list(){
		return getListView();
	}
	
	@RequestMapping(value="add")
	@Dependence(method="list")
	public String add(ModelMap model){
		model.put("data", createNewEntity());
		return getEditView();
	}
	@RequestMapping(value="edit")
	@Dependence(method="list")
	public String edit(ModelMap model,@RequestParam(required=true,value="id")String id){
		model.put("data", getService().getEntityById(id));
		//将当前状态放入到界面 addded by taking.wang 2013-01-24
		model.put("edit_viewStatus", this.getString("VIEWSTATE"));
		return getEditView();
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value="delete")
	@Dependence(method="list")
	public void delete(@RequestParam(required=true,value="id")String id,HttpServletResponse response){
		CoreEntity entity = getService().getEntityById(id);
		if(entity!=null){
			if(isAllowDelete(entity)){
				getService().deleteEntity(entity);
				getOutputMsg().put("STATE", "SUCCESS");
				getOutputMsg().put("MSG", "删除成功");
			}else{
				getOutputMsg().put("STATE", "FAIl");
			}
		}else{
			getOutputMsg().put("STATE", "FAIL");
			getOutputMsg().put("MSG", "记录不存在");
		}
		outPrint(response, JSONObject.fromObject(getOutputMsg(), getDefaultJsonConfig()));
	}
	
	protected boolean isAllowDelete(CoreEntity entity) {
		return true;
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value="deleteBatch")
	@Dependence(method="list")
	public void deleteBatch(HttpServletResponse response){
		String ids = getString("ids");
		if(!StringUtils.isEmpty(ids)){
			String[] idAtt = ids.split(";");
			List<String> idList = new ArrayList<String>();
			for(String id : idAtt){
				idList.add(id);
			}
			getService().deleteBatch(idList);
		}
		getOutputMsg().put("STATE", "SUCCESS");
		getOutputMsg().put("MSG", "删除成功");
		outPrint(response, JSONObject.fromObject(getOutputMsg()).toString());
	}
	
	protected abstract CoreEntity createNewEntity();

	/**
	 * 获取列表view
	 * @return
	 */
	protected abstract String getListView();
	
	/**
	 * 获取编辑界面view
	 * @return
	 */
	protected abstract String getEditView();
	
	/**
	 * mybatis查询列表数据的mapper
	 * @return
	 */
	protected abstract String getListMapper();
	
	/**
	 * 查询参数
	 * @return
	 */
	protected Map<String,Object> getListDataParam(){
		Map<String,String> param = getParamMap();
		Map<String,Object> params = new HashMap<String, Object>();
		Set<String> keys = param.keySet();
		for(String key : keys){
			params.put(key, param.get(key));
		}
		return params;
	}
	
	/**
	 * 获取Service
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	protected abstract BaseService getService();
	
	
	
	/**
	 * 获取数据列表
	 * @param mapper  执行的方法
	 * @param pagination 分页
	 * @param param  参数集合
	 * @return  数据结果集,包含SQL的执行时长和执行的SQL语句
	 */
	protected  Pagination<?>  getPaginationTime(String mapper,Pagination<?> pagination,Map<String, Object> param){
		Date begDate = new Date();
		pagination = queryExecutor.execQuery(mapper, pagination, param);
		Date endDate = new Date();
		pagination.setExceTime(DateUtil.getDiffTime(begDate, endDate));
//		pagination.setExceSql(getService().getExceSql(mapper,param));
		return pagination;
	}
}
