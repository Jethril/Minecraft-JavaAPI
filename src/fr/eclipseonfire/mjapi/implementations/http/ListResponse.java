/*
 * Copyright 2016 Arthur Mille.
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package fr.eclipseonfire.mjapi.implementations.http;

import fr.eclipseonfire.mjapi.interfaces.AccountRepositoryException;
import java.util.ArrayList;

public final class ListResponse<T> extends ArrayList<T>{
    
    private String error, errorMessage;
    
    public ListResponse(String error, String errorMessage){
        super();
        this.error = error;
        this.errorMessage = errorMessage;
    }
    
    public String getError(){
        return this.error;
    }
    
    public String getErrorMessage(){
        return this.errorMessage;
    }
    
    public void throwExceptionIfNeeded() throws AccountRepositoryException{
        if(this.error != null){
            throw new AccountRepositoryException(
                    String.format("The server returned an %s with message : \"%s\"", this.error, this.errorMessage)
            );
        }
    }
}