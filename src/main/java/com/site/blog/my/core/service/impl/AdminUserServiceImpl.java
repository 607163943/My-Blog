package com.site.blog.my.core.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.site.blog.my.core.mapper.AdminUserMapper;
import com.site.blog.my.core.pojo.po.AdminUser;
import com.site.blog.my.core.service.AdminUserService;
import com.site.blog.my.core.util.MD5Util;
import org.springframework.stereotype.Service;

@Service
public class AdminUserServiceImpl extends ServiceImpl<AdminUserMapper, AdminUser> implements AdminUserService {

    @Override
    public AdminUser login(String userName, String password) {
        String passwordMd5 = MD5Util.MD5Encode(password, "UTF-8");
        AdminUser adminUser = lambdaQuery().eq(AdminUser::getLoginUserName, userName)
                .eq(AdminUser::getLocked, 0)
                .one();

        // 密码不等返回null，controller层session负责处理
        if(!adminUser.getLoginPassword().equals(passwordMd5)) {
            return null;
        }

        StpUtil.login(adminUser.getAdminUserId());
        return adminUser;
    }

    @Override
    public AdminUser getUserDetailById(Integer loginUserId) {
        return getById(loginUserId);
    }

    @Override
    public Boolean updatePassword(Integer loginUserId, String originalPassword, String newPassword) {
        AdminUser adminUser = getById(loginUserId);
        //当前用户非空才可以进行更改
        if(adminUser==null) {
            return false;
        }

        String originalPasswordMd5 = MD5Util.MD5Encode(originalPassword, "UTF-8");
        String newPasswordMd5 = MD5Util.MD5Encode(newPassword, "UTF-8");
        //比较原密码是否正确
        if (!originalPasswordMd5.equals(adminUser.getLoginPassword())) {
            return false;
        }

        //设置新密码并修改
        adminUser.setLoginPassword(newPasswordMd5);

        //修改成功则返回true
        return updateById(adminUser);
    }

    @Override
    public Boolean updateName(Integer loginUserId, String loginUserName, String nickName) {
        AdminUser adminUser = getById(loginUserId);
        //当前用户非空才可以进行更改
        if (adminUser == null) {
            return false;
        }

        //修改信息
        adminUser.setLoginUserName(loginUserName);
        adminUser.setNickName(nickName);

        //修改成功则返回true
        return updateById(adminUser);
    }
}
