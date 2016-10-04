package com.appteamnith.hillffair.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appteamnith.hillffair.R;
import com.appteamnith.hillffair.modals.ClubEvent;

import java.util.ArrayList;

/**
 * Created by Akatsuki on 3/23/2016.
 */
public class ClubEventAdapter extends RecyclerView.Adapter<ClubEventAdapter.EventViewHolder> {

    ArrayList<ClubEvent> clubEventArrayList = new ArrayList<ClubEvent>();
    Context context;

    public ClubEventAdapter(ArrayList<ClubEvent> clubEventArrayList, Context context) {
        this.context = context;
        this.clubEventArrayList = clubEventArrayList;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.eventcard_view_layout, parent, false);
        EventViewHolder eventViewHolder = new EventViewHolder(view, clubEventArrayList, context);
        return eventViewHolder;
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {

        int index=2*position;
        ClubEvent clubEvent;
        Log.e("index: "+index,"position: "+position);
        if(index>=clubEventArrayList.size())return;
        clubEvent = clubEventArrayList.get(index);
        holder.clubImage1.setImageResource(clubEvent.getImage_id());
        holder.clubName1.setText(clubEvent.getName());

        Log.e("index: "+(1+index),"position: "+position);
        if(index+1>=clubEventArrayList.size())return;
        clubEvent = clubEventArrayList.get(index+1);
        holder.clubImage2.setImageResource(clubEvent.getImage_id());
        holder.clubName2.setText(clubEvent.getName());
    }

    @Override
    public int getItemCount() {
        return clubEventArrayList.size()/2;
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView clubImage1, clubImage2;
        TextView clubName1, clubName2;
        CardView card1,card2;
        Context context;
        ArrayList<ClubEvent> clubEventArrayList;

        public EventViewHolder(View view, ArrayList<ClubEvent> clubEventArrayList, Context context) {
            super(view);

            this.clubEventArrayList = clubEventArrayList;
            this.context = context;
            clubImage1 = (ImageView) view.findViewById(R.id.club_image1);
            clubName1 = (TextView) view.findViewById(R.id.club_name1);
            clubImage2 = (ImageView) view.findViewById(R.id.club_image2);
            clubName2 = (TextView) view.findViewById(R.id.club_name2);
            card1=(CardView)view.findViewById(R.id.card1);
            card2=(CardView)view.findViewById(R.id.card2);
            //view.setOnClickListener(this);
            card1.setOnClickListener(this);
            card2.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
           //open next act
            if(v.getId()== R.id.card1){
            ClubEvent clubEvent= clubEventArrayList.get(2*getAdapterPosition());
                Toast.makeText(context,"CARD1",Toast.LENGTH_SHORT).show();
                //next act
            }
            if(v.getId()== R.id.card2){
                ClubEvent clubEvent= clubEventArrayList.get(2*getAdapterPosition()+1);
                Toast.makeText(context,"CARD2",Toast.LENGTH_SHORT).show();
                //next act
            }
        }
    }
}
