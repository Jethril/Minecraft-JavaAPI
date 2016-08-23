/* 
 * Copyright 2016 Arthur Mille.
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package fr.eclipseonfire.mjapi.interfaces;

import java.net.Proxy;
import java.util.List;

/**
 * Represents the Mojang's servers, which are able to exchange informations with the application.
 * 
 * @author eclipseonfire
 * @version 1.0
 */
public interface AccountRepository{
    
    /**
     * Retrieves a Minecraft account from the database, based on the provided user name.
     * 
     * @param name The Minecraft user name.
     * @param proxy The proxy used to access the server.
     * @return The Minecraft account, or <code>null</code> if the account wasn't found.
     * @throws AccountRepositoryException If an error occurs while fetching the data.
     */
    public MinecraftAccount getAccountByName(String name, Proxy proxy) throws AccountRepositoryException;
    
    /**
     * Retrieves a Minecraft account from the database, based on the provided UUID.
     * 
     * @param uuid The UUID.
     * @param proxy The proxy used to access the server.
     * @return The Minecraft account, or <code>null</code> if the account wasn't found.
     * @throws AccountRepositoryException If an error occurs while fetching the data.
     * @since 1.0
     */
    public MinecraftAccount getAccountByUuid(String uuid, Proxy proxy) throws AccountRepositoryException;
    
    /**
     * Retrieves an history of the names used by the player from the database, based on the provided UUID.
     * 
     * @param uuid The UUID.
     * @param proxy The proxy used to access the server.
     * @return A list containing the name history, or <code>null</code> if the account wasn't found.
     * @throws AccountRepositoryException If an error occurs while fetching the data.
     */
    public List<NameRecord> getNameHistory(String uuid, Proxy proxy) throws AccountRepositoryException;
}