package org.apache.ibatis.aaatest;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.aaatest.entry.House;
import org.apache.ibatis.aaatest.entry.HousedelBasic;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/** 源码包结构：
 ├─annotations     注解相关 比如select insert
 ├─binding         mapper相关                           done
 ├─builder         解析xml相关                           done
 ├─cache           缓存
 ├─cursor          返回结果resultset
 ├─datasource      数据管理
 ├─exceptions      异常
 ├─executor        执行器                                done
 ├─io              classloader
 ├─jdbc            jdbc
 ├─lang            jdk7 jdk8
 ├─logging         日志相关
 ├─mapping         mapper相关的封装
 ├─parsing         xml相关解析                          doing
 ├─plugin          拦截器                               done
 ├─reflection      反射相关
 ├─scripting       数据厂家                             doing
 ├─session         session                             done
 ├─transaction     事务                                doing
 └─type            返回类型对应
 */
@Slf4j
public class Test {
  public static void main(String[] args) throws Exception {
    String resource = "org/apache/ibatis/aaatest/mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

    try (SqlSession session = sqlSessionFactory.openSession()) {

//      -------------------------------house表------------------------------
//      //不需要mapper类，直接读取mapper.xml
//      House house1 = session.selectOne("org.apache.ibatis.aayyn.HouseMapper.selectHouse", 8052566);

      //获取mapper类，实际上是获取的代理类，代理类自动读取了mapper.xml
      HouseMapper houseMapper = session.getMapper(HouseMapper.class);
      House house2 = houseMapper.selectHouse(8052566L);

//      //批量插入测试
//      List<House> houseList = new ArrayList<>();
//      houseList.add(house2);
//      houseList.add(house2);
//      houseList.add(house2);
//      Integer house3 = houseMapper.batchInsert(houseList);


//    -------------------------------housedel_basic表------------------------------
      HousedelBasicMapper housedelBasicMapper = session.getMapper(HousedelBasicMapper.class);
      HousedelBasic housedelBasic1 = housedelBasicMapper.selectHousedelBasicInfo(105100000002L);

//      List<Integer> delTypeList = new ArrayList<>();
//      delTypeList.add(1);
//      delTypeList.add(2);
//      List<HousedelBasic> housedelBasic5 = housedelBasicMapper.selectBiggerThanCode(105100000002L, delTypeList);
//      List<HousedelBasic> housedelBasic2 = housedelBasicMapper.selectByBuildAreaAndOrientation("东%", housedelBasic1);
//      List<HousedelBasic> housedelBasic3 = housedelBasicMapper.selectByBuildAreaOrOrientation("东%", housedelBasic1);

      //返回更新的行数
      Integer housedelBasic4 = housedelBasicMapper.updateByBuildAreaOrOrientation("东%", housedelBasic1);
      //提交事务、关闭SqlSession
      //mybatis如果底层使用JDBC（在mybatis.xml中配置的transactionManager标签的type设为jdbc的话），那么，mybatis会默认开启事务，也就是说，mybatis默认是关闭自动提交的。
      //在mybatis中，如果我们执行了数据库的修改操作（insert、update、delete），必须调用session.commit()方法，所做的修改才能持久化到磁盘。
      //在openSession()时，传入true，可自动提交事务。若选择空参，默认autoCommit=false，但是没有手动commit，在sqlSession.close()时，Mybatis会将事务进行rollback()操作，然后才执行conn.close()关闭连接，当然数据最终也就没能持久化到数据库中了
      session.commit();
      //try (SqlSession session = sqlSessionFactory.openSession()) 自动在finally中session.close()了

//      log.info("house1:{}", house1);
      log.info("house2:{}", house2);
//      log.info("house3:{}", house3);

      log.info("housedelBasic1:{}", housedelBasic1);
//      log.info("housedelBasic5:{}", housedelBasic5);
//      log.info("housedelBasic2:{}", housedelBasic2);
//      log.info("housedelBasic3:{}", housedelBasic3);
//      log.info("housedelBasic4:{}", housedelBasic4);
    }
  }
}
