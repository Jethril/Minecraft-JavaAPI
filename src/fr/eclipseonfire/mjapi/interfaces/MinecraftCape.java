/*
 * Copyright 2016 Arthur Mille.
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package fr.eclipseonfire.mjapi.interfaces;

import java.awt.image.BufferedImage;

/**
 * This class represents the minecraft cape.
 * 
 * @author eclipseonfire
 * @version 1.0
 */
public class MinecraftCape{
    
    /**
     * The cape image.
     * 
     * @since 1.0
     */
    protected BufferedImage image;
    
    /**
     * Constructs a minecraft cape with a buffered image.
     * 
     * @param image The skin as a buffered image.
     * @since 1.0
     */
    public MinecraftCape(BufferedImage image){
        if(image == null){
            throw new NullPointerException("The parameter image cannot be null!");
        }
        this.image = image;
    }
    
    /**
     * Retrieves a part of the cape.
     * Imagine that the player faces you, the right part is the part that you can see next to his left arm (right arm for you).
     * The front is the visible part of the cape and the back is the other side, between the cape and the player.
     * 
     * @param orientation The orientation.
     * @return A buffered image resized to fit the orientation.
     * @since 1.0
     */
    public BufferedImage get(SkinOrientation orientation){
        if(orientation == null){
            throw new NullPointerException("The parameter orientation cannot be null!");
        }
        
        switch(orientation){
            case TOP:
                return this.image.getSubimage(1, 0, 10, 1);
            case BOTTOM:
                return this.image.getSubimage(10, 0, 10, 1);
            case RIGHT:
                return this.image.getSubimage(0, 1, 1, 16);
            case LEFT:
                return this.image.getSubimage(11, 1, 1, 16);
            case FRONT:
                return this.image.getSubimage(1, 1, 10, 16);
            case BACK:
                return this.image.getSubimage(12, 1, 10, 16);
            default:
                throw new RuntimeException("Uh, wrong constant! What are you doing?");
        }
    }
    
    /**
     * Retrieves the internal buffered image of the skin.
     * 
     * @return The skin as a buffered image.
     * @since 1.0
     */
    public BufferedImage getImage(){
        return this.image;
    }
}