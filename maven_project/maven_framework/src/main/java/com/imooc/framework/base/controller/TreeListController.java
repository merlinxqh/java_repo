package com.imooc.framework.base.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;

import com.imooc.base.common.Pagination;
import com.imooc.base.common.StringUtils;
import com.imooc.base.model.CoreEntity;
import com.imooc.base.model.TreeEntity;
import com.imooc.base.service.BaseService;

import net.sf.json.JSONArray;


/**
 */
public abstract class TreeListController extends ListController {
	
	/**
	 * 延迟加载方式获取树数据
	 * @param response
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="treeData")
	public void treeData(HttpServletResponse response){
		String parent = getString("parent");
		String includeChild = getString("includeChild");
		Map<String,Object> param = getTreeFilterParam();
		CoreEntity pare = null;
		if(!StringUtils.isEmpty(parent)){
			pare = getTreeService().getEntityById(parent);
		}
		if(pare!=null){
			if(!StringUtils.isEmpty(includeChild) && Boolean.getBoolean(includeChild)==true){
				param.put("includeChild", includeChild);
				param.put("longNumber", ((TreeEntity)pare).getLongNumber());
			}else{
				param.put("parent", pare.getId());
			}
		}
		Pagination<Object> page = new Pagination<Object>(Integer.MAX_VALUE,0);
		page = queryExecutor.execQuery(getTreeDataMapper(), page, param);
		String result = JSONArray.fromObject(page.getItems(), getDefaultJsonConfig()).toString();
		outPrint(response, result);
	}
	
	/**
	 * 树形数据
	 * @return
	 */
	protected abstract String getTreeDataMapper();
	
	@RequestMapping(value="simpleTreeData")
	public void simpleTreeData(HttpServletResponse response){
		@SuppressWarnings("rawtypes")
		List<Map> treeData = queryExecutor.execQuery(getSimpleTreeDataMapper(), getSimpleTreeDataFilter(), Map.class);
		outPrint(response, JSONArray.fromObject(treeData, getDefaultJsonConfig()).toString());
	}
	
	protected Map<String, Object> getSimpleTreeDataFilter() {
		return getParaMap();
	}

	/**
	 * 获取
	 * @return
	 */
	protected abstract String getSimpleTreeDataMapper();

	protected Map<String, Object> getTreeFilterParam() {
		return new HashMap<String, Object>();
	}
	
	/**
	 * 取树数据的service，默认为通用Service
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	protected BaseService getTreeService(){
		return getService();
	}
}
