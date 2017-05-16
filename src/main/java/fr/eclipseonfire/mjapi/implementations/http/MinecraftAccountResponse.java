/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package fr.eclipseonfire.mjapi.implementations.http;

import fr.eclipseonfire.mjapi.interfaces.MinecraftAccount;

public final class MinecraftAccountResponse extends Response {

    private String id, name;
    private StandardMinecraftProperties properties;

    public MinecraftAccountResponse(String error, String errorMessage) {
        super(error, errorMessage);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public StandardMinecraftProperties getProperties() {
        return properties;
    }

    public MinecraftAccount toAccount() {
        return new MinecraftAccount(this.id, this.name, this.properties);
    }
}