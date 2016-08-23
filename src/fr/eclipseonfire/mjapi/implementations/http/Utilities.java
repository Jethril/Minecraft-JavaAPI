/*
 * Copyright 2016 Arthur Mille.
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */
package fr.eclipseonfire.mjapi.implementations.http;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Utility class.
 * 
 * @author eclipseonfire
 * @version 1.0
 */
public final class Utilities{
    
    private Utilities(){}
    
    public static HttpURLConnection openConnection(URL url, Proxy proxy) throws IOException{
        HttpURLConnection result = (HttpURLConnection)url.openConnection(proxy);
        result.setConnectTimeout(15000);
        result.setReadTimeout(15000);
        result.setUseCaches(false);
        return result;
    }
    
    public static String performGET(HttpURLConnection connection) throws IOException{
        try(InputStream in = connection.getInputStream()){
            return toString(in);
        }
        catch(IOException e){
            try(InputStream in = connection.getErrorStream()){
                if(in == null){
                    throw e;
                }
                
                return toString(in);
            }
        }
    }
    
    public static String performPOST(byte[] data, String contentType, HttpURLConnection connection) throws IOException{
        connection.setRequestProperty("Content-Type", contentType + "; charset=utf-8");
        connection.setRequestProperty("Content-Length", String.valueOf(data.length));
        connection.setDoOutput(true);

        try(InputStream in = new ByteArrayInputStream(data); OutputStream out = connection.getOutputStream()){
            copy(in, out);
        }

        try(InputStream in = connection.getInputStream()){
            return toString(in);
        }
        catch(IOException e){
            try(InputStream in = connection.getErrorStream()){
                if(in == null){
                    throw e;
                }
                return toString(in);
            }
        }
    }
    
    public static URL createURL(String str){
        try{
            return new URL(str);
        }
        catch(MalformedURLException e){
            throw new RuntimeException("Cannot create URL", e);
        }
    }
    
	public static void read(InputStream source) throws IOException{
		for(int read = source.read(); read != -1; read = source.read()){}
	}
	
	public static void copy(InputStream source, OutputStream out) throws IOException{
		for(int read = source.read(); read != -1; read = source.read()){
			out.write(read);
		}
		out.flush();
	}
	
	public static void copy(InputStream source, OutputStream... out) throws IOException{
		for(int read = source.read(); read != -1; read = source.read()){
			for(OutputStream i : out){
				i.write(read);
			}
		}
		
		for(OutputStream i : out){
			i.flush();
		}
	}
	
	public static String toString(InputStream source) throws IOException{
		return toString(source, StandardCharsets.UTF_8);
	}
	
	public static String toString(InputStream source, Charset charset) throws IOException{
		try(ByteArrayOutputStream out = new ByteArrayOutputStream()){
			copy(source, out);
			return new String(out.toByteArray(), charset);
		}
	}
    
    public static void closeSilently(Closeable c){
        try{
            c.close();
        }
        catch(Exception ignored){}
    }
}