package org.zsz.learnmybatis.configuration.factory;

import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.zsz.learnmybatis.configuration.entity.User;

/**
 * @author Linus Zhang
 * @create 2022-11-21 23:24
 */
public class ExtendsObjectFactory extends DefaultObjectFactory {

  @Override
  public <T> T create(Class<T> type) {
    T t = super.create(type);
    if (User.class.equals(type)) {
      User user = (User) t;
      user.setAge(0);
    }
    return t;
  }
}
