/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package fr.eclipseonfire.mjapi.interfaces;

import java.util.Objects;

/**
 * Represents a record (name history).
 *
 * @author Arthur MILLE
 * @version 1.0
 */
public class NameRecord {

    private final String name;
    private final Long   changedToAt;

    public NameRecord(String name, Long changedToAt) {
        if (name == null) {
            throw new NullPointerException("The parameter name cannot be null!");
        }
        this.name = name;
        this.changedToAt = changedToAt;
    }

    /**
     * @return The name.
     * @since 1.0
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return When this name has been changed.
     * @since 1.0
     */
    public Long getChangedToAt() {
        return this.changedToAt;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof NameRecord)) {
            return false;
        }

        NameRecord o = (NameRecord) obj;

        return Objects.equals(this.name, o.name) && Objects.equals(this.changedToAt, o.changedToAt);
    }

    @Override
    public int hashCode() {
        int hash = 654;
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + Objects.hashCode(this.changedToAt);
        return hash;
    }
}