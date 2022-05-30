package com.example.projetofinal.services;

import android.util.Log;

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
    private static String urlAPI = "http://172.17.57.198:8080/";

    public static String getService(String urlMetodo, String parametro){
        String response = "";
        try{
            URL url;
            if(parametro == "")
                url = new URL(urlAPI + urlMetodo);
            else
                url =new URL(urlAPI+urlMetodo+"/"+parametro);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = convertStreamToString(in);
            return response;
        }
        catch (Exception ex){
            Log.e("Service API",ex.getMessage());
            return null;
        }
    }
    public static String deleteService(String urlMethod){
        try {
            URL url = new URL(urlAPI + urlMethod);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");
            InputStream in = new BufferedInputStream(conn.getInputStream());
            return "OK";
        }
        catch (Exception ex) {
            return null;
        }
    }
    public static String postService(String urlMethod, String data){
        try {
            URL url = new URL(urlAPI +urlMethod);
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
            if(responseCode == HttpsURLConnection.HTTP_OK
                    || responseCode == HttpsURLConnection.HTTP_CREATED)
                return "OK";
            return "Erro";
        } catch (Exception e) {
            return null;
        }
    }
    public static String putService(String urlMethod, String data){
        try {
            URL url = new URL(urlAPI +urlMethod);
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
            if(responseCode == HttpsURLConnection.HTTP_OK
                    || responseCode == HttpsURLConnection.HTTP_NO_CONTENT)
                return "OK";
            return "Erro";
        } catch (Exception e) {
            return null;
        }
    }

    private static String convertStreamToString(InputStream in) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
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
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }        }        return sb.toString();
    }
}
