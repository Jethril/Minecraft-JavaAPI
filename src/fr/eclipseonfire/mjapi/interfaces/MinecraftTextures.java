/* 
 * Copyright 2016 Arthur Mille.
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package fr.eclipseonfire.mjapi.interfaces;

import java.io.IOException;
import java.net.Proxy;
import java.net.URL;

/**
 * Class representing a textures property, which can have a custom implementation.
 * 
 * @author eclipseonfire
 * @version 1.0
 */
public interface MinecraftTextures{
    
    /**
     * @return The cape download URL, or <code>null</code> if the player do not have cape.
     * @since 1.0
     */
    public URL getCapeDownloadUrl();
    
    /**
     * @return The skin download URL, or <code>null</code> if the player has no custom skin.
     * @since 1.0
     */
    public URL getSkinDownloadUrl();
    
    /**
     * Retrives the skin from the Mojang's servers.
     * 
     * @param proxy The proxy to use in order to connect to the URL.
     * @return A <code>MinecraftSkin</code> object. This method never returns <code>null</code> as the skin can be the steve's or alex ones.
     * @throws IOException If an error occurs while downloading or reading the image.
     * @since 1.0
     */
    public MinecraftSkin downloadSkin(Proxy proxy) throws IOException;
    
    /**
     * Retrieves the cape from the Mojang's servers.
     * 
     * @param proxy The proxy to use in order to connect to the URL.
     * @return A <code>MinecraftCape</code> object, or <code>null</code> if the player does not have a cape.
     * @throws IOException If an error occurs while downloading or reading the image.
     * @since 1.0
     */
    public MinecraftCape downloadCape(Proxy proxy) throws IOException;
}