/* 
 * Copyright 2016 Arthur Mille.
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package fr.eclipseonfire.mjapi.interfaces;

/**
 * Class that represents a Minecraft account, with some basic informations.
 * 
 * @author eclipseonfire
 * @version 1.0
 */
public class MinecraftAccount{
    
    /**
     * The account UUID.
     * 
     * @since 1.0
     */
    protected String id;
    
    /**
     * The player name.
     * 
     * @since 1.0
     */
    protected String name;
    
    /**
     * The metadata on the player.
     * 
     * @since 1.0
     */
    protected MinecraftProperties properties;
    
    /**
     * Creates a <code>MinecraftAccount</code> object using its properties.
     * 
     * @param uuid The player UUID, not null.
     * @param name The player name, not null.
     * @param properties The properties, not null.
     */
    public MinecraftAccount(String uuid, String name, MinecraftProperties properties){
        if(uuid == null){
            throw new NullPointerException("The parameter uuid cannot be null!");
        }
        
        if(name == null){
            throw new NullPointerException("The parameter name cannot be null!");
        }
        
        if(properties == null){
            throw new NullPointerException("The parameter properties cannot be null!");
        }
        
        this.id = uuid;
        this.name = name;
        this.properties = properties;
    }
    
    /**
     * @return The player UUID.
     * @since 1.0
     */
    public String getId(){
        return id;
    }
    
    /**
     * @return The player name.
     * @since 1.0
     */
    public String getName(){
        return name;
    }
    
    /**
     * @return The Minecraft properties.
     * @since 1.0
     */
    public MinecraftProperties getProperties(){
        return properties;
    }
}