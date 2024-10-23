package com.shengaike.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Hardy
 * @since 2024-10-22
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class FriendList implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private String friendName;

    private String remarkName;

    private Integer userId;

    private String friendAvatar;

      @TableField(fill = FieldFill.INSERT)
      private Date friendAddtime;


}
