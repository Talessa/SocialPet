package com.example.gerard.socialapp.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.gerard.socialapp.R;
import com.google.firebase.auth.FirebaseAuth;

public class CreateCountActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_count);
        mAuth = FirebaseAuth.getInstance();
    }
}
