package org.zsz.learnmybatis.configuration.application;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.zsz.learnmybatis.configuration.entity.Department;
import org.zsz.learnmybatis.configuration.mapper.UserMapper;
import org.zsz.learnmybatis.configuration.entity.User;

/**
 * @author Linus Zhang
 * @create 2022-11-21 23:11
 */
public class DepartmentTypeHandlerApplication {

  public static void main(String[] args) throws IOException {
    InputStream xml = Resources.getResourceAsStream("mybatis-config.xml");
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(xml);
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

//      List<User> userList = userMapper.findAllUserTypeHandler();
//      Printer.print(userList);

      String userId = UUID.randomUUID().toString().replaceAll("-", "");
      User user = new User()
          .setId(userId)
          .setName("linus")
          .setDepartment(new Department()
              .setId("18ec781fbefd727923b0d35740b177ab"));

      userMapper.saveUser(user);

      // commit才能使数据库操作生效
      sqlSession.commit();
    }
  }

}
