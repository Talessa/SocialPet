package com.example.gerard.socialapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.gerard.socialapp.model.User;
import com.example.gerard.socialapp.view.activity.MainActivity;
import com.example.gerard.socialapp.view.activity.PerfilActivity;
import com.example.gerard.socialapp.view.activity.PostsActivity;

public class ModificarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        final ImageView fotou = findViewById(R.id.fotoperfil);
        final EditText nombreu = findViewById(R.id.nombreu);
        final EditText nicku= findViewById(R.id.nicku);
        final EditText emailu = findViewById(R.id.emailu);
        final EditText biografiau = findViewById(R.id.biografiau);
        if(AppAuth.getInstance().getUser().usuarioPhotoUrl != null) {
            GlideApp.with(this)
                    .load(AppAuth.getInstance().getUser().usuarioPhotoUrl)
                    .into(fotou);
        }
        nombreu.setText(AppAuth.getInstance().getUser().nombre);
        if (AppAuth.getInstance().getUser().nick != null){
            nicku.setText(AppAuth.getInstance().getUser().nick);
        }
        emailu.setText(AppAuth.getInstance().getUser().email);
        if (AppAuth.getInstance().getUser().biografia != null){
            biografiau.setText(AppAuth.getInstance().getUser().biografia);
        }

        findViewById(R.id.modificar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.nombre=nombreu.getText().toString();
                user.nick=nicku.getText().toString();
                user.email=emailu.getText().toString();
                user.biografia=biografiau.getText().toString();
                Intent intent = new Intent(
                        ModificarActivity.this, PostsActivity.class);
                startActivity(intent);
            }
        });
    }
}
