package com.example.gerard.socialapp.model;

import android.net.Uri;

public class User {
    public String uid;
    public String nombre;
    public String nick;
    public String email;
    public String biografia;
    public Uri usuarioPhotoUrl;

    public User() {
    }

    public User(String uid, String nombre, String nick, String email, String biografia, Uri usuarioPhotoUrl) {
        this.uid = uid;
        this.nombre = nombre;
        this.nick = nick;
        this.email = email;
        this.biografia = biografia;
        this.usuarioPhotoUrl = usuarioPhotoUrl;
    }
}
