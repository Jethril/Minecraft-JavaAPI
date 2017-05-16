/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package fr.eclipseonfire.mjapi.implementations.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.eclipseonfire.mjapi.interfaces.AccountRepository;
import fr.eclipseonfire.mjapi.interfaces.AccountRepositoryException;
import fr.eclipseonfire.mjapi.interfaces.MinecraftAccount;
import fr.eclipseonfire.mjapi.interfaces.NameRecord;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.util.List;

public class HttpAccountRepository implements AccountRepository {

    public static final String URL_NAME_TO_UUID = "https://api.mojang.com/users/profiles/minecraft/%s";
    public static final String URL_UUID_TO_PROFILE = "https://sessionserver.mojang.com/session/minecraft/profile/%s";
    public static final String URL_UUID_TO_HISTORY = "https://api.mojang.com/user/profiles/%s/names";

    public static final Gson GSON;

    static {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(NameRecordListResponse.class, NameRecordListResponse.DESERIALIZER);
        builder.setPrettyPrinting();
        builder.serializeNulls();
        GSON = builder.create();
    }

    public HttpAccountRepository() {}

    @Override
    public MinecraftAccount getAccountByName(String name, Proxy proxy) throws AccountRepositoryException {
        if (proxy == null) {
            throw new NullPointerException("The parameter proxy cannot be null! Use Proxy.NO_PROXY constant instead.");
        }

        if (name.contains(" ")) {
            throw new AccountRepositoryException("Invalid user name given!");
        }

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(String.format(
                    URL_NAME_TO_UUID,
                    name
            )).openConnection(proxy);

            UuidResponse response = GSON.fromJson(Utilities.performGET(connection), UuidResponse.class);

            if (response == null) {
                return null;
            }

            response.throwExceptionIfNeeded();

            return this.getAccountByUuid(response.getId(), proxy);
        }
        catch (IOException ex) {
            throw new AccountRepositoryException(ex);
        }
    }

    @Override
    public MinecraftAccount getAccountByUuid(String uuid, Proxy proxy) throws AccountRepositoryException {
        if (proxy == null) {
            throw new NullPointerException("The parameter proxy cannot be null! Use Proxy.NO_PROXY constant instead.");
        }

        if (uuid.contains(" ")) {
            throw new IllegalArgumentException("Invalid UUID given!");
        }

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(String.format(
                    URL_UUID_TO_PROFILE,
                    uuid
            )).openConnection(proxy);

            MinecraftAccountResponse response = GSON.fromJson(
                    Utilities.performGET(connection),
                    MinecraftAccountResponse.class
            );

            if (response == null) {
                return null;
            }

            response.throwExceptionIfNeeded();

            return response.toAccount();
        }
        catch (IOException e) {
            throw new AccountRepositoryException(e);
        }
    }

    @Override
    public List<NameRecord> getNameHistory(String uuid, Proxy proxy) throws AccountRepositoryException {
        if (proxy == null) {
            throw new NullPointerException("The parameter proxy cannot be null! Use Proxy.NO_PROXY constant instead.");
        }

        if (uuid.contains(" ")) {
            throw new IllegalArgumentException("Invalid UUID given!");
        }

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(String.format(
                    URL_UUID_TO_HISTORY,
                    uuid
            )).openConnection(proxy);

            NameRecordListResponse response = GSON.fromJson(
                    Utilities.performGET(connection),
                    NameRecordListResponse.class
            );

            if (response == null) {
                return null;
            }

            response.throwExceptionIfNeeded();

            return response;
        }
        catch (IOException ex) {
            throw new AccountRepositoryException(ex);
        }
    }
}
