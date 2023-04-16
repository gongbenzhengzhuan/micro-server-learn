package com.template.micro.client.service.impl;

import com.template.micro.client.entity.TShippingAddress;
import com.template.micro.client.mapper.TShippingAddressMapper;
import com.template.micro.client.service.ITShippingAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 收货地址表 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2023-04-15
 */
@Service
public class TShippingAddressServiceImpl extends ServiceImpl<TShippingAddressMapper, TShippingAddress> implements ITShippingAddressService {

}
