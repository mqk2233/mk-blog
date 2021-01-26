package com.mk.blog.exception;

import com.mk.blog.enums.ResponseEnum;
import com.mk.blog.http.ResponseEntity;
import com.pig4cloud.plugin.idempotent.exception.IdempotentException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * @author MK
 * @since 2019-04-24 18:09
 **/
@RestControllerAdvice
@Slf4j
@Getter
public class BaseExceptionHandle {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionHandle(Exception e) {
        e.printStackTrace();
        return ResponseEntity.buildByEnum(ResponseEnum.UNKNOWN_ERROR);
    }

    /**
     * 自定义异常
     */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> customExceptionHandle(CustomException e) {
        log.warn("MyException ->{}", e.getEnum().getMsg());
        return ResponseEntity.buildByEnum(e.getEnum());
    }

    /**
     * 表单校验的异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validationExceptionHandle(MethodArgumentNotValidException e) {
        StringBuilder errorMsg = new StringBuilder();
        List<ObjectError> list = e.getBindingResult().getAllErrors();
        list.forEach(error -> errorMsg.append(error.getDefaultMessage()).append(list.size() > 1 ? "," : ""));
        return ResponseEntity.buildByMsg(errorMsg.toString(), 199);
    }


    public ResponseEntity<?> IdempotentExceptionHandle(IdempotentException e){
        return ResponseEntity.buildByEnum(ResponseEnum.IDEMPOTENT_ERROR);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> validationException2Handle(ConstraintViolationException e) {
        String str = e.getMessage();
        String substring = str.substring(str.indexOf(":"));
        log.error(substring);
        return ResponseEntity.buildByMsg(substring, 199);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<?> missingServletRequestParameterExceptionHandle(MissingServletRequestParameterException e) {
        e.printStackTrace();
        return ResponseEntity.buildByEnum(ResponseEnum.NO_PARAM);
    }


}
