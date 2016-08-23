/*
 * Copyright 2016 Arthur Mille.
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package fr.eclipseonfire.mjapi.interfaces;

import java.util.Objects;

/**
 * Represents a record (name history).
 * 
 * @author eclipseonfire
 * @version 1.0
 */
public class NameRecord{
    
    private final String name;
    private final Long changedToAt;
    
    public NameRecord(String name, Long changedToAt){
        if(name == null){
            throw new NullPointerException("The parameter name cannot be null!");
        }
        this.name = name;
        this.changedToAt = changedToAt;
    }
    
    /**
     * @return The name.
     * @since 1.0
     */
    public String getName(){
        return this.name;
    }
    
    /**
     * @return When this name has been changed.
     * @since 1.0
     */
    public Long getChangedToAt(){
        return this.changedToAt;
    }

    @Override
    public int hashCode(){
        int hash = 654;
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + Objects.hashCode(this.changedToAt);
        return hash;
    }

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof NameRecord)){
            return false;
        }
        
        NameRecord o = (NameRecord)obj;
        
        return Objects.equals(this.name, o.name) && Objects.equals(this.changedToAt, o.changedToAt);
    }
}