package com.appteamnith.hillffair.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appteamnith.hillffair.R;
import com.appteamnith.hillffair.models.Dislike;
import com.appteamnith.hillffair.models.Likecount;
import com.appteamnith.hillffair.models.NewsfeedModel2;
import com.appteamnith.hillffair.utilities.APIINTERFACE;
import com.appteamnith.hillffair.utilities.Utils;
import com.bumptech.glide.Glide;
import com.like.LikeButton;
import com.like.OnLikeListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.appteamnith.hillffair.R.id.lyk_status;
import static com.appteamnith.hillffair.R.id.no_of_likes;


/**
 * Created by parvesh_dhull on 28/9/16.
 */


public class CardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private Context mContext;
    private ArrayList<NewsfeedModel2> list_card=new ArrayList<>();
    public static final int FOOTER_VIEW = 1;
    public static final int NORMAL_VIEW = 2;
    private View view;
    int l,count=0;




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
            l=position;
            final MyViewHolder h=(MyViewHolder) holder;
            final NewsfeedModel2 card = list_card.get(position);
            if(card!=null){
                if(card.getUsername()!=null&&!card.getUsername().isEmpty())
                    h.user_name.setText(card.getUsername());
                if(card.getDescription()!=null&&!card.getDescription().isEmpty())
                    h.user_msg.setText(card.getDescription());
                if(card.getTitle()!=null&&!card.getTitle().isEmpty())
                    h.title.setText(card.getTitle());
      if(card.getLikes()>=0)
        h.no_of_likes.setText(""+card.getLikes());
        /*
*/
        if(card.getDate()!=null&&!card.getDate().isEmpty()){

            String od = card.getDate();
            String fd = od.substring(0,10);
            SimpleDateFormat odFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            SimpleDateFormat ndFormat = new SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH);
            try {
                Date date = odFormat.parse(fd);
                String nd = ndFormat.format(date);
                h.post_date.setText(nd);
            }
            catch (ParseException e){

            }
        }

                if(card.getPhoto()!=null&&!card.getPhoto().isEmpty())
                    Glide.with(mContext).load(card.getPhoto()).into(h.post_img);
                else
                    h.post_img.setImageResource(R.drawable.photo1);
                     h.lyk_status.setOnLikeListener(new OnLikeListener() {
                         @Override
                         public void liked(LikeButton likeButton) {

                             APIINTERFACE mApiService = Utils.getRetrofitService();
                             Call<Likecount> mservice = mApiService.likecount(card.get_id());

                             mservice.enqueue(new Callback<Likecount>() {
                                 @Override
                                 public void onResponse(Call<Likecount> call, Response<Likecount> response) {
                                     Likecount likes = response.body();
                                     if (likes != null && response.isSuccess()) {
                                         if (likes.isSuccess()) {
                                             Log.d("id",card.get_id());
                                             Toast.makeText(mContext, "Success", Toast.LENGTH_SHORT).show();
                                             h.no_of_likes.setText("" + (likes.getLikes()));
                                         } else {
                                             Toast.makeText(mContext, "Internal Error", Toast.LENGTH_SHORT).show();

                                         }
                                     }
                                 }

                                 @Override
                                 public void onFailure(Call<Likecount> call, Throwable t) {

                                     Toast.makeText(mContext, "Check Your Internet Connectivity and Permissions", Toast.LENGTH_SHORT).show();
                                 }
                             });
                         }

                         @Override
                         public void unLiked(LikeButton likeButton) {

                             APIINTERFACE mApiService = Utils.getRetrofitService();
                             Call<Dislike> mservice = mApiService.dislike(card.get_id());

                             mservice.enqueue(new Callback<Dislike>() {
                                 @Override
                                 public void onResponse(Call<Dislike> call, Response<Dislike> response) {
                                     Dislike likes1 = response.body();
                                     if (likes1 != null && response.isSuccess()) {
                                         if (likes1.isSuccess()) {

                                             Log.d("id",card.get_id());
                                             Toast.makeText(mContext, "Success", Toast.LENGTH_SHORT).show();
                                             h.no_of_likes.setText("" + (likes1.getDisLikes()));
                                         } else {
                                             Toast.makeText(mContext, "Internal Error", Toast.LENGTH_SHORT).show();

                                         }
                                     }
                                 }

                                 @Override
                                 public void onFailure(Call<Dislike> call, Throwable t) {

                                     Toast.makeText(mContext, "Check Your Internet Connectivity and Permissions", Toast.LENGTH_SHORT).show();
                                 }
                             });
                         }
                     });

       /* holder.lyk_status.setLiked(card.getLyk_status());*/

            }


        }

    }

    @Override
    public int getItemCount() {
        return list_card.size()+1;
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
      final   public TextView user_name, no_of_likes, user_msg, post_date ,title;
        final public ImageView post_img;

       final public com.like.LikeButton lyk_status;



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
        //     @Override
     //   public void onClick(View v) {
       //     NewsfeedModel2 card = list_card.get(position);
         //   APIINTERFACE mApiService = Utils.getRetrofitService();
           // Call<Likecount> mService = mApiService.likecount();
        //}
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