package org.apache.ibatis.aayyn;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * HousedelBasic
 *
 * @author yyn
 */
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Builder
@Getter
@Setter
@ToString
//@Table(name = "sh_housedel_basic")
public class HousedelBasic implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 房源编码
   */
  @Id
  @Column(name = "housedel_code")
  @GeneratedValue(generator = "JDBC")
  private Long housedelCode;

  /**
   * 公司
   */
  @Column(name = "company_code")
  private String companyCode;

  /**
   * 房源类型，1委托
   */
  @Column(name = "housedel_type")
  private Integer housedelType;

  /**
   * 城市国标码
   */
  @Column(name = "office_address")
  private Integer officeAddress;

  /**
   * 楼盘字典楼盘ID
   */
  @Column(name = "resblock_id")
  private Long resblockId;

  /**
   * 楼盘字典楼栋ID
   */
  @Column(name = "building_id")
  private Long buildingId;

  /**
   * 楼盘字典房屋ID
   */
  @Column(name = "standard_house_id")
  private Long standardHouseId;

  /**
   * 建筑面积
   */
  @Column(name = "build_area")
  private BigDecimal buildArea;

  /**
   * 室数
   */
  @Column(name = "bedroom_amount")
  private Integer bedroomAmount;

  /**
   * 厅数
   */
  @Column(name = "parlor_amount")
  private Integer parlorAmount;

  /**
   * 厨房数
   */
  @Column(name = "cookroom_amount")
  private Integer cookroomAmount;

  /**
   * 卫生间数
   */
  @Column(name = "toilet_amount")
  private Integer toiletAmount;

  /**
   * 房屋朝向
   */
  @Column(name = "orientation")
  private String orientation;

  /**
   * 价格
   */
  @Column(name = "price")
  private BigDecimal price;

  /**
   * sh_housedel 表与 sh_housedel_basic表del_status 字段映射关系
   * 0 -> 0 ; 1~6+11 -> 1 ; 7 -> 10
   */
  @Column(name = "del_status")
  private Integer delStatus;

  /**
   * 无效状态改变时间
   */
  @Column(name = "invalid_time")
  private Date invalidTime;

  /**
   * 录入操作人
   * 这里指的是这条数据实际的创建人
   * 与房源角色中的录入人不是一个概念
   */
  @Column(name = "created_ucid")
  private Long createdUcid;

  /**
   * 创建时间
   */
  @Column(name = "created_time")
  private Date createdTime;

  /**
   * 更新时间
   */
  @Column(name = "updated_time")
  private Date updatedTime;

  /**
   * 套内面积
   */
  @Column(name = "indoor_size")
  private BigDecimal indoorSize;

  /**
   * 更新人ucid
   */
  @Column(name = "updated_ucid")
  private Long updatedUcid;

  /**
   * 一级委托来源
   * @return
   */
  @Column(name = "del_source_sup")
  private String delSourceSup;

  /**
   *二级委托来源
   * @return
   */
  @Column(name = "del_source_sub")
  private String delSourceSub;

}
