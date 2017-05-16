/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package fr.eclipseonfire.mjapi.implementations.http;

import fr.eclipseonfire.mjapi.interfaces.AccountRepositoryException;

public class Response {

    protected final String error;
    protected final String errorMessage;

    public Response(String error, String errorMessage) {
        this.error = error;
        this.errorMessage = errorMessage;
    }

    public final String getError() {
        return error;
    }

    public final String getErrorMessage() {
        return errorMessage;
    }

    public void throwExceptionIfNeeded() throws AccountRepositoryException {
        if (this.error != null) {
            throw new AccountRepositoryException(
                    String.format("The server returned a %s with message : \"%s\"", this.error, this.errorMessage)
            );
        }
    }
}
