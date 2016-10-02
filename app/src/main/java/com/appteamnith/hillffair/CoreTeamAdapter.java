package com.appteamnith.hillffair;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Nitin on 9/28/16.
 */
public class CoreTeamAdapter extends RecyclerView.Adapter<CoreTeamAdapter.ViewHolder>{

    ArrayList<CoreTeamItem> array_list;
    Context context;

    public CoreTeamAdapter(ArrayList<CoreTeamItem> array_list,Context context){
        this.array_list=array_list;
        this.context=context;
    }

    @Override
    public void onBindViewHolder(ViewHolder view_Holder,int i){
        if(!(array_list.get(i).name.isEmpty()) && array_list.get(i).name.length()!=0){
            view_Holder.name.setText(array_list.get(i).name);
        }

        if(!(array_list.get(i).designation.isEmpty()) && array_list.get(i).designation.length()!=0){
            view_Holder.designation.setText(array_list.get(i).designation);
        }
    }

    @Override
    public int getItemCount(){
        return array_list.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup view_group,int i){
        View view= LayoutInflater.from(view_group.getContext()).inflate(R.layout.card_view_layout,view_group,false);
        ViewHolder view_holder=new ViewHolder(view);
        return view_holder;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public final TextView name,designation;
        public ViewHolder(View v){
            super(v);
            this.name=(TextView)v.findViewById(R.id.core_team_name);
            this.designation=(TextView)v.findViewById(R.id.core_team_designation);
        }
    }
}
