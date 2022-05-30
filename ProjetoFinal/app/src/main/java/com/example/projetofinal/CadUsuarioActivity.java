package com.example.projetofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.projetofinal.models.Usuario;
import com.example.projetofinal.services.CadUsuarioAsync;
import com.example.projetofinal.services.ListaUsuarioAsync;

public class CadUsuarioActivity extends AppCompatActivity {
    int id = 0;
    Usuario usuario;
    TextView txtEmail, txtSenha, txtNome;

    public Usuario getUsuario(){
        return  usuario;
    }
    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_usuario);
        txtEmail = findViewById(R.id.txtEmail);
        txtSenha = findViewById(R.id.txtSenha);
        txtNome = findViewById(R.id.txtNome);
        if(getIntent().hasExtra("id")){
            id = getIntent().getIntExtra("id",0);
            new CadUsuarioAsync("GET",CadUsuarioActivity.this).execute("api/usuario/" +id,"");
        }
        else
            usuario = new Usuario();
    }
    public void carregarCampos(){
        txtNome.setText(usuario.getNome());
        txtSenha.setText(usuario.getSenha());
        txtEmail.setText(usuario.getEmail());
    }

    public void btnSalvarClick(View v){
        usuario.setId(id);
        usuario.setNome(txtNome.getText().toString());
        usuario.setEmail(txtEmail.getText().toString());
        usuario.setSenha(txtSenha.getText().toString());

        if(id > 0){
            new CadUsuarioAsync("PUT",CadUsuarioActivity.this)
                    .execute("api/usuario/" + id,Usuario.parseJson(usuario));
        }
        else{
           new CadUsuarioAsync("POST",CadUsuarioActivity.this)
                    .execute("api/usuario",Usuario.parseJson(usuario));
        }
    }
}