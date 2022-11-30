package com.cross.Danmat.User.Dao;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.cross.Danmat.Config.DataSourceConfig;


public class UserDaoTest {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfig.class);
		UserDao dao = context.getBean("userDao", UserDao.class);
		
		dao.findPassword("donoun3", "donoun3@naver.com");
		context.close();
	}
}
