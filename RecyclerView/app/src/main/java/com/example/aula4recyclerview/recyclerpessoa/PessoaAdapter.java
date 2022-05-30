package com.example.aula4recyclerview.recyclerpessoa;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aula4recyclerview.R;
import com.example.aula4recyclerview.dataset.PessoaDataset;
import com.example.aula4recyclerview.models.Pessoa;

import java.util.ArrayList;

public class PessoaAdapter extends RecyclerView.Adapter<PessoaHolder> {
    private ArrayList<Pessoa> pessoas;

    public PessoaAdapter(ArrayList<Pessoa> pessoas){
        this.pessoas = pessoas;
    }

    @NonNull
    @Override
    public PessoaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PessoaHolder(
        LayoutInflater.from(parent.getContext()).inflate(
                R.layout.card_item_pessoa,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PessoaHolder holder, int position) {
        holder.nome.setText(pessoas.get(position).getNome());
        holder.telefone.setText(pessoas.get(position).getTelefone());
        holder.buttonExcluir.setOnClickListener(
                                            view -> excluirItem(position));
    }
    public void addItem(Pessoa pessoa){
        PessoaDataset.addPessoa(pessoa);
        notifyItemInserted(getItemCount());
    }
    private void excluirItem(int position){
        pessoas.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, pessoas.size());
    }

    @Override
    public int getItemCount() {
        return pessoas != null ? pessoas.size() : 0;
    }
}
