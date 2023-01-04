package org.zsz.learnmybatis.configuration.mapper;

import java.util.List;
import org.zsz.learnmybatis.configuration.entity.Department;

/**
 * @author Linus Zhang
 * @create 2022-11-17 23:01
 */
public interface DepartmentMapper {

  List<Department> findAll();

  int insert(Department department);

  int update(Department department);

  int deleteById(String id);

  Department findById(String id);

}
