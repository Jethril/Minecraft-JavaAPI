/* 
 * Copyright 2016 Arthur Mille.
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package fr.eclipseonfire.mjapi.interfaces;

/**
 * Represents an exception thrown by the <code>MojangRepostitory</code> class.
 * 
 * @author eclipseonfire
 * @version 1.0
 */
public class AccountRepositoryException extends Exception{
    
    public AccountRepositoryException(){
        super();
    }
    
    public AccountRepositoryException(String msg){
        super(msg);
    }

    public AccountRepositoryException(Throwable cause){
        super(cause);
    }
    
    public AccountRepositoryException(String msg, Throwable cause){
        super(msg, cause);
    }
}