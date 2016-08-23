/*
 * Copyright 2016 Arthur Mille.
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package fr.eclipseonfire.mjapi.interfaces;

import java.awt.image.BufferedImage;

/**
 * This class represents the minecraft skin.
 * 
 * @author eclipseonfire
 * @version 1.0
 */
public class MinecraftSkin{
    
    /**
     * The skin image.
     * 
     * @since 1.0
     */
    protected BufferedImage image;
    
    /**
     * If the skin uses a slim model or not.
     * 
     * @since 1.0
     */
    protected boolean slim;
    
    /**
     * If the skin is alex or not.
     * 
     * @since 1.0
     */
    protected boolean alex;
    
    /**
     * Constructs a minecraft skin with a buffered image.
     * 
     * @param image The skin as a buffered image.
     * @param slim If the skin is slim or not.
     * @param alex If the skin is Alex or not.
     * @since 1.0
     */
    public MinecraftSkin(BufferedImage image, boolean slim, boolean alex){
        if(image == null){
            throw new NullPointerException("The parameter image cannot be null!");
        }
        this.image = image;
        this.slim = slim;
        this.alex = alex;
    }
    
    /**
     * @return <code>true</code> if the player uses the old skin model (skins &lt; mc 1.8), <code>false</code> otherwise.
     * @since 1.0
     */
    public boolean hasOldSkinModel(){
        return this.image.getWidth() == 64 && this.image.getHeight() == 32;
    }
    
    /**
     * @return <code>true</code> if the player has the Alex skin, or <code>false</code> if he has the Steve one.
     * @since 1.0
     */
    public boolean isAlex(){
        return this.alex;
    }
    
    /**
     * Retrieves the head part of the skin, with the specified orientation.
     * 
     * @param orientation The orientation.
     * @return A buffered image resized to fit the orientation.
     * @since 1.0
     */
    public BufferedImage getHead(SkinOrientation orientation){
        if(orientation == null){
            throw new NullPointerException("The parameter orientation cannot be null!");
        }
        
        switch(orientation){
            case TOP:
                return this.image.getSubimage(8, 0, 8, 8);
            case RIGHT:
                return this.image.getSubimage(0, 8, 8, 8);
            case BOTTOM:
                return this.image.getSubimage(16, 0, 8, 8);
            case LEFT:
                return this.image.getSubimage(16, 8, 8, 8);
            case FRONT:
                return this.image.getSubimage(8, 8, 8, 8);
            case BACK:
                return this.image.getSubimage(24, 8, 8, 8);
            default:
                throw new RuntimeException("Uh, wrong constant! What are you doing?");
        }
    }
    
    /**
     * Retrieves the body part of the skin, with the specified orientation.
     * 
     * @param orientation The orientation.
     * @return A buffered image resized to fit the orientation.
     * @since 1.0
     */
    public BufferedImage getBody(SkinOrientation orientation){
        if(orientation == null){
            throw new NullPointerException("The parameter orientation cannot be null!");
        }
        
        switch(orientation){
            case TOP:
                return this.image.getSubimage(20, 16, 8, 4);
            case RIGHT:
                return this.image.getSubimage(16, 20, 4, 12);
            case BOTTOM:
                return this.image.getSubimage(28, 16, 8, 4);
            case LEFT:
                return this.image.getSubimage(28, 20, 4, 12);
            case FRONT:
                return this.image.getSubimage(20, 20, 8, 12);
            case BACK:
                return this.image.getSubimage(32, 20, 8, 12);
            default:
                throw new RuntimeException("Uh, wrong constant! What are you doing?");
        }
    }
    
    /**
     * Retrieves the arm part of the skin, with a specific position (left, right) and an orientation.
     * 
     * @param pos The position (left, right).
     * @param orientation The orientation.
     * @return A buffered image resized to fit the orientation.
     * @since 1.0
     */
    public BufferedImage getArm(SkinPosition pos, SkinOrientation orientation){
        if(pos == null){
            throw new NullPointerException("The parameter pos cannot be null!");
        }
        
        if(orientation == null){
            throw new NullPointerException("The parameter orientation cannot be null!");
        }
        
        //We compute x and y to have a good position if the leg is right, left or if the skin model is the old one (< 1.8).
        final int x, y;
        
        if(pos.equals(SkinPosition.RIGHT) || this.hasOldSkinModel()){
            x = 40;
            y = 16;
        }
        else{
            x = 32;
            y = 48;
        }
        
        switch(orientation){
            case TOP:
                return this.image.getSubimage(x + 4, y, this.slim ? 3 : 4, 4);
            case RIGHT:
                return this.image.getSubimage(x, y + 4, 4, 12);
            case BOTTOM:
                return this.image.getSubimage(this.slim ? x + 7 : x + 8, y, this.slim ? 3 : 4, 4);
            case LEFT:
                return this.image.getSubimage(this.slim ? x + 7 : x + 8, y + 4, 4, 12);
            case FRONT:
                return this.image.getSubimage(x + 4, y + 4, this.slim ? 3 : 4, 12);
            case BACK:
                return this.image.getSubimage(this.slim ? x + 11 : x + 12, y + 4, this.slim ? 3 : 4, 12);
            default:
                throw new RuntimeException("Uh, wrong constant! What are you doing?");
        }
    }
    
    /**
     * Retrieves the leg part of the skin, with a specific position (left, right) and an orientation.
     * 
     * @param pos The position (left, right).
     * @param orientation The orientation.
     * @return A buffered image resized to fit the orientation.
     * @since 1.0
     */
    public BufferedImage getLeg(SkinPosition pos, SkinOrientation orientation){
        if(pos == null){
            throw new NullPointerException("The parameter pos cannot be null!");
        }
        
        if(orientation == null){
            throw new NullPointerException("The parameter orientation cannot be null!");
        }
        
        //We compute x and y to have a good position if the leg is right, left or if the skin model is the old one (< 1.8).
        final int x, y;
        
        if(pos.equals(SkinPosition.RIGHT) || this.hasOldSkinModel()){
            x = 0;
            y = 16;
        }
        else{
            x = 16;
            y = 48;
        }
        
        switch(orientation){
            case TOP:
                return this.image.getSubimage(x + 4, y, 4, 4);
            case RIGHT:
                return this.image.getSubimage(x, y + 4, 4, 12);
            case BOTTOM:
                return this.image.getSubimage(x + 8, y, 4, 4);
            case LEFT:
                return this.image.getSubimage(x + 8, y + 4, 4, 12);
            case FRONT:
                return this.image.getSubimage(x + 4, y + 4, 4, 12);
            case BACK:
                return this.image.getSubimage(x + 12, y + 4, 4, 12);
            default:
                throw new RuntimeException("Uh, wrong constant! What are you doing?");
        }
    }
    
    /**
     * Retrieves the head overlay part of the skin, with the specified orientation.
     * 
     * @param orientation The orientation.
     * @return A buffered image resized to fit the orientation.
     * @since 1.0
     */
    public BufferedImage getHeadOverlay(SkinOrientation orientation){
        if(orientation == null){
            throw new NullPointerException("The parameter orientation cannot be null!");
        }
        
        switch(orientation){
            case TOP:
                return this.image.getSubimage(40, 0, 8, 8);
            case RIGHT:
                return this.image.getSubimage(48, 8, 8, 8);
            case BOTTOM:
                return this.image.getSubimage(48, 0, 8, 8);
            case LEFT:
                return this.image.getSubimage(32, 8, 8, 8);
            case FRONT:
                return this.image.getSubimage(40, 8, 8, 8);
            case BACK:
                return this.image.getSubimage(56, 8, 8, 8);
            default:
                throw new RuntimeException("Uh, wrong constant! What are you doing?");
        }
    }
    
    /**
     * Retrieves the body overlay part of the skin, with the specified orientation.
     * 
     * @param orientation The orientation.
     * @return A buffered image resized to fit the orientation.
     * @since 1.0
     */
    public BufferedImage getBodyOverlay(SkinOrientation orientation){
        if(orientation == null){
            throw new NullPointerException("The parameter orientation cannot be null!");
        }
        
        if(this.hasOldSkinModel()){
            return null;
        }
        
        switch(orientation){
            case TOP:
                return this.image.getSubimage(20, 16, 8, 4);
            case RIGHT:
                return this.image.getSubimage(16, 20, 4, 12);
            case BOTTOM:
                return this.image.getSubimage(28, 16, 8, 4);
            case LEFT:
                return this.image.getSubimage(28, 20, 4, 12);
            case FRONT:
                return this.image.getSubimage(20, 20, 8, 12);
            case BACK:
                return this.image.getSubimage(32, 20, 8, 12);
            default:
                throw new RuntimeException("Uh, wrong constant! What are you doing?");
        }
    }
    
    /**
     * Retrieves the arm overlay part of the skin, with a specific position (left, right) and an orientation.
     * 
     * @param pos The position (left, right).
     * @param orientation The orientation.
     * @return A buffered image resized to fit the orientation, or <code>null</code> if the player has the old skin model.
     * @since 1.0
     */
    public BufferedImage getArmOverlay(SkinPosition pos, SkinOrientation orientation){
        if(pos == null){
            throw new NullPointerException("The parameter pos cannot be null!");
        }
        
        if(orientation == null){
            throw new NullPointerException("The parameter orientation cannot be null!");
        }
        
        if(this.hasOldSkinModel()){
            return null;
        }
        
        //We compute x and y to have a good position if the leg is right, left or if the skin model is the old one (< 1.8).
        final int x, y;
        
        if(pos.equals(SkinPosition.RIGHT)){
            x = 40;
            y = 32;
        }
        else{
            x = 48;
            y = 48;
        }
        
        switch(orientation){
            case TOP:
                return this.image.getSubimage(x + 4, y, this.slim ? 3 : 4, 4);
            case RIGHT:
                return this.image.getSubimage(x, y + 4, 4, 12);
            case BOTTOM:
                return this.image.getSubimage(this.slim ? x + 7 : x + 8, y, this.slim ? 3 : 4, 4);
            case LEFT:
                return this.image.getSubimage(this.slim ? x + 7 : x + 8, y + 4, 4, 12);
            case FRONT:
                return this.image.getSubimage(x + 4, y + 4, this.slim ? 3 : 4, 12);
            case BACK:
                return this.image.getSubimage(this.slim ? x + 11 : x + 12, y + 4, this.slim ? 3 : 4, 12);
            default:
                throw new RuntimeException("Uh, wrong constant! What are you doing?");
        }
    }
    
    /**
     * Retrieves the leg overlay part of the skin, with a specific position (left, right) and an orientation.
     * 
     * @param pos The position (left, right).
     * @param orientation The orientation.
     * @return A buffered image resized to fit the orientation, or <code>null</code> if the player has the old skin model.
     * @since 1.0
     */
    public BufferedImage getLegOverlay(SkinPosition pos, SkinOrientation orientation){
        if(pos == null){
            throw new NullPointerException("The parameter pos cannot be null!");
        }
        
        if(orientation == null){
            throw new NullPointerException("The parameter orientation cannot be null!");
        }
        
        if(this.hasOldSkinModel()){
            return null;
        }
        
        //We compute x and y to have a good position if the leg is right, left or if the skin model is the old one (< 1.8).
        final int x, y;
        
        if(pos.equals(SkinPosition.RIGHT)){
            x = 0;
            y = 16;
        }
        else{
            x = 16;
            y = 48;
        }
        
        switch(orientation){
            case TOP:
                return this.image.getSubimage(x + 4, y, 4, 4);
            case RIGHT:
                return this.image.getSubimage(x, y + 4, 4, 12);
            case BOTTOM:
                return this.image.getSubimage(x + 8, y, 4, 4);
            case LEFT:
                return this.image.getSubimage(x + 8, y + 4, 4, 12);
            case FRONT:
                return this.image.getSubimage(x + 4, y + 4, 4, 12);
            case BACK:
                return this.image.getSubimage(x + 12, y + 4, 4, 12);
            default:
                throw new RuntimeException("Uh, wrong constant! What are you doing?");
        }
    }
    
    /**
     * @return <code>true</code> if this skin uses the slim model, <code>false</code> otherwise.
     * @since 1.0
     */
    public boolean isSlim(){
        return this.slim;
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