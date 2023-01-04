package org.zsz.learnmybatis.basic.mapper;

import java.util.List;
import org.zsz.learnmybatis.basic.entity.Department;

/**
 * @author Linus Zhang
 * @create 2022-11-17 22:54
 */
public interface DepartmentDao {

  List<Department> findAll();

  Department findById(String id);

}
