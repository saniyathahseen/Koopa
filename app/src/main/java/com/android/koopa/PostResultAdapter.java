package com.android.koopa;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class PostResultAdapter extends RecyclerView.Adapter<PostResultAdapter.MyViewHolder> implements View.OnClickListener{
    private Context mContext;
    private List<Postlist> postlists;
    DatabaseReference mydb,myref;
    FirebaseDatabase myfb;

    public PostResultAdapter(Context mContext, List<Postlist> postlists) {
        this.mContext = mContext;
        this.postlists = postlists;
        myfb = FirebaseDatabase.getInstance();
        mydb = myfb.getReference("Posts");
    }
    public PostResultAdapter() {

    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_resultcard, parent, false);

        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Postlist postlist = postlists.get(position);
        holder.pdiscription.setText(postlist.getDescription());
        holder.namep.setText(postlist.getName());
        holder.date.setText(postlist.getDate()+"");
        holder.mainView.setTag(position);


    }


    @Override
    public int getItemCount() {
        return postlists.size();
    }

    @Override
    public void onClick(View v) {
             int position= (int) v.getTag();
             profileView(postlists.get(position).getId());
            Intent profintent=new Intent(mContext,WorkerProfile.class);
            mContext.startActivity(profintent);



    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView pdiscription, namep, date;
        public View mainView;



        public MyViewHolder(View view) {
            super(view);
            mainView=view;
            namep = view.findViewById(R.id.puser);
            pdiscription = view.findViewById(R.id.pdiscription);
            date = view.findViewById(R.id.dateofpost);

            view.setOnClickListener(PostResultAdapter.this);

        }
        }
    private void profileView(final String uid) {
        mydb = myfb.getReference("users");
        mydb.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                user value=dataSnapshot.getValue(user.class);
                if(dataSnapshot.getKey().equalsIgnoreCase(uid)){
                    new SharedPreferance(mContext).profileView(uid,value.getName(),value.getCategory(),value.getSubdistrict());
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}





