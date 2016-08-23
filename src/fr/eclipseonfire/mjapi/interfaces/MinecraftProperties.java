/* 
 * Copyright 2016 Arthur Mille.
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package fr.eclipseonfire.mjapi.interfaces;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The Minecraft properties is a dynamic array of named objects representing metadata about the player.
 * <p>
 * This object is iterable so you can use a for loop on it.
 * </p>
 * 
 * @author eclipseonfire
 * @version 1.0
 */
public abstract class MinecraftProperties extends ArrayList<Property>{
    
    /**
     * The skin property name.
     * 
     * @since 1.0
     */
    public static final String SKIN_PROPERTY_NAME = "textures";
    
    public MinecraftProperties(){
        super();
    }
    
    /**
     * Retrieves a property using its name, case insensitive.
     * 
     * @param name The property name.
     * @return The property, or <code>null</code> if the name was not found.
     * @since 1.0
     */
    public Property getByName(String name){
        for(Property property : this){
            if(name.equalsIgnoreCase(property.getName())){
                return property;
            }
        }
        return null;
    }
    
    /**
     * Finds the textures property and returns a <code>MinecraftTextures</code> object which is a wrapper for the property value.
     * <p>
     * This operation performs an instanciation and the result should be cached for better performance.
     * </p>
     * 
     * @return The value of the skin property, or null if the property was not found.
     * @throws IOException If an error occurs while decoding data.
     * @since 1.0
     */
    public abstract MinecraftTextures getTexturesProperty() throws IOException;
}