package org.zsz.learnmybatis.basic.application;

import java.io.InputStream;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.zsz.learnmybatis.basic.entity.Department;
import org.zsz.learnmybatis.common.Printer;

/**
 * @author Linus Zhang
 * @create 2022-11-17 22:46
 */
public class MyBatisApplication1 {

  public static void main(String[] args) throws Exception {
    InputStream xml = Resources.getResourceAsStream("mybatis-config.xml");
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(xml);
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      List<Department> departmentList = sqlSession.selectList("departmentMapper.findAll");
      Printer.printCollection(departmentList);
    }
  }

}
