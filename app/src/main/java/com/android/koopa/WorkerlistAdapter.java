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


import com.bumptech.glide.Glide;

import java.util.List;

public class WorkerlistAdapter extends RecyclerView.Adapter<WorkerlistAdapter.MyViewHolder> implements View.OnClickListener{
    private Context mContext;
    private List<Workerlist> workerlist;

    public WorkerlistAdapter(Context mContext, List<Workerlist> workerlist) {
        this.mContext = mContext;
        this.workerlist = workerlist;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_result_card, parent, false);

        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Workerlist worker = workerlist.get(position);

        holder.place.setText(worker.getSubdistrict());
        holder.name.setText(worker.getName());
        Glide.with(mContext)
                .load(worker.getImgurl())
                .into(holder.image1);

    }




    @Override
    public int getItemCount() {
        return workerlist.size();
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(mContext,"profile viewing",Toast.LENGTH_SHORT).show();


        Intent workerprofileintent=new Intent(mContext,WorkerProfile.class);
        mContext.startActivity(workerprofileintent);
    }

    private void getUid(String uid) {
        new SharedPreferance(mContext).getUid(uid);
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, job, rating ,place;
        public ImageView image1,overflow;


        public MyViewHolder(View view) {
            super(view);
            image1 = view.findViewById(R.id.image);
            name = view.findViewById(R.id.name1);
            place = view.findViewById(R.id.job);
            view.setOnClickListener(WorkerlistAdapter.this);

        }







        /**
         * Showing popup menu when tapping on 3 dots
         */
        // inflate menu
//        private void showPopupMenu(View view) {
//
//        }


        /**
         * Click listener for popup menu items
         */

        class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

            public MyMenuItemClickListener() {
            }

            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
//                case R.stateId.it1:
//                    Intent in=new Intent(this.class,WorkerProfile.class);
//                    mContext.startActivity(in);
//                    return true;
//                case R.stateId.it2:
//                    Intent innt;
//                    in = new Intent(SearchResult.class,WorkerProfile.class);
//                    mContext.startActivity(innt);
//                default:
                }
                return false;
            }
        }
    }


}





