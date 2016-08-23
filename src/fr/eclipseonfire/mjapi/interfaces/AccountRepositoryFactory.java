/*
 * Copyright 2016 Arthur Mille.
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package fr.eclipseonfire.mjapi.interfaces;

import fr.eclipseonfire.mjapi.implementations.http.HttpAccountRepository;

/**
 * This class is used to determine the default and available implementations of the <code>AccountRepository</code> interface.
 * 
 * @author eclipseonfire
 * @version 1.0
 */
public final class AccountRepositoryFactory{
    
    /**
     * The default implementation class.
     * 
     * @since 1.0
     */
    public static final Class<? extends AccountRepository> DEFAULT_IMPLEMENTATION = HttpAccountRepository.class;
    
    private AccountRepositoryFactory(){}
    
    /**
     * Creates an <code>AccountRepository</code> based on its class.
     * 
     * @param repositoryImplementation The implementation class.
     * @return The <code>AccountRepository</code> implementation.
     * @since 1.0
     */
    public static AccountRepository createAccountRepository(Class<? extends AccountRepository> repositoryImplementation){
        try{
            return repositoryImplementation.newInstance();
        }
        catch(InstantiationException | IllegalAccessException ex){
            throw new IllegalArgumentException("Unable to instantiate the specified implementation!", ex);
        }
    }
}