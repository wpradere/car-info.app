package com.mpage.msvc.infocars.util.exceptions;

public class IdNotFoundExceptions extends RuntimeException{
    private static final String ERROR_MESSAGE = "Error  %s";

    public IdNotFoundExceptions(String tableName){
        super(String.format(ERROR_MESSAGE,tableName));

    }
}
