package com.example.exemplomultithread;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.exemplomultithread.utils.Utils;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    TextView textNumero;
    ProgressDialog dialog;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textNumero = findViewById(R.id.textNumero);
        imageView = (ImageView) findViewById(R.id.imageView);
    }

    public void buttonIniciarClick(View v){
        DownloadImagemAsync imgDownload = new DownloadImagemAsync();
        imgDownload.execute("https://upload.wikimedia.org/wikipedia/pt/thumb/8/86/Avatar_Aang.png/250px-Avatar_Aang.png");

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 10; i++) {
                    try {
                        final int num = i;
                        Thread.sleep(2000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textNumero.setText("" + num);
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


    }

    private class DownloadImagemAsync extends AsyncTask<String,String, Bitmap>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setMessage("Iniciando...");
            dialog.setTitle("Aguarde");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imageView.setImageBitmap(bitmap);
            dialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            dialog.setMessage(values[0]);
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                Thread.sleep(1000);
                publishProgress("Conectando com o servidor...");
                Thread.sleep(1000);
                publishProgress("Fazendo Download...");
                Thread.sleep(2000);
                return Utils.downloadImagem(strings[0]);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}


