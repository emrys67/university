package com.foxminded.university.controller.exception;

import com.foxminded.university.dao.exceptions.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice("com.foxminded.university.controller")
public class ExceptionHandlerController {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class.getName());

    @ExceptionHandler(ServiceException.class)
    public ModelAndView handleServiceExceptions( Exception ex){
        logger.debug("Start handling ServiceException - \"{}\"", ex.getMessage());
        logger.warn(ex.getMessage(), ex);
        return prepareView(ex, "errors/error");
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleOtherExceptions(Exception ex){
        logger.debug("Start handling generic exception - \"{}\"", ex.getMessage());
        logger.warn(ex.getMessage(), ex);
        return prepareView( ex, "errors/error");
    }

    private ModelAndView prepareView(Exception exception, String view){
        logger.debug("Start preparing view");
        ModelAndView modelAndView = new ModelAndView(view);
        modelAndView.addObject("exception", exception.getClass().getSimpleName());
        modelAndView.addObject("message", exception.getMessage());
        logger.debug("Start using view with name \"{}\"", exception.getClass().getSimpleName());
        return modelAndView;
    }
}
