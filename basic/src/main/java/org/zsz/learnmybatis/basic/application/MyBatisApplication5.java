package org.zsz.learnmybatis.basic.application;

import java.io.InputStream;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.zsz.learnmybatis.basic.entity.User;
import org.zsz.learnmybatis.basic.mapper.UserMapper;
import org.zsz.learnmybatis.common.Printer;

/**
 * @author Linus Zhang
 * @create 2022-11-17 23:14
 */
public class MyBatisApplication5 {

  public static void main(String[] args) throws Exception {
    InputStream xml = Resources.getResourceAsStream("mybatis-config.xml");
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(xml);
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
      List<User> userList = userMapper.findAllLazy();
      Printer.print(userList);
    }
  }
}
