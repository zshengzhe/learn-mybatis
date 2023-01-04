package org.zsz.learnmybatis.mapper.application;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.zsz.learnmybatis.common.Printer;
import org.zsz.learnmybatis.mapper.entity.Department;
import org.zsz.learnmybatis.mapper.mapper.DepartmentMapper;

/**
 * @author Linus Zhang
 * @create 2022-12-05 23:43
 */
public class ResultMapApplication {

  public static void main(String[] args) throws IOException {
    InputStream xml = Resources.getResourceAsStream("mybatis-config.xml");
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(xml);
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
      final List<Department> depts = departmentMapper.findAll();
      Printer.printCollection(depts);
    }
  }

}
