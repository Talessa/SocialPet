package com.example.gerard.socialapp.model;

import android.net.Uri;

public class User {

    public String nombre;
    public String nick;
    public String email;
    public String biografia;
    public String usuarioPhotoUrl;

    public User() {
    }

    public User(String nombre, String nick, String email, String biografia, String usuarioPhotoUrl) {
        this.nombre = nombre;
        this.nick = nick;
        this.email = email;
        this.biografia = biografia;
        this.usuarioPhotoUrl = usuarioPhotoUrl;
    }
}
