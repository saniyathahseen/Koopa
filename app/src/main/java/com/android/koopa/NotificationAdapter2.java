package com.android.koopa;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class NotificationAdapter2 extends RecyclerView.Adapter<NotificationAdapter2.MyViewHolder> implements View.OnClickListener
    { private Context mContext;
        private List<Bnotification> notifications;
        public NotificationAdapter2(Context mContext, List<Bnotification> notifications) {
            this.mContext = mContext;
            this.notifications = notifications;


        }

        public NotificationAdapter2() {

        }

        @Override
        public NotificationAdapter2.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.noticifation_card, parent, false);

            return new NotificationAdapter2.MyViewHolder(itemView);

        }

        @Override
        public void onBindViewHolder(NotificationAdapter2.MyViewHolder holder, int position) {
            Bnotification notificationlist = notifications.get(position);

            holder.buser.setText(notificationlist.getBookedworker());
            holder.bdate.setText(notificationlist.getWorkDate());
            holder.bsitee.setText(notificationlist.getWorksSite()+"");
            holder.bstatus.setText(notificationlist.getStatus()+"");


        }

        @Override
        public int getItemCount() {
            return notifications.size();
        }

        @Override
        public void onClick(View v) {

        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView buser, bdate, bsitee,bstatus;



            public MyViewHolder(View view) {
                super(view);
                buser = view.findViewById(R.id.tv1name);
                bdate = view.findViewById(R.id.tv1date);
                bsitee = view.findViewById(R.id.tv1site);
                bstatus=view.findViewById(R.id.tv1status);

                view.setOnClickListener(NotificationAdapter2.this);

            }
        }
    }
