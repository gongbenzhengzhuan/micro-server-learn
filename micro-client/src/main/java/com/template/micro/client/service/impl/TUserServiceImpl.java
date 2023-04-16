package com.template.micro.client.service.impl;

import com.github.yulichang.base.MPJBaseServiceImpl;
import com.template.micro.client.entity.TUser;
import com.template.micro.client.mapper.TUserMapper;
import com.template.micro.client.service.ITUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author 作者
 * @since 2023-04-15
 */
@Service
public class TUserServiceImpl extends MPJBaseServiceImpl<TUserMapper, TUser> implements ITUserService {

}
