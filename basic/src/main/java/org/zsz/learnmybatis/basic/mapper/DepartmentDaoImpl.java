package org.zsz.learnmybatis.basic.mapper;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.zsz.learnmybatis.basic.entity.Department;

/**
 * @author Linus Zhang
 * @create 2022-11-17 22:56
 */
@RequiredArgsConstructor
public class DepartmentDaoImpl implements DepartmentDao {

  private final SqlSessionFactory sqlSessionFactory;

  @Override
  public List<Department> findAll() {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("departmentMapper.findAll");
    }
  }

  @Override
  public Department findById(String id) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectOne("departmentMapper.findById", id);
    }
  }

}
