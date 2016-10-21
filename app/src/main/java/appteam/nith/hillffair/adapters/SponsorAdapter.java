package appteam.nith.hillffair.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import appteam.nith.hillffair.R;
import appteam.nith.hillffair.models.SponsorItem;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.ImageViewTarget;

import java.util.ArrayList;

/**
 * Created by jaykay12 on 5/10/16.
 */
public class SponsorAdapter extends RecyclerView.Adapter<SponsorAdapter.ViewHolder>{

    ArrayList<SponsorItem> sponsorItemArrayList;
    Context context;

    public SponsorAdapter(ArrayList<SponsorItem> sponsorItemArrayList,Context context)
    {
      this.sponsorItemArrayList = sponsorItemArrayList;
      this.context = context;
    }


    @Override
    public int getItemCount() {
        return sponsorItemArrayList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sponsor_cardview,parent,false);
        ViewHolder view_holder = new ViewHolder(view);
        return view_holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        if(!(sponsorItemArrayList.get(position).sponsor_name.isEmpty())){
            holder.sponsorname.setText(sponsorItemArrayList.get(position).sponsor_name);
        }

        if(!(sponsorItemArrayList.get(position).image_id==null)){
           // holder.sponsorimage.setImageResource(sponsorItemArrayList.get(position).image_id);
            Glide.with(context).load(sponsorItemArrayList.get(position).image_id).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL).into(new ImageViewTarget<Bitmap>(holder.sponsorimage) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable drawable= RoundedBitmapDrawableFactory.create(context.getResources(),resource);
                    drawable.setCircular(false);
                    holder.sponsorimage.setImageDrawable(drawable);
                }
            });
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public final TextView sponsorname;
        ImageView sponsorimage;
        public ViewHolder(View v){
            super(v);
            this.sponsorimage = (ImageView)v.findViewById(R.id.imgSponsor);
            this.sponsorname = (TextView)v.findViewById(R.id.tvSponsorName);
        }
    }
}
