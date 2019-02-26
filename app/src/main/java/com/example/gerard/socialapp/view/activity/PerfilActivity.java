package com.example.gerard.socialapp.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.gerard.socialapp.AppAuth;
import com.example.gerard.socialapp.R;
import com.example.gerard.socialapp.model.User;

public class PerfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);


       // AppAuth.getInstance().modifyUser(user).set
    }
}
