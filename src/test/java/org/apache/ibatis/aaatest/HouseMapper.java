package org.apache.ibatis.aaatest;


import org.apache.ibatis.aaatest.entry.House;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HouseMapper {

    House selectHouse(@Param("id") Long id);

    Integer batchInsert(@Param("houseList") List<House> houseList);

    //jdk8之后 接口可以有方法的实现
    default void defaultMethod() {
      System.out.println("我是个默认方法");
    }

}
