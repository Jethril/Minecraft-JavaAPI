/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package fr.eclipseonfire.mjapi.interfaces;

/**
 * Class that represents a Minecraft account, with some basic informations.
 *
 * @author Arthur MILLE
 * @version 1.0
 */
public class MinecraftAccount {

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
     * @param uuid       The player UUID, not null.
     * @param name       The player name, not null.
     * @param properties The properties, not null.
     */
    public MinecraftAccount(String uuid, String name, MinecraftProperties properties) {
        if (uuid == null) {
            throw new NullPointerException("The parameter uuid cannot be null!");
        }

        if (name == null) {
            throw new NullPointerException("The parameter name cannot be null!");
        }

        if (properties == null) {
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
    public String getId() {
        return id;
    }

    /**
     * @return The player name.
     * @since 1.0
     */
    public String getName() {
        return name;
    }

    /**
     * @return The Minecraft properties.
     * @since 1.0
     */
    public MinecraftProperties getProperties() {
        return properties;
    }
}