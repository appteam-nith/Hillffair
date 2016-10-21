package appteam.nith.hillffair.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import appteam.nith.hillffair.R;
import appteam.nith.hillffair.models.BattleDayItem;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

/**
 * Created by sukhbir on 19/10/16.
 */

public class BattleDayAdapter extends RecyclerView.Adapter<BattleDayAdapter.ViewHolder>{

    ArrayList<BattleDayItem> arrayList=new ArrayList<>();
    Context context;

    public BattleDayAdapter(Context context)
    {
        this.context = context;
    }
    public void refresh(ArrayList<BattleDayItem> list){
        arrayList=list;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_battleday,parent,false);
        ViewHolder view_holder = new ViewHolder(view);
        return view_holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        
       if(arrayList!=null){
           if(arrayList.get(position).getName()!=null){
               holder.event_name.setText(arrayList.get(position).getName());
           }

           Glide.with(context).load(arrayList.get(position).getPhoto()).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.icon);
           
       }
        
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public final TextView event_name;
        ImageView icon;
        public ViewHolder(View v){
            super(v);
            this.icon = (ImageView)v.findViewById(R.id.icon);
            this.event_name = (TextView)v.findViewById(R.id.event_name);
        }
    }
}
