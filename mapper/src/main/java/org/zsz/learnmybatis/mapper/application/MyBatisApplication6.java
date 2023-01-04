package org.zsz.learnmybatis.mapper.application;

import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.zsz.learnmybatis.common.Printer;
import org.zsz.learnmybatis.mapper.entity.Department;
import org.zsz.learnmybatis.mapper.mapper.DepartmentMapper;

/**
 * @author Linus Zhang
 * @create 2022-11-17 23:21
 */
public class MyBatisApplication6 {

  public static void main(String[] args) throws Exception {
    InputStream xml = Resources.getResourceAsStream("mybatis-config.xml");
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(xml);
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
      Department department = departmentMapper.findById("18ec781fbefd727923b0d35740b177ab");
      Printer.print(department);
      Printer.printCollection(department.getUsers());
    }
  }

}
