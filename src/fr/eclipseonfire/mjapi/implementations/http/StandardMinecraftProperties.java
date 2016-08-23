/*
 * Copyright 2016 Arthur Mille.
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package fr.eclipseonfire.mjapi.implementations.http;

import fr.eclipseonfire.mjapi.interfaces.MinecraftProperties;
import fr.eclipseonfire.mjapi.interfaces.MinecraftTextures;
import fr.eclipseonfire.mjapi.interfaces.Property;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.codec.binary.Base64;

public class StandardMinecraftProperties extends MinecraftProperties{
    
    public StandardMinecraftProperties(){
        super();
    }
    
    @Override
    public MinecraftTextures getTexturesProperty() throws IOException{
        Property property = this.getByName("textures");
        
        if(property == null){
            return null;
        }
        
        String json = new String(Base64.decodeBase64(property.getValue()), StandardCharsets.UTF_8);
        
        return HttpAccountRepository.GSON.fromJson(json, HttpMinecraftTextures.class);
    }
}