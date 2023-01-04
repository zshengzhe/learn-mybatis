package org.zsz.learnmybatis.basic.mapper;

import java.util.List;
import org.zsz.learnmybatis.basic.entity.User;

/**
 * @author Linus Zhang
 * @create 2022-11-17 23:10
 */
public interface UserMapper {

  List<User> findAll();

  List<User> findAllLazy();

  List<User> findAllByDepartmentId();

}
