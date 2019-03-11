package com.example.gerard.socialapp.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gerard.socialapp.AppAuth;
import com.example.gerard.socialapp.GlideApp;
import com.example.gerard.socialapp.ModificarActivity;
import com.example.gerard.socialapp.R;
import com.example.gerard.socialapp.model.User;
import com.google.firebase.auth.FirebaseAuth;

public class PerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        ImageView fotou = findViewById(R.id.fotoperfil);
        TextView nombreu = findViewById(R.id.nombreu);
        TextView nicku= findViewById(R.id.nicku);
        TextView emailu = findViewById(R.id.emailu);
        TextView biografiau = findViewById(R.id.biografiau);

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
                Intent intent = new Intent(PerfilActivity.this, ModificarActivity.class);
                startActivity(intent);
            }
        });


    }
}
