package com.example.projetofinal.services;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.projetofinal.CadUsuarioActivity;
import com.example.projetofinal.MainActivity;
import com.example.projetofinal.models.Usuario;

public class CadUsuarioAsync extends AsyncTask<String,String,String> {
    private String metodo;
    ProgressDialog progressDialog;
    Context context;

    public CadUsuarioAsync(String metodo, Context context){
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
            ((CadUsuarioActivity)context).setUsuario(Usuario.parseObject(s));
            ((CadUsuarioActivity)context).carregarCampos();
            progressDialog.dismiss();
        }
        else if(s.equals("OK")){
            Toast.makeText(context,"Salvo com sucesso!",Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
            ((CadUsuarioActivity)context).finish();
        }
        else {
            Toast.makeText(context,"Erro ao salvar!",Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        }

    }
    @Override
    protected String doInBackground(String... strings) {
        String data = "";
        if(metodo.equals("GET"))
            data = ServiceApi.getService(strings[0],strings[1]);
        else if(metodo.equals("PUT"))
            data = ServiceApi.putService(strings[0],strings[1]);
        else if(metodo.equals("POST"))
            data = ServiceApi.postService(strings[0],strings[1]);

        return data;
    }
}
