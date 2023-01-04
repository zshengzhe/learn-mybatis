package org.zsz.learnmybatis.basic.entity;

import java.util.Set;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Department {

  private String id;

  private String name;

  private String tel;

  private Set<User> users;

}