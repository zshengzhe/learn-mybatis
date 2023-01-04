package org.zsz.learnmybatis.mapper.entity;

import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class Department {

  private String id;

  private String name;

  private String tel;

  private Set<User> users;

  public Department(String id) {
    this.id = id;
  }
}