package com.template.micro.client.dto;

import com.template.micro.client.entity.TShippingAddress;
import com.template.micro.client.entity.TUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户收货地址 DTO
 *
 * @author CL
 */
@Data
public class UserShippingAddressDto extends TUser {
    private static final long serialVersionUID = 1L;

    private String address; // 地址
    private String def; // 是否默认
}
