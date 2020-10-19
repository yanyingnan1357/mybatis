package org.apache.ibatis.aaatest.entry;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class House implements Serializable {

  /**
   * 主键
   */
  private Long id;

  /**
   * id_type 1全局，2acn， default 1
   */
  private Integer idType;

  /**
   * 创建时间
   */
  private Date createTime;

}
