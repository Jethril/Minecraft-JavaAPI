/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package fr.eclipseonfire.mjapi.interfaces;

import java.util.UUID;

/**
 * Utility class used to convert UUIDs.
 *
 * @author Arthur Mille
 * @version 1.0
 */
public final class UuidConverter {

    private UuidConverter() {}

    /**
     * Converts a Minecraft UUID in a Java UUID.
     *
     * @param uuid The Minecraft UUID to convert.
     * @return The Java UUID.
     * @since 1.0
     */
    public static UUID fromMinecraftUuid(String uuid) {
        String result = uuid.substring(0, 8) + '-' +
                        uuid.substring(8, 12) + '-' +
                        uuid.substring(12, 16) + '-' +
                        uuid.substring(16, 20) + '-' +
                        uuid.substring(20, 32);
        return UUID.fromString(result);
    }

    /**
     * Converts a Java UUID in a Minecraft UUID.
     *
     * @param uuid The Java UUID.
     * @return The Minecraft UUID.
     * @since 1.0
     */
    public static String toMinecraftUuid(UUID uuid) {
        return uuid.toString().replace("-", "");
    }
}