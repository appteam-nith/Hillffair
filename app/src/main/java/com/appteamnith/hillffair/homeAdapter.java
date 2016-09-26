package com.appteamnith.hillffair;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by lenovo on 9/25/2016.
 */
public class homeAdapter extends RecyclerView.Adapter<homeAdapter.viewHolder> {

    private List<Integer> arrayList;
    private Context context;

    public homeAdapter(List<Integer> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.home_list_layout,parent,false);
        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {

        if(arrayList.get(position)!=null&&arrayList.get(position)!=0)
            holder.imageView.setImageResource(arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        public viewHolder(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.home_list_image);
        }
    }
}
