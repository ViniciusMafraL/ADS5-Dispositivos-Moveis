package com.example.lermensagemtexto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txtMensagem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtMensagem = (TextView) findViewById(R.id.txtMensagem);

        if(getIntent().getExtras() != null){
            String msg = getIntent().getExtras().getString(Intent.EXTRA_TEXT);
            txtMensagem.setText(msg);
        }
    }
}