package com.imooc.framework.base.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.imooc.base.dao.QueryExecutor;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

/**
 * 控制器基类
 * @author FengMy
 * @since 2012-6-26下午04:34:54
 */
public abstract class BaseController {
	private Logger log = Logger.getLogger(BaseController.class);
	private static ThreadLocal<Map<String,Object>> outPutMsg = new ThreadLocal<Map<String,Object>>();
	@Resource(name="queryExecutor")
	protected QueryExecutor queryExecutor;
	/**
	 * 获取request
	 * @return
	 */
	protected HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
	}

	/**
	 * 获取session
	 * @return
	 */
	protected HttpSession getSession() {
		return ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest().getSession();
	}

	protected ServletContext getServletContext() {
		return ContextLoader.getCurrentWebApplicationContext()
				.getServletContext();
	}
	
	
	public String getString(String name) {
		return getString(name, null);
	}

	public String getString(String name, String defaultValue) {
		String resultStr = getRequest().getParameter(name);
		if (resultStr == null || "".equals(resultStr)
				|| "null".equals(resultStr) || "undefined".equals(resultStr)) {
			return defaultValue;
		} else {
			return resultStr;
		}
	}
	
	public void outPrint(HttpServletResponse response, Object result) {
        try {
            response.setCharacterEncoding("utf-8");
            
            PrintWriter out = response.getWriter();
            out.print(result.toString());
        } catch (IOException e) {
        	log.error(e);
        }
    }
	
	/**
	 * json转换config
	 * @return
	 */
	protected JsonConfig getDefaultJsonConfig(){
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(Date.class, new JsonValueProcessor() {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			public Object processObjectValue(String key, Object value, JsonConfig cfg) {
				if(value!=null && value instanceof Date){
					return sdf.format(value);
				}
				return null;
			}
			
			public Object processArrayValue(Object value, JsonConfig cfg) {
				if(value!=null && value instanceof Date){
					return sdf.format(value);
				}
				return null;
			}
		});
		return config;
	}

	/**
	 * @return the outputMsg
	 */
	public Map<String,Object> getOutputMsg() {
		Map<String,Object> output = outPutMsg.get();
		if(output==null){
			output = new HashMap<String, Object>();
			outPutMsg.set(output);
		}
		return output;
	}
	
	protected void setOutputMsg(String key,String value){
		getOutputMsg().put(key, value);
	}
	
	protected Map<String,String> getParamMap(){
		Map<String,String> parameters = new HashMap<String, String>();
		Map map = getRequest().getParameterMap();
		Set keys = map.keySet();
		for(Object key : keys){
			parameters.put(key.toString(), getRequest().getParameter(key.toString()));
		}
		return parameters;
	}
	
	/**
	 * 将参数封装成Map<String,Object>空字符串转为null
	 * @return
	 * add by hlf 2013-2-10
	 */
	@SuppressWarnings("rawtypes")
	protected Map<String,Object> getParaMap(){
		Map<String,Object> parameters = new HashMap<String, Object>();
		Map map = getRequest().getParameterMap();
		Set keys = map.keySet();
		for(Object key : keys){
			Object o=getRequest().getParameter(key.toString());
			if(o instanceof String ){
				if("".equals(o)){
					o=null;
				}
			}
			parameters.put(key.toString(), o);
		}
		return parameters;
	}
	

	public int getInt(String name) {
		return getInt(name, 0);
	}

	public int getInt(String name, int defaultValue) {
		String resultStr = getRequest().getParameter(name);
		if (resultStr != null) {
			try {
				return Integer.parseInt(resultStr);
			} catch (Exception e) {
				return defaultValue;
			}
		}
		return defaultValue;
	}

	public BigDecimal getBigDecimal(String name) {
		return getBigDecimal(name, null);
	}
	
	public BigDecimal getBigDecimal(String name, BigDecimal defaultValue) {
		String resultStr = getRequest().getParameter(name);
		if (resultStr != null) {
			try {
				return BigDecimal.valueOf(Double.parseDouble(resultStr));
			} catch (Exception e) {
				return defaultValue;
			}
		}
		return defaultValue;
	}
	
	/**
	 * 判断当前人员是否有该权限项的权限
	 * @param permissionId 权限项的ID
	 * @return
	 */
	public boolean hasPermission(String permissionId){
		Map<String, Object> param = new HashMap<String, Object>();
//		param.put("person", SystemUtil.getCurrentUser().getId());
//		param.put("position", SystemUtil.getCurrentPosition().getId());
		param.put("permId", permissionId);
		int c = this.queryExecutor.execCount("com.merlin.basedata.permission.dao.PersonPermissionDao.getPersonPermission", param);
		if(c>0)return true;
		return false;
	}
	
	/**
	 * 得到IP地址
	 * @param request
	 * @return
	 */
	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
