/*
 * Copyright 2016 Arthur Mille.
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package fr.eclipseonfire.mjapi.interfaces;

import java.util.UUID;

/**
 * Utility class used to convert UUIDs.
 * 
 * @author Arthur Mille
 * @version 1.0
 */
public final class UuidConverter{
    
    private UuidConverter(){}
    
    /**
     * Converts a Minecraft UUID in a Java UUID.
     * 
     * @param uuid The Minecraft UUID to convert.
     * @return The Java UUID.
     * @since 1.0
     */
    public static UUID fromMinecraftUuid(String uuid){
        StringBuilder result = new StringBuilder();
        result.append(uuid.substring(0, 8)).append('-');
        result.append(uuid.substring(8, 12)).append('-');
        result.append(uuid.substring(12, 16)).append('-');
        result.append(uuid.substring(16, 20)).append('-');
        result.append(uuid.substring(20, 32));
        return UUID.fromString(result.toString());
    }
    
    /**
     * Converts a Java UUID in a Minecraft UUID.
     * 
     * @param uuid The Java UUID.
     * @return The Minecraft UUID.
     * @since 1.0
     */
    public static String toMinecraftUuid(UUID uuid){
        return uuid.toString().replace("-", "");
    }
}