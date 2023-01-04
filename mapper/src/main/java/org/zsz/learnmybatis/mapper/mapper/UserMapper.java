package org.zsz.learnmybatis.mapper.mapper;

import java.util.List;
import org.zsz.learnmybatis.mapper.entity.User;

/**
 * @author Linus Zhang
 * @create 2022-11-17 23:10
 */
public interface UserMapper {

  List<User> findAll();

  List<User> findAllLazy();

  List<User> findAllByDepartmentId();

  int saveUser(User user);

  int cleanCache();

}
