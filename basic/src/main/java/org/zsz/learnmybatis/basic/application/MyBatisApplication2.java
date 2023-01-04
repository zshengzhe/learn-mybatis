package org.zsz.learnmybatis.basic.application;

import java.io.InputStream;
import java.util.List;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.zsz.learnmybatis.basic.entity.Department;
import org.zsz.learnmybatis.common.Printer;
import org.zsz.learnmybatis.basic.mapper.DepartmentDao;
import org.zsz.learnmybatis.basic.mapper.DepartmentDaoImpl;

/**
 * @author Linus Zhang
 * @create 2022-11-17 22:46
 */
public class MyBatisApplication2 {

  public static void main(String[] args) throws Exception {
    InputStream xml = Resources.getResourceAsStream("mybatis-config.xml");
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(xml);

    DepartmentDao departmentDao = new DepartmentDaoImpl(sqlSessionFactory);
    List<Department> departmentList = departmentDao.findAll();
    Printer.printCollection(departmentList);
  }

}