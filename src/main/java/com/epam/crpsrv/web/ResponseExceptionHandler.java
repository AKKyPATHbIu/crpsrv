package com.epam.crpsrv.web;

import com.epam.crpsrv.exception.CrpSrvException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResponseExceptionHandler {

    @ExceptionHandler({CrpSrvException.class})
    public String handleCrpSrvException(CrpSrvException e) {
        return e.getMessage();
    }
}