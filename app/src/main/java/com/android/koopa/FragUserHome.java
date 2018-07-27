package com.android.koopa;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.net.URL;

import static android.content.Context.MODE_PRIVATE;

public class FragUserHome extends Fragment {

    View view;
    private StorageReference storage;

    ImageView imageview;

    SharedPreferance prefs ;
    TextView Uname;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fraguserhome,container,false);

        storage = FirebaseStorage.getInstance().getReference();

        imageview=view.findViewById(R.id.profile);

        SharedPreferences sp=getContext().getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        String name=sp.getString("Name","");
        String UID=sp.getString("Uid","");

        StorageReference ref = storage.child("images/"+ UID.toString());
        ref.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                String ImageUrl=task.getResult().toString();
                Glide.with(getContext())
                        .load(ImageUrl)
                        .into(imageview);
            }
        });



        Uname=view.findViewById(R.id.textView4);
        Uname.setText(name);
        return view;
    }

}
