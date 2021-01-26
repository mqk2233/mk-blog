package com.mk.blog.handler;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.log.StaticLog;
import com.mk.blog.enums.ResponseEnum;
import com.mk.blog.exception.CustomException;
import com.mk.blog.http.ResponseEntity;
import com.pig4cloud.plugin.idempotent.exception.IdempotentException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * @describe 全局异常处理器
 * @author MK
 * @date 2021/1/26
 */
@RestControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionHandle(Exception e) {
        StaticLog.error(ExceptionUtil.stacktraceToString(e));
        return ResponseEntity.buildByEnum(ResponseEnum.UNKNOWN_ERROR);
    }

    /**
     * 自定义异常
     */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> customExceptionHandle(CustomException e) {
        StaticLog.error("MyException ->{}", e.getEnum().getMsg());
        return ResponseEntity.buildByEnum(e.getEnum());
    }

    /**
     * 表单校验的异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validationExceptionHandle(MethodArgumentNotValidException e) {
        StringBuilder errorMsg = new StringBuilder();
        List<ObjectError> list = e.getBindingResult().getAllErrors();
        list.forEach(error -> errorMsg.append(error.getDefaultMessage())
                .append(list.size() > 1 ? "," : ""));
        StaticLog.error(e);
        return ResponseEntity.buildByMsg(errorMsg.toString(), 199);
    }

    @ExceptionHandler(IdempotentException.class)
    public ResponseEntity<?> idempotentExceptionHandle(IdempotentException e){
        StaticLog.error(e);
        return ResponseEntity.buildByEnum(ResponseEnum.IDEMPOTENT_ERROR);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> validationException2Handle(ConstraintViolationException e) {
        String str = ExceptionUtil.stacktraceToString(e);
        String substring = str.substring(str.indexOf(":"));
        StaticLog.error(substring);
        return ResponseEntity.buildByMsg(substring, 199);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<?> missingServletRequestParameterExceptionHandle(MissingServletRequestParameterException e) {
        StaticLog.error(ExceptionUtil.stacktraceToString(e));
        return ResponseEntity.buildByEnum(ResponseEnum.NO_PARAM);
    }
}