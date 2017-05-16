/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package fr.eclipseonfire.mjapi.interfaces;

/**
 * Represents an exception thrown by the <code>MojangRepostitory</code> class.
 *
 * @author Arthur MILLE
 * @version 1.0
 */
public class AccountRepositoryException extends Exception {

    public AccountRepositoryException() {
        super();
    }

    public AccountRepositoryException(String msg) {
        super(msg);
    }

    public AccountRepositoryException(Throwable cause) {
        super(cause);
    }

    public AccountRepositoryException(String msg, Throwable cause) {
        super(msg, cause);
    }
}