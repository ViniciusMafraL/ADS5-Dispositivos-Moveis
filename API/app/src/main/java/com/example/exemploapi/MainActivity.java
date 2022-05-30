package com.example.exemploapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.exemploapi.adapters.UsuarioAdapter;
import com.example.exemploapi.models.Usuario;
import com.example.exemploapi.services.UsuarioService;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Usuario> listaUsuarios;
    RecyclerView recyclerUsuario;
    UsuarioAdapter usuarioAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerUsuario = (RecyclerView)findViewById(R.id.recyclerUsuario);
        listaUsuarios = new ArrayList<>();
    }
    public void addUsuarioClick(View view){
        Intent i = new Intent(MainActivity.this, CadUsuario.class);
        startActivity(i);
    }


    @Override
    protected void onResume() {
        super.onResume();
        buscarUsuarios();
    }
    public void deleteUsuario(String id){
        new UsuarioService("DELETE",MainActivity.this).execute("/api/usuario/" + id);
    }

    public void setListaUsuarios(ArrayList<Usuario> lista){
        listaUsuarios = lista;
    }

    public void buscarUsuarios(){
        new UsuarioService("GET",MainActivity.this).execute("api/usuario","");
    }

    public void setupRecyclerUsuario(){
        //Configurando o layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerUsuario.setLayoutManager(layoutManager);

        // add adapter
        usuarioAdapter = new UsuarioAdapter(listaUsuarios);
        recyclerUsuario.setAdapter(usuarioAdapter);

        //divisor entre linhas
        recyclerUsuario.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
    }



}