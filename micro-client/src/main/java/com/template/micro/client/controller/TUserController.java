package com.template.micro.client.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.yulichang.query.MPJQueryWrapper;
import com.github.yulichang.toolkit.MPJWrappers;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.template.micro.client.dto.UserShippingAddressDto;
import com.template.micro.client.entity.TShippingAddress;
import com.template.micro.client.entity.TUser;
import com.template.micro.client.mapper.TUserMapper;
import com.template.micro.client.service.ITUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 * https://www.cnblogs.com/cao-lei/p/16615920.html
 * 一种模式，写一个例子
 * @author 作者
 * @since 2023-04-15
 */
@Api(tags = {"[连表查询], tags = [连表查询], description = [连表查询]"})
@RestController
@RequestMapping("/tUser")
public class TUserController {
    @Autowired
    private ITUserService itUserService;
    @Resource
    private TUserMapper tUserMapper;

    /**
     * 查询用户列表数据
     *
     * @return
     */
    @ApiOperation(value = "listData")
    @GetMapping(value = "/listData")
    public List<TUser> listData() {
        return itUserService.list(Wrappers.emptyWrapper());
    }

    /**
     * 查询用户分页数据
     *
     * @return
     */
    @ApiOperation(value = "pageData")
    @PostMapping(value = "/pageData")
    public IPage<TUser> pageData(long current, long size, TUser user) {
        return itUserService.page(new Page<>(current, size), Wrappers.query(user));
    }

    /**
     * 查询用户默认收货地址
     * https://gitee.com/best_handsome/mybatis-plus-join
     *
     * @return
     */
    @ApiOperation(value = "userAddress")
    @PostMapping(value = "/userAddress")
    public List<UserShippingAddressDto> userShippingAddress(UserShippingAddressDto userShippingAddressDto) {
        // 分页查询
        MPJLambdaWrapper<TUser> wrapper = new MPJLambdaWrapper<TUser>()
                .selectAll(TUser.class).select(TShippingAddress::getAddress)
                .selectAs(TShippingAddress::getIsDefault, UserShippingAddressDto::getDef)
                .leftJoin(TShippingAddress.class, TShippingAddress::getUserId, TUser::getId)
                .eq(TUser::getId, userShippingAddressDto.getId())
                .like(TShippingAddress::getAddress, "陕西省")
                .gt(TUser::getId, 0)
                .orderByAsc(TUser::getId);


        Page<UserShippingAddressDto> listPage = tUserMapper.selectJoinPage(new Page<>(0, 10), UserShippingAddressDto.class, wrapper);
        System.out.println(listPage.getRecords());

        // 以sql形式查询
        MPJQueryWrapper queryWrapper = new MPJQueryWrapper<TUser>()
                .selectAll(TUser.class)
                .select("tsa.address")
                .select("CASE tsa.is_default WHEN 't' THEN 'true' ELSE 'false' END AS def")
                .leftJoin(" t_shipping_address tsa on t.id=tsa.user_id ")
                .eq("t.id",userShippingAddressDto.getId())
                .orderByAsc("t.id");
        List<UserShippingAddressDto> userShippingAddressDtoList =tUserMapper.selectJoinList(UserShippingAddressDto.class,queryWrapper);
        System.out.println(userShippingAddressDtoList);
        Page<UserShippingAddressDto> queryListPage = tUserMapper.selectJoinPage(new Page<>(0,10),UserShippingAddressDto.class,queryWrapper);
        System.out.println(queryListPage.getRecords());


        return itUserService.selectJoinList(UserShippingAddressDto.class, MPJWrappers.<TUser>lambdaJoin()
                .selectAll(TUser.class).select(TShippingAddress::getAddress)
                .selectAs(TShippingAddress::getIsDefault, UserShippingAddressDto::getDef)
                .leftJoin(TShippingAddress.class, TShippingAddress::getUserId, TUser::getId)
                .eq(TUser::getId, userShippingAddressDto.getId())
                .like(TShippingAddress::getAddress, "陕西省")
                .gt(TUser::getId, 0)
                .orderByAsc(TUser::getId)
                .last(" limit 10 ")
        );
    }


    /**
     * 查询用户默认收货地址
     * https://gitee.com/best_handsome/mybatis-plus-join
     *
     * @return
     */
    @ApiOperation(value = "default")
    @PostMapping(value = "/default")
    public UserShippingAddressDto userDefaultShippingAddress(UserShippingAddressDto userShippingAddressDto) {
        return itUserService.selectJoinOne(UserShippingAddressDto.class, MPJWrappers.<TUser>lambdaJoin()
                .selectAll(TUser.class)
                .select(TShippingAddress::getAddress)
                .selectAs(TShippingAddress::getIsDefault, UserShippingAddressDto::getDef)
                .leftJoin(TShippingAddress.class, TShippingAddress::getUserId, TUser::getId)
                .eq(TUser::getId, userShippingAddressDto.getId())
                .eq(TShippingAddress::getIsDefault, "t")
                .last("limit 1"));
    }

    /**
     * 查询用户收货地址分页数据
     * 分页查询demo
     * @return
     */
    @ApiOperation(value = "pageAddressData")
    @PostMapping(value = "/pageAddressData")
    public Page<UserShippingAddressDto> pageUserShippingAddressData(long current, long size, UserShippingAddressDto userShippingAddressDto) {
        // Mybatis-plus 对count进行了优化，因此需要关掉优化关联统计sql，详见：https://gitee.com/best_handsome/mybatis-plus-join/issues/I55FIU
        return itUserService.selectJoinListPage(new Page<>(current, size), UserShippingAddressDto.class, MPJWrappers.<TUser>lambdaJoin()
                .selectAll(TUser.class)
                .select(TShippingAddress::getAddress)
                .selectAs(TShippingAddress::getIsDefault, UserShippingAddressDto::getDef)
                .leftJoin(TShippingAddress.class, TShippingAddress::getUserId, TUser::getId)
                .eq(Objects.nonNull(userShippingAddressDto.getId()), TUser::getId, userShippingAddressDto.getId())
                .like(StringUtils.isNotEmpty(userShippingAddressDto.getUsername()), TUser::getUsername, userShippingAddressDto.getUsername())
                .eq(StringUtils.isNotEmpty(userShippingAddressDto.getDef()), TShippingAddress::getIsDefault, userShippingAddressDto.getDef()));
    }

    /**
     * 统计用户收货地址数量
     * 统计demo
     * @return
     */
    @ApiOperation(value = "countAddress")
    @PostMapping(value = "/countAddress")
    public long countUserShippingAddress(UserShippingAddressDto userShippingAddressDto) {
        return itUserService.selectJoinCount(MPJWrappers.<TUser>lambdaJoin()
                .select(TShippingAddress::getAddress)
                .leftJoin(TShippingAddress.class, TShippingAddress::getUserId, TUser::getId)
                .eq(TUser::getId, userShippingAddressDto.getId()));
    }

}

