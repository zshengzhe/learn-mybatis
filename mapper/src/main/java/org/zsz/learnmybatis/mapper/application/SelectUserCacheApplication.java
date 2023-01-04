package org.zsz.learnmybatis.mapper.application;

import java.io.IOException;
import java.io.InputStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.zsz.learnmybatis.common.Printer;
import org.zsz.learnmybatis.mapper.entity.Department;
import org.zsz.learnmybatis.mapper.mapper.DepartmentMapper;
import org.zsz.learnmybatis.mapper.mapper.UserMapper;

/**
 * @author Linus Zhang
 * @create 2022-11-23 23:33
 */
@Slf4j
public class SelectUserCacheApplication {

  public static void main(String[] args) throws IOException {
    InputStream xml = Resources.getResourceAsStream("mybatis-config.xml");
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(xml);
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      // 连续查询两次同一个Department
      DepartmentMapper departmentMapper = sqlSession.getMapper(DepartmentMapper.class);
      Department department = departmentMapper.findById("18ec781fbefd727923b0d35740b177ab");
      Printer.print(department);
      Department department2 = departmentMapper.findById("18ec781fbefd727923b0d35740b177ab");
      log.info("department == department2 : {}", department == department2);
    }

    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      DepartmentMapper departmentMapper2 = sqlSession.getMapper(DepartmentMapper.class);
      // 再次查询Department
      Department department3 = departmentMapper2.findById("18ec781fbefd727923b0d35740b177ab");
      departmentMapper2.findAll();

      UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
      // 触发缓存清除
      userMapper.cleanCache();
     log.info("==================cleanCache====================");

      // 再再次查询Department
      Department department4 = departmentMapper2.findById("18ec781fbefd727923b0d35740b177ab");
      log.info("department3 == department4 : {}", department3 == department4);
    }
  }

}
