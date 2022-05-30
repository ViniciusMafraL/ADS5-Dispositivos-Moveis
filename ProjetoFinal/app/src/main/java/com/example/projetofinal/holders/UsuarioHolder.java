package com.example.projetofinal.holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetofinal.R;

public class UsuarioHolder extends RecyclerView.ViewHolder {
    public TextView txtEmail;
    public TextView txtNome;

    public UsuarioHolder(@NonNull View itemView) {
        super(itemView);
        txtEmail = (TextView)itemView.findViewById(R.id.textEmail);
        txtNome = (TextView) itemView.findViewById(R.id.textNome);
    }
}
