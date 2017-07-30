package com.sample.cache.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by msanaulla on 12/24/2014.
 */
public class Util {

    private static ObjectMapper objectMapper = new ObjectMapper();
    private static Properties properties = null;

    public static ObjectMapper getObjectMapper(){
        return objectMapper;
    }

    public static Properties getProperties() throws IOException {
        if ( properties != null){
            return  properties;
        }
        properties = new Properties();
        InputStream inputStream = Util.class.getClassLoader().getResourceAsStream("application.properties");
        properties.load(inputStream);
        return properties;
    }

    public static String getHttpResponse(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        conn.setConnectTimeout(5000);
        //conn.setReadTimeout(20000);

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));

        StringBuilder outputBuilder = new StringBuilder();
        String output;
        while ((output = br.readLine()) != null) {
            outputBuilder.append(output);
        }
        conn.disconnect();
        return outputBuilder.toString();
    }
}
