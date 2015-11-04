package com.imooc.transaction.mybatis.tools;


import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.imooc.transaction.base.model.CoreEntity;
import com.imooc.transaction.model.Account;

/**
 * @author FengMy
 * 
 * @since 2012-10-18
 */
public class BuildMyBatisXMLAndSQLByClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Class c = Account.class;
		String tableName = "ACCOUNT";
		List<Field> fields = getFieldByClass(c, true);
		createTableSql(tableName, fields);
//		createInsertSql(c, tableName, fields);
//		createUpdateSql(c, tableName, fields);
//		createDeleteSql(tableName);
//		createGetById(c, tableName, fields);
	}

	private static void createGetById(Class c, String tableName,
			List<Field> fields) {
		StringBuilder sql = new StringBuilder();
		sql.append("<select id=\"getById")
				.append("\" parameterType=\"string\" resultType=\"")
				.append(c.getName()).append("\">\n");
		sql.append("\tSELECT \n");
		for (Field f : fields) {
			sql.append("\t\tD.").append(f.getColumn()).append(" AS \"")
					.append(f.getField()).append("\",\n");
		}
		sql.deleteCharAt(sql.lastIndexOf(","));
		sql.append("\tFROM ").append(tableName).append(" D\n");
		sql.append("\tWHERE D.FID = #{id}\n");
		sql.append("</select>");
		System.out.println(sql.toString());
	}

	private static void createDeleteSql(String tableName) {
		StringBuilder sql = new StringBuilder();
		sql.append("<delete id=\"delete").append(
				"\" parameterType=\"string\" >\n");
		sql.append("\tDELETE FROM ").append(tableName)
				.append(" WHERE FID = #{id}\n");
		sql.append("</delete>");
		System.out.println(sql.toString());
	}

	private static void createInsertSql(Class c, String tableName,
			List<Field> fields) {
		StringBuilder sql = new StringBuilder();
		sql.append("<insert id=\"insert").append("\" parameterType=\"")
				.append(c.getName()).append("\" >\n");
		sql.append("\tINSERT INTO ").append(tableName).append("\n");
		sql.append("\t(\n");
		for (Field f : fields) {
			sql.append("\t\t").append(f.getColumn()).append(",\n");
		}
		sql.deleteCharAt(sql.lastIndexOf(","));
		sql.append("\t) VALUES\n\t(\n");
		for (Field f : fields) {
			sql.append("\t\t").append("#{").append(f.getField())
					.append(",jdbcType=").append(f.getType()).append("},\n");
		}
		sql.deleteCharAt(sql.lastIndexOf(","));
		sql.append("\t)\n");
		sql.append("</insert>");
		System.out.println(sql.toString());
	}

	private static void createUpdateSql(Class c, String tableName,
			List<Field> fields) {
		StringBuilder sql = new StringBuilder();
		sql.append("<update id=\"update").append("\" parameterType=\"")
				.append(c.getName()).append("\" >\n");
		sql.append("\tUPDATE ").append(tableName).append("\n");
		sql.append("\t<set>\n");
		for (Field f : fields) {
			sql.append("\t\t").append(f.getColumn()).append(" = ").append("#{")
					.append(f.getField()).append(",jdbcType=")
					.append(f.getType()).append("},\n");
		}
		sql.deleteCharAt(sql.lastIndexOf(","));
		sql.append("\t</set>\n");
		sql.append("\tWHERE FID = #{id}\n");
		sql.append("</update>");
		System.out.println(sql.toString());
	}

	private static void createTableSql(String tableName, List<Field> fields) {
		StringBuilder sql = new StringBuilder();
		sql.append("CREATE TABLE ").append(tableName).append("(\n");
		for (Field f : fields) {
			String type = f.getType();
			if ("VARCHAR".endsWith(type)) {
				if (f.getColumn().startsWith("FK")
						&& f.getColumn().endsWith("ID")) {
					type = "VARCHAR(44)";
				} else {
					type = "VARCHAR(50)";
				}
			} else if ("NUMERIC".equals(type)) {
				type = "NUMERIC(11,2)";
			}
			sql.append("\t").append(f.getColumn()).append(" ").append(type)
					.append(",\n");
		}
		sql.append("\t").append("PRIMARY KEY(FID)\n").append(");\n");
		System.out.println(sql.toString());
	}

	private static List<Field> getFieldByClass(Class c, boolean includeSuper) {
		List<Field> fields = new ArrayList<BuildMyBatisXMLAndSQLByClass.Field>();
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
				fi.setType("INT");
			} else if (int.class.isAssignableFrom(type)
					|| Integer.class.isAssignableFrom(type)) {
				fi.setType("INT");
			} else if (long.class.isAssignableFrom(type)
					|| Long.class.isAssignableFrom(type)) {
				fi.setType("INT");
			} else if (short.class.isAssignableFrom(type)
					|| Short.class.isAssignableFrom(type)) {
				fi.setType("INT");
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