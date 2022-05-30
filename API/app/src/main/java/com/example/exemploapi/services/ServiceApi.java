package com.example.exemploapi.services;

import android.util.Log;

import com.example.exemploapi.R;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class ServiceApi {
    private static String urlApi = "http://192.168.15.41:8080/";//http://localhost:8080/";

    public static String getService(String reqUrl, String method, String data) {

        String response = null;
        try {
            URL url = new URL(urlApi + reqUrl + data);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = convertStreamToString(in);
        } catch (Exception e) {
            Log.e("Service Api", "Exception: " + e.getMessage());
        }
        return response;

    }
    public static String deleteService(String reqUrl) {
        try {
            URL url = new URL(urlApi +reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");
            InputStream in = new BufferedInputStream(conn.getInputStream());
            return "OK";
        } catch (Exception e) {
            return null;
        }
    }
    public static String postService(String reqUrl, String data) {
        try {
            URL url = new URL(urlApi+reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestMethod("POST");
            OutputStream out = conn.getOutputStream();
            byte[] input = data.getBytes("utf-8");
            out.write(input, 0, input.length);
            int responseCode= conn.getResponseCode();
            if(responseCode == HttpsURLConnection.HTTP_CREATED)
                return "OK";
            return "Erro";
        } catch (Exception e) {
            return  null;
        }
    }

    public static String putService(String reqUrl, String data){
        try {
            URL url = new URL(urlApi +reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestMethod("PUT");
            OutputStream out = conn.getOutputStream();
            byte[] input = data.getBytes("utf-8");
            out.write(input, 0, input.length);
            int responseCode= conn.getResponseCode();
            if(responseCode == HttpsURLConnection.HTTP_NO_CONTENT)
                return "OK";

            return "Erro";
        } catch (Exception e) {
            return null;
        }
    }

    private static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}
