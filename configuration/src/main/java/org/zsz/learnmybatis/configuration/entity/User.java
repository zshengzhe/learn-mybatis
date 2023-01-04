package org.zsz.learnmybatis.configuration.entity;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Linus Zhang
 * @create 2022-11-17 23:06
 */
@Data
@Accessors(chain = true)
public class User {

  private String id;

  private String name;

  private Integer age;

  private LocalDateTime birthday;

  private Department department;

//  private Department department_id;

}
