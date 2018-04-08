package com.jack.handler;

import com.jack.intepen.dto.IntepenResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jack.intepen.enums.AuthcEnum;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 11407 on 30/030.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    private IntepenResult exceptionHandler(HttpServletRequest req, Exception e){
        return new IntepenResult(AuthcEnum.ERROR.getCode(), e.getMessage());
    }
}
