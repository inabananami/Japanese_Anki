package com.inabananami.japanesedemo.exception;

import com.inabananami.japanesedemo.vo.Result;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // 参数校验
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleValidationException(MethodArgumentNotValidException e) {
        String errorMsg = e.getBindingResult().getFieldError().getDefaultMessage();
        return Result.fail(10000, errorMsg);
    }
    // 重复键异常
    @ExceptionHandler(DuplicateKeyException.class)
    public Result handleDuplicateKey(DuplicateKeyException e) {
        return Result.fail(10000, "用户名已存在，请更换");
    }
    // 未登录
    @ExceptionHandler(UnauthorizedException.class)
    public Result handleUnauthorizedException(UnauthorizedException e) {
        return Result.fail(401, e.getMessage());
    }
    // token过期
    @ExceptionHandler(ForbiddenException.class)
    public Result handleForbiddenException(ForbiddenException e) {
        return Result.fail(403, e.getMessage());
    }
    //其他异常
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        return Result.fail(500, "服务器异常");
    }
}