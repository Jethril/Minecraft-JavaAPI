/*
 * Copyright 2016 Arthur Mille.
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package fr.eclipseonfire.mjapi.implementations.http;

import fr.eclipseonfire.mjapi.interfaces.AccountRepositoryException;

public class Response{

    protected String error, errorMessage;

    public Response(String error, String errorMessage){
        this.error = error;
        this.errorMessage = errorMessage;
    }

    public final String getError(){
        return error;
    }

    public final String getErrorMessage(){
        return errorMessage;
    }

    public void throwExceptionIfNeeded() throws AccountRepositoryException{
        if(this.error != null){
            throw new AccountRepositoryException(
                    String.format("The server returned a %s with message : \"%s\"", this.error, this.errorMessage)
            );
        }
    }
}
