package com.jvokt.csci571.congressapi;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by jvokt on 11/27/16.
 */
public class BillRowAdapter extends RecyclerView.Adapter<BillRowAdapter.ViewHolder>{
    public static final String ITEM_KEY = "item_key";
    private List<Bill> mItems;
    private Context mContext;
    public BillRowAdapter(Context context, List<Bill> items) {
        this.mContext = context;
        this.mItems = items;
    }

    @Override
    public BillRowAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.list_bill_row, parent, false);
        BillRowAdapter.ViewHolder viewHolder = new BillRowAdapter.ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Bill bill = mItems.get(position);
        holder.tvId.setText(bill.bill_id.toUpperCase());
//        holder.tvTitle.setText(bill.official_title);
        if (bill.short_title != null) {
            holder.tvTitle.setText(bill.short_title);
        } else if (bill.official_title != null) {
            holder.tvTitle.setText(bill.official_title);
        } else {
            holder.tvTitle.setText("N.A");
        }
        holder.tvIntroducedOn.setText(formatDate(bill.introduced_on));
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, BillDetailsActivity.class);
                Gson gson = new Gson();
                String json = gson.toJson(bill);
                intent.putExtra(ITEM_KEY, json);
                mContext.startActivity(intent);
            }
        });
    }
    private String formatDate(String termStart) {
        if (termStart == null)
            return null;
        SimpleDateFormat from = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = from.parse(termStart);
            SimpleDateFormat to = new SimpleDateFormat("MMM dd, yyyy");
            return to.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public int getItemCount() {
        return mItems.size();
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
