package com.example.exemploapi.holders;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.exemploapi.R;

public class UsuarioHolder extends RecyclerView.ViewHolder {
    public TextView txtNome;
    public TextView txtEmail;

    public UsuarioHolder(View view){
        super(view);
        txtEmail = (TextView)view.findViewById(R.id.textEmail);
        txtNome = (TextView)view.findViewById(R.id.textNome);


    }
}