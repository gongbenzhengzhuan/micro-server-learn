package com.template.micro.client.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author 作者
 * @since 2023-04-15
 */
@Getter
@Setter
@TableName("t_user")
@ApiModel(value = "TUser对象", description = "用户信息表")
public class TUser extends Model<TUser> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户名称")
    private String username;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("手机号")
    private String phone;


}
