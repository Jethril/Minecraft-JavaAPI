/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package fr.eclipseonfire.mjapi.interfaces;

import java.net.Proxy;
import java.util.List;

/**
 * Represents the Mojang's servers, which are able to exchange informations with the application.
 *
 * @author Arthur MILLE
 * @version 1.0
 */
public interface AccountRepository {

    /**
     * Retrieves a Minecraft account from the database, based on the provided user name.
     *
     * @param name  The Minecraft user name.
     * @param proxy The proxy used to access the server.
     * @return The Minecraft account, or <code>null</code> if the account wasn't found.
     * @throws AccountRepositoryException If an error occurs while fetching the data.
     */
    MinecraftAccount getAccountByName(String name, Proxy proxy) throws AccountRepositoryException;

    /**
     * Retrieves a Minecraft account from the database, based on the provided UUID.
     *
     * @param uuid  The UUID.
     * @param proxy The proxy used to access the server.
     * @return The Minecraft account, or <code>null</code> if the account wasn't found.
     * @throws AccountRepositoryException If an error occurs while fetching the data.
     * @since 1.0
     */
    MinecraftAccount getAccountByUuid(String uuid, Proxy proxy) throws AccountRepositoryException;

    /**
     * Retrieves an history of the names used by the player from the database, based on the provided UUID.
     *
     * @param uuid  The UUID.
     * @param proxy The proxy used to access the server.
     * @return A list containing the name history, or <code>null</code> if the account wasn't found.
     * @throws AccountRepositoryException If an error occurs while fetching the data.
     */
    List<NameRecord> getNameHistory(String uuid, Proxy proxy) throws AccountRepositoryException;
}