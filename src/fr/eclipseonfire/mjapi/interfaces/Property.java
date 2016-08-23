/* 
 * Copyright 2016 Arthur Mille.
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package fr.eclipseonfire.mjapi.interfaces;

/**
 * This class represents a Minecraft property.
 * 
 * @author eclipseonfire
 * @version 1.0
 */
public class Property{
    
    protected String name, value, signature;
    
    /**
     * Creates a property with its fields.
     * 
     * @param name The property name, not null.
     * @param value The property value, not null.
     * @param signature The signature (can be null).
     * @since 1.0
     */
    public Property(String name, String value, String signature){
        if(name == null){
            throw new NullPointerException("The parameter name cannot be null!");
        }
        
        if(value == null){
            throw new NullPointerException("The parameter value cannot be null!");
        }
        
        this.name = name;
        this.value = value;
        this.signature = signature;
    }
    
    /**
     * @return The property name.
     * @since 1.0
     */
    public String getName(){
        return name;
    }
    
    /**
     * @return The property value.
     * @since 1.0
     */
    public String getValue(){
        return value;
    }
    
    /**
     * @return The property signature.
     * @since 1.0
     */
    public String getSignature(){
        return signature;
    }
}