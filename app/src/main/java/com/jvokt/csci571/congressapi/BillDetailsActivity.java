package com.jvokt.csci571.congressapi;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by jvokt on 11/27/16.
 */
public class BillDetailsActivity extends AppCompatActivity {
    private TextView tvId, tvTitle, tvType, tvSponsor, tvChamber, tvIntroduced, tvStatus,
            tvVersionStatus, tvCongressURL, tvBillURL;
    private ImageButton favorite;
    private Bill bill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_details);
//        bill = getIntent().getExtras().getParcelable(BillRowAdapter.ITEM_KEY);
        Gson gson = new Gson();
        String json = getIntent().getExtras().getString(BillRowAdapter.ITEM_KEY);
        bill = gson.fromJson(json, Bill.class);
        if (bill == null) {
            throw new AssertionError("Null data item received!");
        }
        tvId = (TextView) findViewById(R.id.tvId);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvType = (TextView) findViewById(R.id.tvType);
        tvSponsor = (TextView) findViewById(R.id.tvSponsor);
        tvChamber = (TextView) findViewById(R.id.tvChamber);
        tvIntroduced = (TextView) findViewById(R.id.tvIntroduced);
        tvStatus = (TextView) findViewById(R.id.tvStatus);
        tvVersionStatus = (TextView) findViewById(R.id.tvVersionStatus);
        tvCongressURL = (TextView) findViewById(R.id.tvCongressURL);
        tvBillURL = (TextView) findViewById(R.id.tvBillURL);
        favorite = (ImageButton) findViewById(R.id.favoriteBill);

        setIfNotNull(tvId, bill.bill_id.toUpperCase());
//        setIfNotNull(tvTitle, bill.official_title);
        if (bill.short_title != null) {
            tvTitle.setText(bill.short_title);
        } else if (bill.official_title != null) {
            tvTitle.setText(bill.official_title);
        } else {
            tvTitle.setText("N.A");
        }
        setIfNotNull(tvType, bill.bill_type.toUpperCase());
        setIfNotNull(tvSponsor, bill.sponsor.title + ". " + bill.sponsor.last_name +
                ", " + bill.sponsor.first_name);
        setIfNotNull(tvChamber, firstLetterToUpperCase(bill.chamber));
        setIfNotNull(tvIntroduced, formatDate(bill.introduced_on));
        setIfNotNull(tvStatus, bill.history.active ? "Active" : "New");
        setIfNotNull(tvVersionStatus, bill.last_version.version_name);
        setIfNotNull(tvCongressURL, bill.urls.congress);
        setIfNotNull(tvBillURL, bill.last_version.urls.pdf);
        if (checkFavorite()) {
            Picasso.with(getApplicationContext()).load(android.R.drawable.btn_star_big_on).resize(0, 35).into(favorite);
        } else {
            Picasso.with(getApplicationContext()).load(android.R.drawable.btn_star_big_off).resize(0, 35).into(favorite);
        }
    }
    private void setIfNotNull(TextView tv, String value) {
        if (value != null) {
            tv.setText(value);
        } else {
            tv.setText("N.A");
        }
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

    private String firstLetterToUpperCase(String chamber) {
        if (chamber == null || chamber.length() < 2)
            return null;
        return chamber.substring(0,1).toUpperCase() + chamber.substring(1);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            super.onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public boolean checkFavorite() {
        SharedPreference<Bill> sharedPreference = new SharedPreference(bill);
        List<Bill> favorites = sharedPreference.getFavorites(getApplicationContext());
        if (favorites != null) {
            for (Bill other : favorites)
                if (other.equals(bill))
                    return true;
        }
        return false;
    }
    public void onToggleStar(View view) {
        SharedPreference<Bill> sharedPreference = new SharedPreference(bill);
        if (checkFavorite()) {
            sharedPreference.removeFavorite(getApplicationContext(), bill);
            Picasso.with(getApplicationContext()).load(android.R.drawable.btn_star_big_off).resize(0, 35).into(favorite);
        } else {
            sharedPreference.addFavorite(getApplicationContext(), bill);
            Picasso.with(getApplicationContext()).load(android.R.drawable.btn_star_big_on).resize(0, 35).into(favorite);
        }
    }
}
