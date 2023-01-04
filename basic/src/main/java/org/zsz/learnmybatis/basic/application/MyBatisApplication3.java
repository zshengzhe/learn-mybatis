package org.zsz.learnmybatis.basic.application;

import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.zsz.learnmybatis.basic.entity.Department;
import org.zsz.learnmybatis.common.Printer;
import org.zsz.learnmybatis.basic.mapper.DepartmentMapper;

/**
 * @author Linus Zhang
 * @create 2022-11-17 23:02
 */
public class MyBatisApplication3 {

  public static void main(String[] args) throws Exception {
    InputStream xml = Resources.getResourceAsStream("mybatis-config.xml");
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(xml);
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      // 获取Mapper接口的代理
      DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
      Department department = departmentMapper.findById("18ec781fbefd727923b0d35740b177ab");
      Printer.print(department);
    }

  }

}
