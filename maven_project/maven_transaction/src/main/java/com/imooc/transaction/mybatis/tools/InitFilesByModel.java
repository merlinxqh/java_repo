package com.imooc.transaction.mybatis.tools;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.imooc.transaction.base.model.CoreEntity;
import com.imooc.transaction.base.model.DataEntity;
import com.imooc.transaction.base.service.impl.BaseServiceImpl;
import com.imooc.transaction.base.service.impl.DataEntityService;
import com.imooc.transaction.model.Account;

@SuppressWarnings({ "unchecked", "rawtypes","unused" })
public class InitFilesByModel {
	public static void main(String[] args) throws IOException {
		String workSpacePath = "E:\\workspace";
		String project = "transaction";
		Class c = Account.class;//NewPerson.class;
		String tableName = "ACCOUNT";
		createMyBatisXML(workSpacePath, project, c, tableName);
		createDao(workSpacePath, project, c);
		createService(workSpacePath, project, c);
	}
	
	private static <T extends CoreEntity> void createController(String workSpacePath,String project,Class<T> c) throws IOException{
		String dir = workSpacePath + "/" + project + "-web/" + "src/main/java/";
		String pg = getModuleName(c) + "." + "dao";
		dir += getModuleName(c).replaceAll("\\.", "/") + "/dao/";
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String javaName = c.getSimpleName() + "Dao";
		File javaFile = new File(dir + javaName + ".java");
		if(javaFile.exists()){
			System.out.println("dao己存在");
			return ;
		}
		File dirFile = new File(dir);
		if(!dirFile.exists()){
			dirFile.mkdirs();
		}
		String tmpString = getTmpFileDesc("dao.java.temp");
		if(tmpString!=null && !"".equals(tmpString)){
			String nameSpace = getModuleName(c) + ".dao." + c.getSimpleName()+"Dao";
			tmpString = tmpString.replaceAll("\\$\\{package\\}", pg);
			tmpString = tmpString.replaceAll("\\$\\{date\\}", date);
			tmpString = tmpString.replaceAll("\\$\\{javaName\\}", javaName);
		}
		FileWriter fw = new FileWriter(javaFile);
		fw.write(tmpString);
		fw.close();
		
		String superPackage = pg;
		String superName = javaName;
		String component = javaName.substring(0, 1).toLowerCase() + javaName.substring(1);
		pg = getModuleName(c) + ".dao.impl";
		dir = workSpacePath + "/" + project + "/" + "src/main/java/";
		dir += pg.replaceAll("\\.", "/") + "/";
		javaName = c.getSimpleName() + "DaoImpl";
		
		javaFile = new File(dir + javaName + ".java");
		if(javaFile.exists()){
			System.out.println("daoImpl己存在");
			return ;
		}
		dirFile = new File(dir);
		if(!dirFile.exists()){
			dirFile.mkdirs();
		}
		tmpString = getTmpFileDesc("daoImpl.java.temp");
		if(tmpString!=null && !"".equals(tmpString)){
			String nameSpace = getModuleName(c) + ".dao." + c.getSimpleName()+"Dao";
			tmpString = tmpString.replaceAll("\\$\\{package\\}", pg);
			tmpString = tmpString.replaceAll("\\$\\{date\\}", date);
			tmpString = tmpString.replaceAll("\\$\\{javaName\\}", javaName);
			
			tmpString = tmpString.replaceAll("\\$\\{super\\}", superName);
			tmpString = tmpString.replaceAll("\\$\\{superPackage\\}", superPackage);
			tmpString = tmpString.replaceAll("\\$\\{component\\}", component);
			tmpString = tmpString.replaceAll("\\$\\{nameSpace\\}", nameSpace);
		}
		fw = new FileWriter(javaFile);
		fw.write(tmpString);
		fw.close();
	}
	
	private static <T extends CoreEntity> void createService(String workSpacePath,String project,Class<T> c) throws IOException{
		String dir = workSpacePath + "/" + project + "/" + "src/main/java/";
		String pg = getModuleName(c) + "." + "service";
		dir += getModuleName(c).replaceAll("\\.", "/") + "/service/";
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String javaName = c.getSimpleName() + "Service";
		File javaFile = new File(dir + javaName + ".java");
		if(javaFile.exists()){
			System.out.println("service己存在");
			return ;
		}
		File dirFile = new File(dir);
		if(!dirFile.exists()){
			dirFile.mkdirs();
		}
		String tmpString = getTmpFileDesc("service.java.temp");
		if(tmpString!=null && !"".equals(tmpString)){
			tmpString = tmpString.replaceAll("\\$\\{package\\}", pg);
			tmpString = tmpString.replaceAll("\\$\\{date\\}", date);
			tmpString = tmpString.replaceAll("\\$\\{javaName\\}", javaName);
			tmpString = tmpString.replaceAll("\\$\\{entity\\}", c.getName());
			tmpString = tmpString.replaceAll("\\$\\{entityName\\}", c.getSimpleName());
			tmpString = tmpString.replaceAll("\\$\\{javaName\\}", c.getSimpleName());
		}
		FileWriter fw = new FileWriter(javaFile);
		fw.write(tmpString);
		fw.close();
		String superPackage = pg;
		String superName = javaName;
		String component = javaName.substring(0, 1).toLowerCase() + javaName.substring(1);
		pg = getModuleName(c) + ".service.impl";
		dir = workSpacePath + "/" + project + "/" + "src/main/java/";
		dir += pg.replaceAll("\\.", "/") + "/";
		javaName = c.getSimpleName() + "ServiceImpl";
		javaFile = new File(dir + javaName + ".java");
		if(javaFile.exists()){
			System.out.println("serviceImpl己存在");
			return ;
		}
		dirFile = new File(dir);
		if(!dirFile.exists()){
			dirFile.mkdirs();
		}
		String daopg = getModuleName(c) + "." + "dao";
		String daojavaName = c.getSimpleName() + "Dao";
		tmpString = getTmpFileDesc("serviceImpl.java.temp");
		if(tmpString!=null && !"".equals(tmpString)){
			tmpString = tmpString.replaceAll("\\$\\{package\\}", pg);
			tmpString = tmpString.replaceAll("\\$\\{date\\}", date);
			tmpString = tmpString.replaceAll("\\$\\{javaName\\}", javaName);
			tmpString = tmpString.replaceAll("\\$\\{super\\}", superName);
			tmpString = tmpString.replaceAll("\\$\\{superPackage\\}", superPackage);
			tmpString = tmpString.replaceAll("\\$\\{component\\}", component);
			tmpString = tmpString.replaceAll("\\$\\{baseService\\}", DataEntity.class.isAssignableFrom(c)?DataEntityService.class.getName():BaseServiceImpl.class.getName());
			tmpString = tmpString.replaceAll("\\$\\{baseServiceName\\}", DataEntity.class.isAssignableFrom(c)?DataEntityService.class.getSimpleName():BaseServiceImpl.class.getSimpleName());
			tmpString = tmpString.replaceAll("\\$\\{entity\\}", c.getName());
			tmpString = tmpString.replaceAll("\\$\\{entityName\\}", c.getSimpleName());
			tmpString = tmpString.replaceAll("\\$\\{dao\\}", daopg + "." + daojavaName);
			tmpString = tmpString.replaceAll("\\$\\{daoName\\}", daojavaName);
			tmpString = tmpString.replaceAll("\\$\\{lowDaoName\\}", daojavaName.substring(0, 1).toLowerCase() + daojavaName.substring(1));
		}
		fw = new FileWriter(javaFile);
		fw.write(tmpString);
		fw.close();
	}
	
	private static <T extends CoreEntity> void createDao(String workSpacePath,String project,Class<T> c) throws IOException{
		String dir = workSpacePath + "/" + project + "/" + "src/main/java/";
		String pg = getModuleName(c) + "." + "dao";
		dir += getModuleName(c).replaceAll("\\.", "/") + "/dao/";
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String javaName = c.getSimpleName() + "Dao";
		File javaFile = new File(dir + javaName + ".java");
		if(javaFile.exists()){
			System.out.println("dao己存在");
			return ;
		}
		File dirFile = new File(dir);
		if(!dirFile.exists()){
			dirFile.mkdirs();
		}
		String tmpString = getTmpFileDesc("dao.java.temp");
		if(tmpString!=null && !"".equals(tmpString)){
			String nameSpace = getModuleName(c) + ".dao." + c.getSimpleName()+"Dao";
			tmpString = tmpString.replaceAll("\\$\\{package\\}", pg);
			tmpString = tmpString.replaceAll("\\$\\{date\\}", date);
			tmpString = tmpString.replaceAll("\\$\\{javaName\\}", javaName);
		}
		FileWriter fw = new FileWriter(javaFile);
		fw.write(tmpString);
		fw.close();
		
		String superPackage = pg;
		String superName = javaName;
		String component = javaName.substring(0, 1).toLowerCase() + javaName.substring(1);
		pg = getModuleName(c) + ".dao.impl";
		dir = workSpacePath + "/" + project + "/" + "src/main/java/";
		dir += pg.replaceAll("\\.", "/") + "/";
		javaName = c.getSimpleName() + "DaoImpl";
		
		javaFile = new File(dir + javaName + ".java");
		if(javaFile.exists()){
			System.out.println("daoImpl己存在");
			return ;
		}
		dirFile = new File(dir);
		if(!dirFile.exists()){
			dirFile.mkdirs();
		}
		tmpString = getTmpFileDesc("daoImpl.java.temp");
		if(tmpString!=null && !"".equals(tmpString)){
			String nameSpace = getModuleName(c) + ".dao." + c.getSimpleName()+"Dao";
			tmpString = tmpString.replaceAll("\\$\\{package\\}", pg);
			tmpString = tmpString.replaceAll("\\$\\{date\\}", date);
			tmpString = tmpString.replaceAll("\\$\\{javaName\\}", javaName);
			
			tmpString = tmpString.replaceAll("\\$\\{super\\}", superName);
			tmpString = tmpString.replaceAll("\\$\\{superPackage\\}", superPackage);
			tmpString = tmpString.replaceAll("\\$\\{component\\}", component);
			tmpString = tmpString.replaceAll("\\$\\{nameSpace\\}", nameSpace);
		}
		fw = new FileWriter(javaFile);
		fw.write(tmpString);
		fw.close();
		
	}
	
	private static <T extends CoreEntity> void createMyBatisXML(String workSpacePath,String project,Class<T> c,String tableName) throws IOException{
		String className = c.getName();
		String dir = workSpacePath + "/" + project + "/" + "src/main/resources/";
		File fileDir = new File(dir);
		dir += getModuleName(c).replaceAll("\\.", "/");
		if(!fileDir.exists()){
			fileDir.mkdirs();
		}
		String fileName = c.getSimpleName() + "Dao.xml";
		File file = new File(dir + "/" + fileName);
		if(file.exists()){
			System.out.println("xml文件己存在");
			return;
		}
		String tmpString = getTmpFileDesc("mybatis.xml.temp");
		if(tmpString!=null && !"".equals(tmpString)){
			String nameSpace = getModuleName(c) + ".dao." + c.getSimpleName()+"Dao";
			List<Field> fields = getFieldByClass(c, true);
			String insertSQL = createInsertSql(c, tableName, fields);
			String updateSQL = createUpdateSql(c, tableName, fields);
			String deleteSQL = createDeleteSql(tableName);
			String getByIdSQL = createGetById(c, tableName, fields);
			String selectSQL = getSelectSql(c, tableName, fields);
			tmpString = tmpString.replaceAll("\\$\\{nameSpace\\}", nameSpace);
			tmpString = tmpString.replaceAll("\\$\\{className\\}", className);
			tmpString = tmpString.replaceAll("\\$\\{insertSQL\\}", insertSQL);
			tmpString = tmpString.replaceAll("\\$\\{updateSQL\\}", updateSQL);
			tmpString = tmpString.replaceAll("\\$\\{deleteSQL\\}", deleteSQL);
			tmpString = tmpString.replaceAll("\\$\\{getByIdSQL\\}", getByIdSQL);
			tmpString = tmpString.replaceAll("\\$\\{selectSQL\\}", selectSQL);
			tmpString = tmpString.replaceAll("\\$\\{selectCountSQL\\}", getSelectCountSql(c, tableName, fields));
		}
		FileWriter fw = new FileWriter(file);
		fw.write(tmpString);
		fw.close();
	}
	
	private static String getTmpFileDesc(String tmpFileName) throws IOException{
		InputStream is = InitFilesByModel.class.getResourceAsStream(tmpFileName);
		BufferedInputStream bis = new BufferedInputStream(is);
		byte[] bytes = new byte[bis.available()];
		bis.read(bytes, 0, bis.available());
		return new String(bytes);
	}
	
	private static <T extends CoreEntity> String getModuleName(Class<T> c){
		//com.merlin.basedata.images.model.Photo
		String className = c.getName();
		className = className.substring(0, className.lastIndexOf("."));
		className = className.substring(0, className.lastIndexOf("."));
		return className;
	}
	
	private static String createGetById(Class c, String tableName,
			List<Field> fields) {
		StringBuilder sql = new StringBuilder(getSelectSql(c, tableName, fields));
		sql.append("\n\t\tWHERE D.FID = #{id}");
		return (sql.toString());
	}
	
	private static String getSelectSql(Class c, String tableName,
			List<Field> fields) {
		StringBuilder sql = new StringBuilder();
		sql.append("\tSELECT \n");
		for (Field f : fields) {
			sql.append("\t\t\tD.").append(f.getColumn()).append(" AS \"")
					.append(f.getField()).append("\",\n");
		}
		sql.deleteCharAt(sql.lastIndexOf(","));
		sql.append("\t\tFROM ").append(tableName).append(" D");
		return (sql.toString());
	}
	
	
	private static String getSelectCountSql(Class c, String tableName,
			List<Field> fields){
		StringBuilder sql = new StringBuilder();
		sql.append("\tSELECT \n");
		sql.append("\t\t\tCOUNT(D.FID)").append("\n");
		sql.append("\t\tFROM ").append(tableName).append(" D");
		return (sql.toString());
	}

	private static String createDeleteSql(String tableName) {
		StringBuilder sql = new StringBuilder();
		sql.append("\tDELETE FROM ").append(tableName)
				.append(" WHERE FID = #{id}");
		return (sql.toString());
	}

	private static String createInsertSql(Class c, String tableName,
			List<Field> fields) {
		StringBuilder sql = new StringBuilder();
		sql.append("\tINSERT INTO ").append(tableName).append("\n");
		sql.append("\t\t(\n");
		for (Field f : fields) {
			sql.append("\t\t\t").append(f.getColumn()).append(",\n");
		}
		sql.deleteCharAt(sql.lastIndexOf(","));
		sql.append("\t\t) VALUES\n\t\t(\n");
		for (Field f : fields) {
			sql.append("\t\t\t").append("#{").append(f.getField())
					.append(",jdbcType=").append(f.getType()).append("},\n");
		}
		sql.deleteCharAt(sql.lastIndexOf(","));
		sql.append("\t\t)");
		return (sql.toString());
	}

	private static String createUpdateSql(Class c, String tableName,
			List<Field> fields) {
		StringBuilder sql = new StringBuilder();
		sql.append("\tUPDATE ").append(tableName).append("\n");
		sql.append("\t\t<set>\n");
		for (Field f : fields) {
			sql.append("\t\t\t").append(f.getColumn()).append(" = ").append("#{")
					.append(f.getField()).append(",jdbcType=")
					.append(f.getType()).append("},\n");
		}
		sql.deleteCharAt(sql.lastIndexOf(","));
		sql.append("\t\t</set>\n");
		sql.append("\t\tWHERE FID = #{id}");
		return (sql.toString());
	}

	private static List<Field> getFieldByClass(Class c, boolean includeSuper) {
		List<Field> fields = new ArrayList<InitFilesByModel.Field>();
		Class sc = c.getSuperclass();
		if (includeSuper) {
			if (CoreEntity.class.isAssignableFrom(sc)) {
				fields.addAll(getFieldByClass(sc, includeSuper));
			}
		}
		java.lang.reflect.Field[] fs = c.getDeclaredFields();
		for (java.lang.reflect.Field f : fs) {
			if (Modifier.isPrivate(f.getModifiers())
					&& !Modifier.isStatic(f.getModifiers())) {
				Field fi = initField(f);
				if (fi != null) {
					fields.add(fi);
				}
			}
		}
		return fields;
	}

	private static Field initField(java.lang.reflect.Field field) {
		Field fi = null;
		if (CoreEntity.class.isAssignableFrom(field.getType())) {
			fi = new Field();
			fi.setField(field.getName() + ".id");
			fi.setColumn("FK" + field.getName().toUpperCase() + "ID");
			fi.setType("VARCHAR");
		} else if (field.getType().isAssignableFrom(Collection.class)
				|| field.getType().isAssignableFrom(Map.class)) {
			// 集合类
			return null;
		} else {
			fi = new Field();
			fi.setField(field.getName());
			fi.setColumn("F" + field.getName().toUpperCase());
			Class type = field.getType();
			if (CoreEntity.class.isAssignableFrom(type)) {
				// 外键
				fi.setType("VARCHAR");
			} else if (Byte.class.isAssignableFrom(type)
					|| byte.class.isAssignableFrom(type)) {
				fi.setType("INTEGER");
			} else if (int.class.isAssignableFrom(type)
					|| Integer.class.isAssignableFrom(type)) {
				fi.setType("INTEGER");
			} else if (long.class.isAssignableFrom(type)
					|| Long.class.isAssignableFrom(type)) {
				fi.setType("INTEGER");
			} else if (short.class.isAssignableFrom(type)
					|| Short.class.isAssignableFrom(type)) {
				fi.setType("INTEGER");
			} else if (float.class.isAssignableFrom(type)
					|| Float.class.isAssignableFrom(type)) {
				fi.setType("FLOAT");
			} else if (double.class.isAssignableFrom(type)
					|| Double.class.isAssignableFrom(type)) {
				fi.setType("FLOAT");
			} else if (BigDecimal.class.isAssignableFrom(type)) {
				fi.setType("NUMERIC");
			} else if (boolean.class.isAssignableFrom(type)
					|| Boolean.class.isAssignableFrom(type)) {
				fi.setType("BOOLEAN");
			} else if (Date.class.isAssignableFrom(type)) {
				fi.setType("TIMESTAMP");
			} else if (type.isEnum()) {
				fi.setType("VARCHAR");
			} else if (Collection.class.isAssignableFrom(type)
					|| Map.class.isAssignableFrom(type) || type.isArray()) {
				// 集合或者数组
				return null;
			} else {
				fi.setType("VARCHAR");
			}
		}
		return fi;
	}

	public static class Field {
		private String field;
		private String column;
		private String type;

		public String getField() {
			return field;
		}

		public void setField(String field) {
			this.field = field;
		}

		public String getColumn() {
			return column;
		}

		public void setColumn(String column) {
			this.column = column;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

	}
}
