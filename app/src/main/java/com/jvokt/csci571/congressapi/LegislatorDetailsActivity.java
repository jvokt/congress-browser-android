package com.jvokt.csci571.congressapi;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by jvokt on 11/23/16.
 */
public class LegislatorDetailsActivity extends AppCompatActivity {
    private TextView partyName, name, email, chamber, contact, start, end, term, address, state, fax, birthday;
    private ImageView itemImage, partyImage;
    private ImageButton favorite;
    private ProgressBar termProgressBar;
    private Legislator legislator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legislator_details);
        legislator = getIntent().getExtras().getParcelable(LegislatorRowAdapter.ITEM_KEY);
        if (legislator == null) {
            throw new AssertionError("Null data item received!");
        }
        itemImage = (ImageView) findViewById(R.id.itemImage);

        if (legislator.getBioguideId() != null) {
            String url = "https://theunitedstates.io/images/congress/original/" + legislator.getBioguideId() + ".jpg";
            Picasso p = Picasso.with(getApplicationContext());
            p.load(url).resize(100, 122).into(itemImage);
        }

        partyImage = (ImageView) findViewById(R.id.partyImage);
        partyName = (TextView) findViewById(R.id.partyName);

        if (legislator.getParty().equals("D")) {
            Picasso.with(getApplicationContext()).load(R.drawable.d).resize(0,40).into(partyImage);
//            partyImage.setImageResource(R.drawable.d);
            partyName.setText("Democrat");
        } else if (legislator.getParty().equals("R")) {
            Picasso.with(getApplicationContext()).load(R.drawable.r).resize(0,40).into(partyImage);
//            partyImage.setImageResource(R.drawable.r);
            partyName.setText("Republican");
        } else {
            Picasso.with(getApplicationContext()).load(R.drawable.i).resize(0,40).into(partyImage);
//            partyImage.setImageResource(R.drawable.r);
            partyName.setText("Independent");
        }
        name = (TextView) findViewById(R.id.name);
        name.setText(legislator.getTitle() + ". " + legislator.getLastName() + ", " + legislator.getFirstName());
        email = (TextView) findViewById(R.id.email);
        setIfNotNull(email, legislator.getOcEmail());
        chamber = (TextView) findViewById(R.id.chamber);
        setIfNotNull(chamber, firstLetterToUpperCase(legislator.getChamber()));
        contact = (TextView) findViewById(R.id.contact);
        setIfNotNull(contact, legislator.getPhone());
        start = (TextView) findViewById(R.id.start);
        setIfNotNull(start, formatDate(legislator.getTermStart()));
        end = (TextView) findViewById(R.id.end);
        setIfNotNull(end, formatDate(legislator.getTermEnd()));
        term = (TextView) findViewById(R.id.term);
        SimpleDateFormat from = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null, endDate = null;
        try {
            startDate = from.parse(legislator.getTermStart());
            endDate = from.parse(legislator.getTermEnd());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int percent = getPercentageCompleted(startDate, endDate);
        setIfNotNull(term, percent + "%");
        termProgressBar = (ProgressBar) findViewById(R.id.termProgressBar);
        termProgressBar.setProgress(percent);
        address = (TextView) findViewById(R.id.address);
        setIfNotNull(address, legislator.getOffice());
        state = (TextView) findViewById(R.id.state);
        setIfNotNull(state, legislator.getState());
        fax = (TextView) findViewById(R.id.fax);
        setIfNotNull(fax, legislator.getFax());
        birthday = (TextView) findViewById(R.id.birthday);
        setIfNotNull(birthday, formatDate(legislator.getBirthday()));

        favorite = (ImageButton) findViewById(R.id.favoriteLegislator);
        if (checkFavoriteLegislator()) {
            Picasso.with(getApplicationContext()).load(android.R.drawable.btn_star_big_on).resize(0, 35).into(favorite);
        } else {
            Picasso.with(getApplicationContext()).load(android.R.drawable.btn_star_big_off).resize(0, 35).into(favorite);
        }
        /*
        tvName = (TextView) findViewById(R.id.tvItemName);
        tvPrice = (TextView) findViewById(R.id.tvPrice);
        tvDescription = (TextView) findViewById(R.id.tvDescription);

        tvName.setText("Name");
        tvPrice.setText("Price");
        */
//        tvPrice.setText(legislator.getWebsite());
        /*
        tvName.setText(legislator.getTitle() + ". " + legislator.getLastName() + ", " + legislator.getFirstName()
                + "; State: " + legislator.getStateName());
        */
//        itemImage.setImageResource(R.drawable.apple_pie);
        /*
        tvDescription.setText(legislator.toString());
        */
        // tvDescription.setText(legislator.getBioguideId());


        /*
        InputStream inputStream = null;
        try {
            String imageFile = item.getImage();
            inputStream = getAssets().open(imageFile);
            Drawable d = Drawable.createFromStream(inputStream, null);
            itemImage.setImageDrawable(d);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        */

    }

    /*
     * From stack overflow: http://stackoverflow.com/questions/15880861/difference-between-two-dates-and-percentage-of-current-time-java
     */
    public static int getPercentageCompleted(Date start, Date end) {
        if (start == null || end == null)
            return 0;
        long now = System.currentTimeMillis();
        long s = start.getTime();
        long e = end.getTime();
        if (s >= e || now >= e) {
            return 0;
        }
        if (now <= s) {
            return 100;
        }
        return (int) ((now - s) * 100 / (e - s));
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

    private void setIfNotNull(TextView chamber, String value) {
        if (value != null) {
            chamber.setText(value);
        } else {
            chamber.setText("N.A");
        }
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
    public boolean checkFavoriteLegislator() {
        SharedPreference<Legislator> sharedPreference = new SharedPreference(legislator);
        List<Legislator> favorites = sharedPreference.getFavorites(getApplicationContext());
        if (favorites != null) {
            for (Legislator other : favorites)
                if (other.equals(legislator))
                    return true;
        }
        return false;
    }
    public void onToggleStar(View view) {
        SharedPreference<Legislator> sharedPreference = new SharedPreference(legislator);
        if (checkFavoriteLegislator()) {
            sharedPreference.removeFavorite(getApplicationContext(), legislator);
            Picasso.with(getApplicationContext()).load(android.R.drawable.btn_star_big_off).resize(0, 35).into(favorite);
        } else {
            sharedPreference.addFavorite(getApplicationContext(), legislator);
            Picasso.with(getApplicationContext()).load(android.R.drawable.btn_star_big_on).resize(0, 35).into(favorite);
        }
    }
    public void onPressFacebookButton(View view) {
        if (legislator.getFacebook_id() != null) {
            Uri uri = Uri.parse("https://www.facebook.com/" + legislator.getFacebook_id());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Facebook Page Not Available", Toast.LENGTH_SHORT).show();
        }
    }
    public void onPressTwitterButton(View view) {
        if (legislator.getTwitter_id() != null) {
            Uri uri = Uri.parse("https://www.twitter.com/" + legislator.getTwitter_id());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Twitter Page Not Available", Toast.LENGTH_SHORT).show();
        }
    }
    public void onPressHomepageButton(View view) {
        if (legislator.getWebsite() != null) {
            Uri uri = Uri.parse(legislator.getWebsite());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Home Page Not Available", Toast.LENGTH_SHORT).show();
        }
    }
}