package org.apache.ibatis.aayyn;


import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HouseMapper {

    House selectHouse(@Param("id") Long id);

    Integer batchInsert(@Param("houseList") List<House> houseList);

}
