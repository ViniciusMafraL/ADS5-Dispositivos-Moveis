package com.example.exemplomultithread.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Utils {
    public static Bitmap downloadImagem(String url) throws IOException {
        URL enderecoImagem = new URL(url);
        InputStream inputStream = enderecoImagem.openStream();
        Bitmap imagem = BitmapFactory.decodeStream(inputStream);
        inputStream.close();
        return imagem;
    }
}