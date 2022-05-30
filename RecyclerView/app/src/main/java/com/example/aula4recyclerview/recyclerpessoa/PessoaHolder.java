package com.example.aula4recyclerview.recyclerpessoa;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.aula4recyclerview.R;

public class PessoaHolder extends RecyclerView.ViewHolder {
    public TextView nome;
    public TextView telefone;
    public ImageButton buttonExcluir;

    public PessoaHolder(View itemView){
        super(itemView);
        nome = (TextView) itemView.findViewById(R.id.textNome);
        telefone = (TextView)itemView.findViewById(R.id.textTelefone);
        buttonExcluir=(ImageButton)itemView.findViewById(R.id.buttonExcluir);

    }
}
