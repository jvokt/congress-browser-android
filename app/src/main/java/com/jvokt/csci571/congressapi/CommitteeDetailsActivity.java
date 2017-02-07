package com.jvokt.csci571.congressapi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by jvokt on 11/27/16.
 */
public class CommitteeDetailsActivity extends AppCompatActivity {
    private TextView tvId, tvName, tvChamber, tvParentCommitteeId, tvPhone, tvOffice;
    private ImageButton favorite;
    private Committee committee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_committee_details);
        Gson gson = new Gson();
        String json = getIntent().getExtras().getString(CommitteeRowAdapter.ITEM_KEY);
        committee = gson.fromJson(json, Committee.class);
        if (committee == null) {
            throw new AssertionError("Null data item received!");
        }
        tvId = (TextView) findViewById(R.id.tvId);
        tvName = (TextView) findViewById(R.id.tvName);
        tvChamber = (TextView) findViewById(R.id.tvChamber);
        tvParentCommitteeId = (TextView) findViewById(R.id.tvParentCommitteeId);
        tvPhone = (TextView) findViewById(R.id.tvPhone);
        tvOffice = (TextView) findViewById(R.id.tvOffice);
        favorite = (ImageButton) findViewById(R.id.favorite);

        setIfNotNull(tvId, committee.committee_id.toUpperCase());
        setIfNotNull(tvName, committee.name);
        setIfNotNull(tvChamber, firstLetterToUpperCase(committee.chamber));
        setIfNotNull(tvParentCommitteeId, committee.parent_committee_id != null ?
                committee.parent_committee_id.toUpperCase() : null);
        setIfNotNull(tvPhone, committee.phone);
        setIfNotNull(tvOffice, committee.office);

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
        SharedPreference<Committee> sharedPreference = new SharedPreference(committee);
        List<Committee> favorites = sharedPreference.getFavorites(getApplicationContext());
        if (favorites != null) {
            for (Committee other : favorites)
                if (other.equals(committee))
                    return true;
        }
        return false;
    }
    public void onToggleStar(View view) {
        SharedPreference<Committee> sharedPreference = new SharedPreference(committee);
        if (checkFavorite()) {
            sharedPreference.removeFavorite(getApplicationContext(), committee);
            Picasso.with(getApplicationContext()).load(android.R.drawable.btn_star_big_off).resize(0, 35).into(favorite);
        } else {
            sharedPreference.addFavorite(getApplicationContext(), committee);
            Picasso.with(getApplicationContext()).load(android.R.drawable.btn_star_big_on).resize(0, 35).into(favorite);
        }
    }
}
