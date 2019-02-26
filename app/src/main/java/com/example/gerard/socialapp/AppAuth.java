package com.example.gerard.socialapp;

import android.support.annotation.NonNull;

import com.example.gerard.socialapp.model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AppAuth {

    static AppAuth appAuth;
    OnLoadCompleteListener onLoadCompleteListener;
    OnCreateCompleteListener onCreateCompleteListener;
    User user;


    public interface OnLoadCompleteListener {
        void onComplete(User user);
    }

    public interface OnCreateCompleteListener {
        void onComplete();
    }

    public static AppAuth getInstance(){
        if(appAuth == null){
            appAuth = new AppAuth();
        }
        return appAuth;
    }

    public AppAuth loadUser(){
        FirebaseDatabase.getInstance().getReference().child("users").child(FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(User.class);
                onLoadCompleteListener.onComplete(user);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return appAuth;
    }

    public AppAuth createUser(){

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        User user = new User();
        user.nombre=firebaseUser.getDisplayName();
        user.email=firebaseUser.getEmail();

        if(firebaseUser.getPhotoUrl() != null) {
            user.usuarioPhotoUrl = firebaseUser.getPhotoUrl().toString();
        }else{
            user.usuarioPhotoUrl = "https://firebasestorage.googleapis.com/v0/b/socialpet-5ef36.appspot.com/o/avatar%2Favatargenerico.jpg?alt=media&token=0aefb380-2896-4ea6-becc-acffecd52581";
        }

        FirebaseDatabase.getInstance().getReference()
                .child("users")
                .child(firebaseUser.getUid())
                .setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                onCreateCompleteListener.onComplete();
            }
        });
        return appAuth;
    }

    public AppAuth modifyUser(User user){
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase.getInstance().getReference()
                .child("users")
                .child(firebaseUser.getUid())
                .setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                onCreateCompleteListener.onComplete();
            }
        });
        return appAuth;
    }

    public void setOnLoadCompleteListener(OnLoadCompleteListener onLoadCompleteListener){
        this.onLoadCompleteListener = onLoadCompleteListener;
    }

    public void setOnCreateCompleteListener(OnCreateCompleteListener onCreateCompleteListener){
        this.onCreateCompleteListener = onCreateCompleteListener;
    }

    public User getUser(){
        return user;
    }
}
