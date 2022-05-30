package com.example.exemploapi.adapters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.exemploapi.CadUsuario;
import com.example.exemploapi.MainActivity;
import com.example.exemploapi.R;
import com.example.exemploapi.holders.UsuarioHolder;
import com.example.exemploapi.models.Usuario;

import java.util.ArrayList;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioHolder> {
    private final ArrayList<Usuario> usuarios;

    public UsuarioAdapter(ArrayList<Usuario> usuarios){
        this.usuarios = usuarios;
    }

    @NonNull
    @Override
    public UsuarioHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UsuarioHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listagem_usuario,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioHolder holder, int position) {
        holder.txtNome.setText(usuarios.get(holder.getAdapterPosition()).getNome());
        holder.txtEmail.setText(usuarios.get(holder.getAdapterPosition()).getEmail());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
                alert.setMessage("Deseja excluir esse item?");
                alert.setTitle("Atenção");
                alert.setIcon(R.mipmap.ic_launcher);
                alert.setNegativeButton("Não",null);
                alert.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((MainActivity)holder.itemView.getContext()).deleteUsuario (usuarios.get(holder.getAdapterPosition()).getId() +"");
                    }
                });
                alert.show();

                return true;
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                Intent i = new Intent(holder.itemView.getContext(), CadUsuario.class);
                i.putExtra("id",usuarios.get(holder.getAdapterPosition()).getId());
                holder.itemView.getContext().startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return usuarios != null ? usuarios.size() : 0;
    }
}