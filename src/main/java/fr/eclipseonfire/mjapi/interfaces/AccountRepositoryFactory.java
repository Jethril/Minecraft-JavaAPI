/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package fr.eclipseonfire.mjapi.interfaces;

import fr.eclipseonfire.mjapi.implementations.http.HttpAccountRepository;

/**
 * This class is used to determine the default and available implementations of the <code>AccountRepository</code> interface.
 *
 * @author Arthur MILLE
 * @version 1.0
 */
public final class AccountRepositoryFactory {

    /**
     * The default implementation class.
     *
     * @since 1.0
     */
    public static final Class<? extends AccountRepository> DEFAULT_IMPLEMENTATION = HttpAccountRepository.class;

    private AccountRepositoryFactory() {}

    /**
     * Creates an <code>AccountRepository</code> based on its class.
     *
     * @param repositoryImplementation The implementation class.
     * @return The <code>AccountRepository</code> implementation.
     * @since 1.0
     */
    public static AccountRepository createAccountRepository(Class<? extends AccountRepository> repositoryImplementation) {
        try {
            return repositoryImplementation.newInstance();
        }
        catch (InstantiationException | IllegalAccessException ex) {
            throw new IllegalArgumentException("Unable to instantiate the specified implementation!", ex);
        }
    }
}