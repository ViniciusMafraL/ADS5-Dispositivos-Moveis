package com.example.exemploapi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.exemploapi.models.Usuario;
import com.example.exemploapi.services.ServiceApi;
import com.example.exemploapi.services.UsuarioService;

public class CadUsuario extends AppCompatActivity {
    int id = 0;
    Usuario usuario;
    TextView textNome, textEmail, textSenha;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_usuario);
            textEmail = findViewById(R.id.txtEmail);
            textNome = findViewById(R.id.txtNome);
            textSenha = findViewById(R.id.txtSenha);
            if(getIntent().hasExtra("id")){
                id = getIntent().getIntExtra("id",0);
                new UsuarioService("GET",CadUsuario.this).execute("api/usuario/",""+id);
            }
    }

    public void carregarCampos(){
        textEmail.setText(usuario.getEmail());
        textSenha.setText(usuario.getSenha());
        textNome.setText(usuario.getNome());
    }

    public void btnSalvarClick(View v){
        Log.e("teste","click");
        if(id > 0) {
            usuario.setId(id);
            usuario.setNome(textNome.getText().toString());
            usuario.setEmail(textEmail.getText().toString());
            usuario.setSenha(textSenha.getText().toString());
            new UsuarioService("PUT",CadUsuario.this).execute("api/usuario/" + id, Usuario.parseJson(usuario));
        }
        else{
            usuario = new Usuario();
            usuario.setNome(textNome.getText().toString());
            usuario.setEmail(textEmail.getText().toString());
            usuario.setSenha(textSenha.getText().toString());
            usuario.setId(0);
            new UsuarioService("POST",CadUsuario.this).execute("api/usuario", Usuario.parseJson(usuario));
        }
    }

}