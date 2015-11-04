package com.imooc.annotation;

@Table("user")
public class Filter {
	
   @Column("id")
   private String id;
   
   @Column("user_name")
   private String usreName;
   
   @Column("mobile")
   private String mobile;
   
   @Column("age")
   private int age;
   
   @Column("gender")
   private String gender;
   
   @Column("nick_name")
   private String nickName;

public String getId() {
	return id;
}

public void setId(String id) {
	this.id = id;
}

public String getUsreName() {
	return usreName;
}

public void setUsreName(String usreName) {
	this.usreName = usreName;
}

public String getMobile() {
	return mobile;
}

public void setMobile(String mobile) {
	this.mobile = mobile;
}

public int getAge() {
	return age;
}

public void setAge(int age) {
	this.age = age;
}

public String getGender() {
	return gender;
}

public void setGender(String gender) {
	this.gender = gender;
}

public String getNickName() {
	return nickName;
}

public void setNickName(String nickName) {
	this.nickName = nickName;
}
   
   
   
}
