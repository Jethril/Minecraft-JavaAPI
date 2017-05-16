/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package fr.eclipseonfire.mjapi.implementations.http;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Utility class.
 *
 * @author Arthur MILLE
 * @version 1.0
 */
final class Utilities {

    private Utilities() {}

    static HttpURLConnection openConnection(URL url, Proxy proxy) throws IOException {
        HttpURLConnection result = (HttpURLConnection) url.openConnection(proxy);
        result.setConnectTimeout(15000);
        result.setReadTimeout(15000);
        result.setUseCaches(false);
        return result;
    }

    static String performGET(HttpURLConnection connection) throws IOException {
        try (InputStream in = connection.getInputStream()) {
            return toString(in);
        }
        catch (IOException e) {
            try (InputStream in = connection.getErrorStream()) {
                if (in == null) {
                    throw e;
                }

                return toString(in);
            }
        }
    }

    static String performPOST(byte[] data, String contentType, HttpURLConnection connection) throws IOException {
        connection.setRequestProperty("Content-Type", contentType + "; charset=utf-8");
        connection.setRequestProperty("Content-Length", String.valueOf(data.length));
        connection.setDoOutput(true);

        try (InputStream in = new ByteArrayInputStream(data); OutputStream out = connection.getOutputStream()) {
            copy(in, out);
        }

        try (InputStream in = connection.getInputStream()) {
            return toString(in);
        }
        catch (IOException e) {
            try (InputStream in = connection.getErrorStream()) {
                if (in == null) {
                    throw e;
                }
                return toString(in);
            }
        }
    }

    static URL createURL(String str) {
        try {
            return new URL(str);
        }
        catch (MalformedURLException e) {
            throw new RuntimeException("Cannot create URL", e);
        }
    }

    static void read(InputStream source) throws IOException {
        for (int read = source.read(); read != -1; read = source.read()) {
        }
    }

    static void copy(InputStream source, OutputStream out) throws IOException {
        for (int read = source.read(); read != -1; read = source.read()) {
            out.write(read);
        }
        out.flush();
    }

    static void copy(InputStream source, OutputStream... out) throws IOException {
        for (int read = source.read(); read != -1; read = source.read()) {
            for (OutputStream i : out) {
                i.write(read);
            }
        }

        for (OutputStream i : out) {
            i.flush();
        }
    }

    static String toString(InputStream source) throws IOException {
        return toString(source, StandardCharsets.UTF_8);
    }

    static String toString(InputStream source, Charset charset) throws IOException {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            copy(source, out);
            return new String(out.toByteArray(), charset);
        }
    }

    static void closeSilently(Closeable c) {
        try {
            c.close();
        }
        catch (Exception ignored) {
        }
    }
}