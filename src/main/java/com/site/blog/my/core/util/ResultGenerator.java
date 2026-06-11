package com.site.blog.my.core.util;

import com.site.blog.my.core.result.Result;
import org.springframework.util.StringUtils;

/**
 * 响应结果生成工具
 */
public class ResultGenerator {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";
    private static final String DEFAULT_FAIL_MESSAGE = "FAIL";
    private static final int RESULT_CODE_SUCCESS = 200;
    private static final int RESULT_CODE_SERVER_ERROR = 500;

    public static Result<Object> genSuccessResult() {
        return Result.of(RESULT_CODE_SUCCESS, DEFAULT_SUCCESS_MESSAGE,null);
    }

    public static Result<Object> genSuccessResult(String message) {
        return Result.of(RESULT_CODE_SUCCESS, message,null);
    }

    public static Result<Object> genSuccessResult(Object data) {
        return Result.of(RESULT_CODE_SUCCESS, DEFAULT_SUCCESS_MESSAGE,data);
    }

    public static Result<Object> genFailResult(String message) {
        String msg=DEFAULT_FAIL_MESSAGE;
        if (StringUtils.hasText(message)) {
            msg=message;
        }
        return Result.of(RESULT_CODE_SERVER_ERROR, msg,null);
    }

    public static Result<Object> genErrorResult(int code, String message) {
        return Result.of(code, message,null);
    }
}
