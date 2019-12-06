package com.hand.hls.train.springbootdemoday01.exception;

import com.hand.hls.train.springbootdemoday01.entity.ErrMsg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 自定义异常
 *
 * @author jinhua.wu@hand-china.com 2019/12/05 13:41
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ErrMsg handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        ErrMsg errMsg = new ErrMsg();
        log.error("method argument not valid: {}", exception.getMessage());
        String errorMessage = "field error";
        BindingResult bindingResult = exception.getBindingResult();
        if (bindingResult.hasErrors()) {
            errorMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
        }
        errMsg.setMessage(errorMessage);
        return errMsg;
    }
}
