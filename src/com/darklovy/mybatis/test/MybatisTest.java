package com.darklovy.mybatis.test;

import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import com.darklovy.mybatis.bean.Employee;
import com.darklovy.mybatis.dao.EmployeeMapper;

public class MybatisTest {

	/**
	 * 1. 根據 xml 配置文件（全局配置文件）创建一个 SQLSessionFactory 对象 2. 创建 SQLSession 对象 3. 执行操作
	 * 4. 关闭 SQLSession 对象
	 * 
	 * @throws Exception
	 */
	@Test
	public void test() throws Exception {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession openSession = sqlSessionFactory.openSession();

		try {
			// 获取接口的实现类对象
			EmployeeMapper employeeMapper = openSession.getMapper(EmployeeMapper.class);
			Employee employee = employeeMapper.getEmpById(1);
			System.out.println(employee);
		} finally {
			openSession.close();
		}
	}
}
