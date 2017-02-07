package com.jvokt.csci571.congressapi;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

/**
 * Created by jvokt on 11/27/16.
 */
public class CommitteeRowAdapter extends RecyclerView.Adapter<CommitteeRowAdapter.ViewHolder>{
    public static final String ITEM_KEY = "item_key";
    private List<Committee> mItems;
    private Context mContext;
    public CommitteeRowAdapter(Context context, List<Committee> items) {
        this.mContext = context;
        this.mItems = items;
    }

    @Override
    public CommitteeRowAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.list_bill_row, parent, false);
        CommitteeRowAdapter.ViewHolder viewHolder = new CommitteeRowAdapter.ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CommitteeRowAdapter.ViewHolder holder, int position) {
        final Committee committee = mItems.get(position);
        holder.tvId.setText(committee.committee_id.toUpperCase());
        holder.tvTitle.setText(committee.name);
        holder.tvIntroducedOn.setText(firstLetterToUpperCase(committee.chamber));
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CommitteeDetailsActivity.class);
                Gson gson = new Gson();
                String json = gson.toJson(committee);
                intent.putExtra(ITEM_KEY, json);
                mContext.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return mItems.size();
    }

    private String firstLetterToUpperCase(String chamber) {
        if (chamber == null || chamber.length() < 2)
            return null;
        return chamber.substring(0,1).toUpperCase() + chamber.substring(1);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvId, tvTitle, tvIntroducedOn;
        public View mView;
        public ViewHolder(View itemView) {
            super(itemView);
            tvId = (TextView) itemView.findViewById(R.id.tvId);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvIntroducedOn = (TextView) itemView.findViewById(R.id.tvIntroducedOn);
            mView = itemView;
        }
    }
}
