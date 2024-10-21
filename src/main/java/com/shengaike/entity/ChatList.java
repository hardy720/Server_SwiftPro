package com.shengaike.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Hardy
 * @since 2024-10-21
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class ChatList implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

    private Integer userId;

    private Integer friendId;

    private String lastText;

    private String friendAvatar;

    private String friendName;

    private LocalDateTime lastCreatetime;


}
