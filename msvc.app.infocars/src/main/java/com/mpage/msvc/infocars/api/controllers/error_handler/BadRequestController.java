package com.mpage.msvc.infocars.api.controllers.error_handler;

import com.mpage.msvc.infocars.api.models.response.BaseErrorREsponse;
import com.mpage.msvc.infocars.api.models.response.ErrorAllREsponse;
import com.mpage.msvc.infocars.api.models.response.ErrorResponse;
import com.mpage.msvc.infocars.util.exceptions.IdNotFoundExceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;

public class BadRequestController {

    @ExceptionHandler(IdNotFoundExceptions.class)
    public BaseErrorREsponse handleIdNotFound(IdNotFoundExceptions exception){
        return ErrorResponse.builder()
                .error(exception.getMessage())
                .status(HttpStatus.BAD_REQUEST.name())
                .codeError(HttpStatus.BAD_REQUEST.value())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseErrorREsponse handleIdNotFound(MethodArgumentNotValidException exception){
        var errors = new ArrayList<String>();
        exception.getAllErrors()
                .forEach(error -> errors.add(error.getDefaultMessage()));

        return ErrorAllREsponse.builder()
                .errors(errors)
                .status(HttpStatus.BAD_REQUEST.name())
                .codeError(HttpStatus.BAD_REQUEST.value())
                .build();
    }
}
