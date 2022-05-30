package com.example.projetofinal.services;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.projetofinal.MainActivity;
import com.example.projetofinal.models.Usuario;

public class ListaUsuarioAsync extends AsyncTask<String,String,String> {
    private String metodo;
    ProgressDialog progressDialog;
    Context context;

    public ListaUsuarioAsync(String metodo, Context context){
        this.metodo = metodo;
        this.progressDialog = new ProgressDialog(context);
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Carregando...");
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if(metodo.equals("GET")){
           ((MainActivity)context).setListaUsuarios(Usuario.parseArrayList(s));
            ((MainActivity)context).setupRecyclerUsuario();
            progressDialog.dismiss();
        }
        else if(s.equals("OK")){
            Toast.makeText(context,"Operação realizada com sucesso",Toast.LENGTH_SHORT)
                                                                                .show();
            progressDialog.dismiss();
            ((MainActivity)context).buscarUsuarios();
        }

    }
    @Override
    protected String doInBackground(String... strings) {
        String data = "";
        if(metodo.equals("GET"))
            data = ServiceApi.getService(strings[0],strings[1]);
        else if(metodo.equals("DELETE"))
            data = ServiceApi.deleteService(strings[0]);
        return data;
    }
}
