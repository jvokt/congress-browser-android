package com.jvokt.csci571.congressapi;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by jvokt on 11/23/16.
 */
public class LegislatorRowAdapter extends RecyclerView.Adapter<LegislatorRowAdapter.ViewHolder> {
    public static final String ITEM_KEY = "item_key";
    private List<Legislator> mItems;
    private Context mContext;

    public LegislatorRowAdapter(Context context, List<Legislator> items) {
        this.mContext = context;
        this.mItems = items;
    }

    @Override
    public LegislatorRowAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.list_legislator_row, parent, false);
        LegislatorRowAdapter.ViewHolder viewHolder = new LegislatorRowAdapter.ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LegislatorRowAdapter.ViewHolder holder, int position) {
//        Log.i("LegislatorRowAdapter","position: " + position);
        final Legislator legislator = mItems.get(position);
        /*
        holder.tvName.setText(legislator.getTitle() + ". " + legislator.getLastName() + ", " + legislator.getFirstName()
                + "; State: " + legislator.getStateName());
        */
//        holder.imageView.setImageResource(R.drawable.apple_pie);
        // ImageView img = (ImageView) view.findViewById(R.id.img);
        if (legislator.getBioguideId() != null) {
            String url = "https://theunitedstates.io/images/congress/original/" + legislator.getBioguideId() + ".jpg";
            Picasso p = Picasso.with(mContext);
            p.load(url).tag(position).resize(100, 122).into(holder.imageView);
//            p.setIndicatorsEnabled(true);
            if (position > 7) {
                p.pauseTag(position);
            }
//            Picasso.with(mContext).load(url).tag(position).resize(100, 122).into(holder.imageView);
//            Picasso.with(mContext).load(url).tag(holder.mView.getContext()).resize(100, 122).into(holder.imageView);
//            Picasso.with(mContext).load(url).resize(100, 122).into(holder.imageView);
//            Picasso.with(mContext).load(url).into(holder.imageView);
        } else {
            holder.imageView.setImageResource(R.drawable.apple_pie);
        }
        holder.tvName.setText(legislator.getTitle() + ". " + legislator.getLastName() + ", " + legislator.getFirstName());
        holder.tvDetails.setText("(" + (legislator.getParty() != null ? legislator.getParty() : "N.A.") + ")"
        + legislator.getStateName() + " - District " + legislator.getDistrict());
        /*
        // holder.tvName.setText(item.getItemName());
        // holder.imageView.setImageResource(R.drawable.apple_pie);
        try {
            holder.tvName.setText(item.getItemName());
            String imageFile = item.getImage();
            InputStream inputStream = mContext.getAssets().open(imageFile);
            Drawable d = Drawable.createFromStream(inputStream, null);
            holder.imageView.setImageDrawable(d);
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(mContext, "You selected " + item.getItemName(),
//                        Toast.LENGTH_SHORT).show();
//                String itemId = item.getItemId();
                Intent intent = new Intent(mContext, LegislatorDetailsActivity.class);
                intent.putExtra(ITEM_KEY, legislator);
                // TabLayout tabLayout = (TabLayout) v.findViewById(R.id.tabLayout);
                // /intent.putExtra("tabPos", tabLayout.getSelectedTabPosition());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        public TextView tvDetails;
        public ImageView imageView;
        public View mView;
        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.itemNameText);
            tvDetails = (TextView) itemView.findViewById(R.id.itemDetails);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            mView = itemView;
        }
    }
}
