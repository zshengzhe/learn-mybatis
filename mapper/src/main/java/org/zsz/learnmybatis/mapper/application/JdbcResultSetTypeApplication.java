package org.zsz.learnmybatis.mapper.application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Linus Zhang
 * @create 2022-12-05 23:31
 */
@Slf4j
public class JdbcResultSetTypeApplication {

  public static void main(String[] args) throws SQLException {
    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis?useSSL=false&characterEncoding=utf-8", "root",
        "root");
        PreparedStatement ps = connection.prepareStatement("select * from tbl_department")) {

      // 在PreparedStatement上设置一次性抓取的结果行数
      ResultSet resultSet = ps.executeQuery();
      // 遍历游标向下迭代
      while (resultSet.next()) {
        log.info("正序 --- {}", resultSet.getString("name"));
      }

      // 遍历游标向上迭代
      while (resultSet.previous()) {
        log.info("倒序 --- {}", resultSet.getString("name"));
      }
      resultSet.close();
    }
  }

}
