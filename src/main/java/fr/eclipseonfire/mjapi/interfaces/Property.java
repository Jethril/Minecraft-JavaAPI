/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package fr.eclipseonfire.mjapi.interfaces;

/**
 * This class represents a Minecraft property.
 *
 * @author Arthur MILLE
 * @version 1.0
 */
public class Property {

    protected String name, value, signature;

    /**
     * Creates a property with its fields.
     *
     * @param name      The property name, not null.
     * @param value     The property value, not null.
     * @param signature The signature (can be null).
     * @since 1.0
     */
    public Property(String name, String value, String signature) {
        if (name == null) {
            throw new NullPointerException("The parameter name cannot be null!");
        }

        if (value == null) {
            throw new NullPointerException("The parameter value cannot be null!");
        }

        this.name = name;
        this.value = value;
        this.signature = signature;
    }

    /**
     * @return The property name.
     * @since 1.0
     */
    public String getName() {
        return name;
    }

    /**
     * @return The property value.
     * @since 1.0
     */
    public String getValue() {
        return value;
    }

    /**
     * @return The property signature.
     * @since 1.0
     */
    public String getSignature() {
        return signature;
    }
}