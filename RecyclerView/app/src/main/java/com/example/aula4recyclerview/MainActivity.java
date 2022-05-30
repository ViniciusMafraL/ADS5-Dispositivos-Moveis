package com.example.aula4recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.aula4recyclerview.dataset.PessoaDataset;
import com.example.aula4recyclerview.models.Pessoa;
import com.example.aula4recyclerview.recyclerpessoa.PessoaAdapter;

public class MainActivity extends AppCompatActivity {
    private RecyclerView listPessoa;
    private PessoaAdapter pessoaAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listPessoa = (RecyclerView) findViewById(R.id.listPessoa);
        setupRecyclerPessoa();
    }
    public void buttonAddClick(View view){
        pessoaAdapter.addItem(new Pessoa(999,"ITEM ADD","444444"));
    }
    public void setupRecyclerPessoa(){
        //formato em lista vertical
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        //formato em grid
       // GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        //formato em lista horizontal
       // LinearLayoutManager layoutManager = new LinearLayoutManager(
        //                               this,LinearLayoutManager.HORIZONTAL,false);

        listPessoa.setLayoutManager(layoutManager);
        pessoaAdapter = new PessoaAdapter(PessoaDataset.getItens());
        listPessoa.setAdapter(pessoaAdapter);
        listPessoa.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));

    }
}