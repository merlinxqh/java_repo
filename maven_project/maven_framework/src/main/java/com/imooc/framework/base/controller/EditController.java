package com.imooc.framework.base.controller;



import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;

import com.imooc.base.common.StringUtils;
import com.imooc.base.model.CoreEntity;
import com.imooc.base.service.BaseService;
import com.imooc.framework.utils.BeanUtils;

import net.sf.json.JSONObject;

/**
 * 编辑界面基类
 */
public abstract class EditController extends BaseController {
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="save")
	public void save(HttpServletResponse response) throws InstantiationException, IllegalAccessException{
		Object data = getSubmitEntity();
		if(validate(data)){
			if(data instanceof CoreEntity){
				if(StringUtils.isEmpty(((CoreEntity)data).getId())){
					getService().addEntity((CoreEntity)data);
				}else{
					getService().updateEntity((CoreEntity)data);
				}
				getOutputMsg().put("id", ((CoreEntity)data).getId());
				getOutputMsg().put("STATE", "SUCCESS");
				getOutputMsg().put("MSG", "保存成功");
			}
		}else{
			getOutputMsg().put("STATE", "FAIL");
		}
		outPrint(response, JSONObject.fromObject(getOutputMsg(), getDefaultJsonConfig()));
	}
	
	protected boolean validate(Object data) {
		return true;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected Object getSubmitEntity(){
		Class c = getSubmitClass();
		if(c==null){
			return null;
		}
		String id = getString("id");
		Object entity = null;
		if(!StringUtils.isEmpty(id)){
			entity = getService().getEntityById(id);
		}
		entity = BeanUtils.fillentity(getParamMap(),entity, c);
		return entity;
	}
	
	@SuppressWarnings("rawtypes")
	protected abstract Class getSubmitClass();

	/**
	 * 获取Service
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	protected abstract BaseService getService();
	
//	/**
//	 * 选择默认图片页面
//	 * @return
//	 */
//	@RequestMapping(value="chooseImage")
//	public String chooseImage(ModelMap modelMap){
//		String dir = getImagePath();
//		File path = new File(SystemConfig.getParameter("image_path"));
//		path = new File(path.getParentFile().getAbsolutePath()+"/default/"+dir);
//		List<Photo> photoList = new ArrayList<Photo>();
//		if(path.exists()){
//			for(File tmpFile : path.listFiles()){
//				if(tmpFile.isFile()){
//					Photo photo = new Photo();
//					photo.setId(UUID.randomUUID().toString());
//					photo.setPath(dir+"/"+tmpFile.getName());
//					photoList.add(photo);
//				}
//			}
//		}
//		modelMap.put("photoList", photoList);
//		return "common/chooseImage";
//	}
	
	/**
	 * 得到默认图片的路径
	 * @return
	 */
	protected String getImagePath(){
		return "";
	}
}
