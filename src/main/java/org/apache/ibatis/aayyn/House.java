package org.apache.ibatis.aayyn;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
//@Table(name = "sh_house")
public class House implements Serializable {
  /**
   * 主键
   */
  @Id
//  @Column(name = "id")
  @GeneratedValue(generator = "JDBC")
  private Long id;

  /**
   * id_type 1全局，2acn， default 1
   */
//  @Column(name = "id_type")
  private Integer idType;

  /**
   * 创建时间
   */
//  @Column(name = "create_time")
  private Date createTime;

}
