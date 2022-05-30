package com.example.projetofinal.models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Usuario {
    private Integer id;
    private String nome;
    private String email;
    private String senha;

    public Usuario() {
    }

    public Usuario(Integer id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }
    public static Usuario parseObject(String json){
        Usuario usuario = new Usuario();
        try{
           JSONObject obj = new JSONObject(json);
          // usuario.setId(obj.getInt("id"));
           usuario.setNome(obj.getString("nome"));
           usuario.setSenha(obj.getString("senha"));
           usuario.setEmail(obj.getString("email"));

           return usuario;
        }
        catch (Exception ex){
            return usuario;
        }
    }
    public static String parseJson(Usuario usuario){
        JSONObject obj = new JSONObject();
        try{
            obj.put("id",""+usuario.getId());
            obj.put("nome",usuario.getNome());
            obj.put("email",usuario.getEmail());
            obj.put("senha",usuario.getSenha());
            return obj.toString();
        }
        catch (Exception ex){
            return "";
        }
    }

    public static ArrayList<Usuario> parseArrayList(String json){
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try{
            JSONArray array = new JSONArray(json);
            for(int i = 0; i < array.length(); i++){
                Usuario usuario = new Usuario();
                JSONObject obj = array.getJSONObject(i);
                usuario.setEmail(obj.getString("email"));
                usuario.setNome(obj.getString("nome"));
                usuario.setId(obj.getInt("id"));
                usuario.setSenha(obj.getString("senha"));
                usuarios.add(usuario);
            }
            return usuarios;
        }
        catch (Exception ex){
            return null;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
