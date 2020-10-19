package org.apache.ibatis.aaatest;


import org.apache.ibatis.aaatest.entry.HousedelBasic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HousedelBasicMapper {

    HousedelBasic selectHousedelBasicInfo(@Param("housedelCode")Long housedelCode);

    List<HousedelBasic> selectBiggerThanCode(@Param("housedelCode")Long housedelCode, @Param("delTypeList")List<Integer> delTypeList);

    List<HousedelBasic> selectByBuildAreaAndOrientation(@Param("orientation")String orientation, @Param(value = "hb")HousedelBasic housedelBasic);

    List<HousedelBasic> selectByBuildAreaOrOrientation(@Param("orientation")String orientation, @Param(value = "hb")HousedelBasic housedelBasic);

    Integer updateByBuildAreaOrOrientation(@Param("orientation")String orientation, @Param(value = "hb")HousedelBasic housedelBasic);

}
