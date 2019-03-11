package com.example.gerard.socialapp;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.gerard.socialapp.model.User;
import com.example.gerard.socialapp.view.activity.MainActivity;
import com.example.gerard.socialapp.view.activity.PerfilActivity;
import com.example.gerard.socialapp.view.activity.PostsActivity;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

public class ModificarActivity extends AppCompatActivity {

    Uri mediaUri = null;
    String uri;
    static final int RC_IMAGE_PICK = 9000;
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
            uri = AppAuth.getInstance().getUser().usuarioPhotoUrl;
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
                uploadPhotoAndPublish();
                User user = new User();
                user.nombre=nombreu.getText().toString();
                user.nick=nicku.getText().toString();
                user.email=emailu.getText().toString();
                user.biografia=biografiau.getText().toString();
                user.usuarioPhotoUrl=uri;

                AppAuth.getInstance().modifyUser(user).setOnCreateCompleteListener(new AppAuth.OnCreateCompleteListener() {
                    @Override
                    public void onComplete() {
                        Intent intent = new Intent(
                                ModificarActivity.this, PostsActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });
        findViewById(R.id.btnImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI), RC_IMAGE_PICK);
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null) {
            Log.e("abc", "guay");
            mediaUri = data.getData();

            if (requestCode == RC_IMAGE_PICK) {
                Log.e("abc", "yeaaa " + mediaUri.toString());
                ImageView foto = findViewById(R.id.fotoperfil);
                GlideApp.with(this).load(mediaUri).into(foto);
            }
        }
    }
    void uploadPhotoAndPublish(){
        FirebaseStorage.getInstance().getReference("avatar/")
                .putFile(mediaUri)
                .continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        return task.getResult().getStorage().getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    // URL DE DESCARGA
                    uri = task.getResult().toString();
                }
            }
        });
    }
}
