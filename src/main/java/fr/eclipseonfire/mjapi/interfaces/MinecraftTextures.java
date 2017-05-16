/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package fr.eclipseonfire.mjapi.interfaces;

import java.io.IOException;
import java.net.Proxy;
import java.net.URL;

/**
 * Class representing a textures property, which can have a custom implementation.
 *
 * @author Arthur MILLE
 * @version 1.0
 */
public interface MinecraftTextures {

    /**
     * @return The cape download URL, or <code>null</code> if the player do not have cape.
     * @since 1.0
     */
    URL getCapeDownloadUrl();

    /**
     * @return The skin download URL, or <code>null</code> if the player has no custom skin.
     * @since 1.0
     */
    URL getSkinDownloadUrl();

    /**
     * Retrives the skin from the Mojang's servers.
     *
     * @param proxy The proxy to use in order to connect to the URL.
     * @return A <code>MinecraftSkin</code> object. This method never returns <code>null</code> as the skin can be the steve's or alex ones.
     * @throws IOException If an error occurs while downloading or reading the image.
     * @since 1.0
     */
    MinecraftSkin downloadSkin(Proxy proxy) throws IOException;

    /**
     * Retrieves the cape from the Mojang's servers.
     *
     * @param proxy The proxy to use in order to connect to the URL.
     * @return A <code>MinecraftCape</code> object, or <code>null</code> if the player does not have a cape.
     * @throws IOException If an error occurs while downloading or reading the image.
     * @since 1.0
     */
    MinecraftCape downloadCape(Proxy proxy) throws IOException;
}