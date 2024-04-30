package com.ringpro.ringannouncerapi;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// @Order says 'hey man, don't use your built in error handling to give me that
// 500 internal server error BS... use this file instead for the errors I define,
// and for any error I don't define then you can use your build in stuff

@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestExceptionHandler extends ResponseEntityExceptionHandler{
    
}
