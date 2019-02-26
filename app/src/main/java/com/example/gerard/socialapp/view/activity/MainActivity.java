package com.example.gerard.socialapp.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.gerard.socialapp.AppAuth;
import com.example.gerard.socialapp.R;
import com.example.gerard.socialapp.model.User;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;
    public DatabaseReference mReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.sign_in).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

        comeIn();
    }

    void comeIn(){

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {

            AppAuth.getInstance().loadUser().setOnLoadCompleteListener(new AppAuth.OnLoadCompleteListener(){
                public void onComplete(User user){
                    if(user != null){
                        startActivity(new Intent(MainActivity.this, PostsActivity.class));
                        finish();
                    } else {
                        AppAuth.getInstance().createUser().setOnCreateCompleteListener(new AppAuth.OnCreateCompleteListener() {
                            @Override
                            public void onComplete() {
                                comeIn();
                            }
                        });
                    }
                }
            });
        }
    }

    void signIn(){
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(Arrays.asList(
                                new AuthUI.IdpConfig.GoogleBuilder().build(),
                                new AuthUI.IdpConfig.EmailBuilder().build()
                        ))
                        .build(),
                RC_SIGN_IN);
    }




    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                comeIn();
            }
        }
    }
}
