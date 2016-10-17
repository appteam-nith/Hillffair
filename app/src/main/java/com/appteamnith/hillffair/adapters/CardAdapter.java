package com.appteamnith.hillffair.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.appteamnith.hillffair.R;
import com.appteamnith.hillffair.models.NewsfeedModel2;
import com.bumptech.glide.Glide;

import java.util.ArrayList;


/**
 * Created by parvesh_dhull on 28/9/16.
 */


public class CardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;

    private ArrayList<NewsfeedModel2> list_card=new ArrayList<>();
    public static final int FOOTER_VIEW = 1;
    public static final int NORMAL_VIEW = 2;
    private View view;




    public CardAdapter(Context mContext) {

        this.mContext = mContext;
    }


    public  void  refresh(ArrayList<NewsfeedModel2> list){
        list_card=list;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == FOOTER_VIEW&&list_card.size()!=0) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_progress, parent, false);
            return new footerView(view);
        }
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof MyViewHolder&&getItemViewType(position)!=FOOTER_VIEW)
        {
            MyViewHolder h=(MyViewHolder) holder;
            NewsfeedModel2 card = list_card.get(position);
            if(card!=null){
                if(card.getUsername()!=null&&!card.getUsername().isEmpty())
                    h.user_name.setText(card.getUsername());
                if(card.getDescription()!=null&&!card.getDescription().isEmpty())
                    h.user_msg.setText(card.getDescription());
                if(card.getTitle()!=null&&!card.getTitle().isEmpty())
                    h.title.setText(card.getTitle());
 /*       if(card.getNo_of_likes()!=0)
        holder.no_of_likes.setText(""+card.getNo_of_likes());*/
        /*

        if(card.getPost_date()!=null&&!card.getPost_date().isEmpty())
        holder.post_date.setText(card.getPost_date());
        */
                if(card.getPhoto()!=null&&!card.getPhoto().isEmpty())
                    Glide.with(mContext).load(card.getPhoto()).into(h.post_img);
                else
                    h.post_img.setImageResource(R.drawable.photo1);

       /* holder.lyk_status.setLiked(card.getLyk_status());*/
            }


        }

    }

    @Override
    public int getItemCount() {
        return list_card.size()+1;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView user_name, no_of_likes, user_msg, post_date ,title;
        public ImageView post_img;
        public com.like.LikeButton lyk_status;

        public MyViewHolder(View view) {
            super(view);
            user_name = (TextView) view.findViewById(R.id.user_name);
            no_of_likes = (TextView) view.findViewById(R.id.no_of_likes);
            post_img = (ImageView) view.findViewById(R.id.post_img);
            post_date = (TextView) view.findViewById(R.id.post_date);
            user_msg = (TextView) view.findViewById(R.id.user_msg);
            lyk_status = (com.like.LikeButton) view.findViewById(R.id.lyk_status);
            title = (TextView)view.findViewById(R.id.post_title);

        }
    }

    @Override
    public int getItemViewType(int position) {
        return position>=list_card.size()?FOOTER_VIEW:NORMAL_VIEW;
    }

    public static class footerView extends RecyclerView.ViewHolder {
        public footerView(View itemView) {
            super(itemView);
        }
    }

    public void removeItem(NewsfeedModel2 item) {
        int indexOfItem = list_card.indexOf(item);
        if (indexOfItem != -1) {
            list_card.remove(indexOfItem);
            notifyItemRemoved(indexOfItem);
        }
    }
}