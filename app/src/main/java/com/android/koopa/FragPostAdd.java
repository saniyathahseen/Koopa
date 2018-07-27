package com.android.koopa;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class FragPostAdd extends Fragment implements View.OnClickListener{
    View view;

    EditText Description,Date;
    Button Post;
    String description,date;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    DatabaseReference mydb;
    FirebaseDatabase myfb;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.layout,container,false);
        Description=view.findViewById(R.id.Description);
      Date=view.findViewById(R.id.Date);
        Post=view.findViewById(R.id.post);

        progressDialog=new ProgressDialog(getContext());
        Post.setOnClickListener(this);



        return view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.post) {
            myfb = FirebaseDatabase.getInstance();
            mydb = myfb.getReference("Posts");

            description = Description.getText().toString();
            date = new Date().toString();

            SharedPreferences sp = getContext().getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            String Uid = sp.getString("Uid", "");

            final DatabaseReference userId = mydb.child(Uid).push();
            userId.child("description").setValue(description);
            userId.child("date").setValue(date);

            progressDialog.setMessage("Adding post to feed");
            progressDialog.show();

            Toast t = Toast.makeText(getActivity(), "Post Added", Toast.LENGTH_SHORT);
            t.show();

            progressDialog.dismiss();

            Description.setText("");
            Date.setText("");


        }
    }
}
