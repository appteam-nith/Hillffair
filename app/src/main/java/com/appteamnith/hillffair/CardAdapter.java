package com.appteamnith.hillffair;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;


/**
 * Created by parvesh_dhull on 28/9/16.
 */


public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyViewHolder> {

    private Context mContext;
    private List<newsfeed_model2> list_card;

    public CardAdapter(Context mContext, List<newsfeed_model2> list_card) {
        this.mContext = mContext;
        this.list_card = list_card;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        newsfeed_model2 card = list_card.get(position);
        if(card.user.getName()!=null&&!card.user.getName().isEmpty())
        holder.user_name.setText(card.user.getName());
        if(card.getDescription()!=null&&!card.getDescription().isEmpty())
        holder.user_msg.setText(card.getDescription());
        if(card.getTitle()!=null&&!card.getTitle().isEmpty())
            holder.user_msg.setText(card.getTitle());
 /*       if(card.getNo_of_likes()!=0)
        holder.no_of_likes.setText(""+card.getNo_of_likes());*/
        /*
        if(card.getPost_date()!=null&&!card.getPost_date().isEmpty())
        holder.post_date.setText(card.getPost_date());
        */
        if(card.getPhoto()!=null&&!card.getPhoto().isEmpty())
        Glide.with(mContext).load(card.getPhoto()).into(holder.post_img);
        else
        holder.post_img.setImageResource(R.drawable.photo);

       /* holder.lyk_status.setLiked(card.getLyk_status());*/
    }

    @Override
    public int getItemCount() {
        return list_card.size();
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
}