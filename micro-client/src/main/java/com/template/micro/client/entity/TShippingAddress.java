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
 * 收货地址表
 * </p>
 *
 * @author 作者
 * @since 2023-04-15
 */
@Getter
@Setter
@TableName("t_shipping_address")
@ApiModel(value = "TShippingAddress对象", description = "收货地址表")
public class TShippingAddress extends Model<TShippingAddress> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户ID")
    private Integer userId;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("是否默认")
    private String isDefault;


}
