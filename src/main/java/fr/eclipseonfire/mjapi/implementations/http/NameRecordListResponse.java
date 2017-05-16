/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package fr.eclipseonfire.mjapi.implementations.http;

import com.google.gson.*;
import fr.eclipseonfire.mjapi.interfaces.AccountRepositoryException;
import fr.eclipseonfire.mjapi.interfaces.NameRecord;
import java.lang.reflect.Type;
import java.util.ArrayList;

public final class NameRecordListResponse extends ArrayList<NameRecord> {

    public static final JsonDeserializer<NameRecordListResponse> DESERIALIZER =
            new NameRecordListResponseDeserializer();

    private String error, errorMessage;

    public NameRecordListResponse() {}

    public NameRecordListResponse(String error, String errorMessage) {
        super();
        this.error = error;
        this.errorMessage = errorMessage;
    }

    public String getError() {
        return this.error;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void throwExceptionIfNeeded() throws AccountRepositoryException {
        if (this.error != null) {
            throw new AccountRepositoryException(
                    String.format("The server returned an %s with message : \"%s\"", this.error, this.errorMessage)
            );
        }
    }

    private static class NameRecordListResponseDeserializer implements JsonDeserializer<NameRecordListResponse> {

        @Override
        public NameRecordListResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
        throws JsonParseException {
            if (json.isJsonNull()) {
                return null;
            }

            if (json.isJsonObject()) {
                JsonObject obj = json.getAsJsonObject();
                return new NameRecordListResponse(
                        obj.get("error").getAsString(),
                        obj.get("errorMessage").getAsString()
                );
            }

            if (json.isJsonArray()) {
                NameRecordListResponse result = new NameRecordListResponse();

                for (JsonElement element : json.getAsJsonArray()) {
                    result.add(context.deserialize(element, NameRecord.class));
                }

                return result;
            }

            throw new JsonParseException("Unable to parse the list response!");
        }
    }
}