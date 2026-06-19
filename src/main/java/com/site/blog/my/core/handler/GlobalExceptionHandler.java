package com.site.blog.my.core.handler;

import cn.dev33.satoken.exception.NotLoginException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 出现登录异常直接跳转到后台登录页
     * @param e 登录异常实例
     */
    @ExceptionHandler(NotLoginException.class)
    public String notLoginExceptionHandler(NotLoginException e, HttpServletRequest request) {
        log.warn("登录异常：{}", e.getMessage(), e);
        // 会话填充错误信息
        request.getSession().setAttribute("errorMsg", "请重新登陆");

        // 跳转到后台登录页
        return "redirect:/admin/login";
    }
}
