package org.zsz.learnmybatis.mapper.application;

import java.io.IOException;
import java.io.InputStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.zsz.learnmybatis.mapper.entity.Department;

/**
 * @author Linus Zhang
 * @create 2022-12-05 23:34
 */
@Slf4j
public class GeneratedKeysApplication {
  public static void main(String[] args) throws IOException {
    InputStream xml = Resources.getResourceAsStream("mybatis-config.xml");
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(xml);
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      Department department = new Department();
      department.setName("hahaha");
      department.setTel("12345");
      sqlSession.insert("test.save", department);
      sqlSession.commit();

      log.info("dept -> {}", department);
    }
  }
}
