package org.apache.ibatis.type;

import org.apache.ibatis.aaatest.entry.House;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExampleTypeHandler extends BaseTypeHandler<House> {

  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, House parameter, JdbcType jdbcType) throws SQLException {

  }

  @Override
  public House getNullableResult(ResultSet rs, String columnName) throws SQLException {
    return new House();
  }

  @Override
  public House getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
    return new House();
  }

  @Override
  public House getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
    return new House();
  }
}
