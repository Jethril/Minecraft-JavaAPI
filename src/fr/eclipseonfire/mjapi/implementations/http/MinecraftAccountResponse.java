/*
 * Copyright 2016 Arthur Mille.
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package fr.eclipseonfire.mjapi.implementations.http;

import fr.eclipseonfire.mjapi.interfaces.MinecraftAccount;

public final class MinecraftAccountResponse extends Response{
    
    private String id, name;
    private StandardMinecraftProperties properties;

    public MinecraftAccountResponse(String error, String errorMessage){
        super(error, errorMessage);
    }

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public StandardMinecraftProperties getProperties(){
        return properties;
    }
    
    public MinecraftAccount toAccount(){
        return new MinecraftAccount(this.id, this.name, this.properties);
    }
}