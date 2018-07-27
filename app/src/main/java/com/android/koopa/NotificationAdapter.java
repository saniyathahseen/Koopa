package com.android.koopa;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> implements View.OnClickListener{
    private Context mContext;
    DatabaseReference myRef;
    FirebaseDatabase myfb;
    private List<Booking> bookings;
    public NotificationAdapter(Context mContext, List<Booking> bookings) {
        this.mContext = mContext;
        this.bookings = bookings;
        myfb = FirebaseDatabase.getInstance();

    }

    public NotificationAdapter() {

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.noticifation_card, parent, false);

        return new  MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Booking bookinglist = bookings.get(position);
        holder.buser.setText(bookinglist.getBookedUser());
        holder.bdate.setText(bookinglist.getWorkDate());
        holder.bsitee.setText(bookinglist.getWorksSite()+"");
        holder.baccept.setTag(position);
        holder.bregect.setTag(position);
    }

    @Override
    public int getItemCount() {
        return bookings.size();
    }

    @Override
    public void onClick(View v) {
        final int position = (int) v.getTag();
        final Booking currentLBooking =  bookings.get(position);
        if(v.getId()==R.id.baccept) {

            myRef=myfb.getReference("Notification").child(currentLBooking.getBookedUserId());
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    myRef.child(dataSnapshot.getKey()).child("status").setValue("Accepted");
                    Toast toast=Toast.makeText(mContext,"Job Accepted",Toast.LENGTH_SHORT);
                    toast.show();
                    myRef.removeEventListener(this);
                    bookings.remove(position);
                    notifyDataSetChanged();
                        }@Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });


        }
        if(v.getId()==R.id.bregect) {
            myRef=myfb.getReference("Notification").child(currentLBooking.getBookedUserId());
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    myRef.child(dataSnapshot.getKey()).child("status").setValue("Rejected");
                    Toast toast=Toast.makeText(mContext,"Job Rejected",Toast.LENGTH_SHORT);
                    toast.show();
                    myRef.removeEventListener(this);
                    bookings.remove(position);
                    notifyDataSetChanged();
                }@Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });


        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView buser, bdate, bsitee;
        public Button baccept, bregect;




        public MyViewHolder(View view) {
            super(view);
            buser = view.findViewById(R.id.tvname);
            bdate = view.findViewById(R.id.tvdate);
            bsitee = view.findViewById(R.id.tvsite);
            baccept=(Button) view.findViewById(R.id.baccept);
            bregect=(Button) view.findViewById(R.id.bregect);
            view.setOnClickListener(NotificationAdapter.this);
            baccept.setOnClickListener(NotificationAdapter.this);
            bregect.setOnClickListener(NotificationAdapter.this);

        }
    }
}
