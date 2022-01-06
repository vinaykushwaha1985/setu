package com.gov.nha.bis.server.exception;

/**
 * @author Vinay Kushwaha
 * @Email  vinaykushwaha85@gmail.com

 */
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.gov.nha.bis.server.model.UserForm;

@ControllerAdvice
public class ExceptionResponseStatusHandler {
    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handleError() {
        
        return new ModelAndView("error", "command", new UserForm());
    }
}