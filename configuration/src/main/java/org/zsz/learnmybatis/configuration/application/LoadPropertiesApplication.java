package org.zsz.learnmybatis.configuration.application;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.zsz.learnmybatis.configuration.entity.Department;
import org.zsz.learnmybatis.configuration.mapper.DepartmentMapper;
import org.zsz.learnmybatis.common.Printer;

/**
 * @author Linus Zhang
 * @create 2022-11-21 22:37
 */
public class LoadPropertiesApplication {

  public static void main(String[] args) throws IOException {
    InputStream xml = Resources.getResourceAsStream("mybatis-config.xml");
    Properties properties = new Properties();
    properties.load(Resources.getResourceAsStream("jdbc.properties"));

    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(xml, properties);
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
      Department department = departmentMapper.findById("18ec781fbefd727923b0d35740b177ab");
      Printer.print(department);
      Printer.printCollection(department.getUsers());
    }
  }

}
