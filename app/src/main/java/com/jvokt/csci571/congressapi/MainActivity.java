package com.jvokt.csci571.congressapi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Fragment currentFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        currentFragment = BlankFragment.newInstance("","");
        currentFragment = LegislatorFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.content_frame, currentFragment, "l").commit();
        Fragment fragment = BillFragment.newInstance();
//        Fragment fragment = BlankFragment.newInstance("","");
        fragmentManager.beginTransaction().add(R.id.content_frame, fragment, "b").hide(fragment).commit();
        fragment = CommitteeFragment.newInstance();
        fragmentManager.beginTransaction().add(R.id.content_frame, fragment, "c").hide(fragment).commit();
        fragment = FavoriteFragment.newInstance();
        fragmentManager.beginTransaction().add(R.id.content_frame, fragment, "f").hide(fragment).commit();
        /*
        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment fragment = BlankFragment.newInstance("","");
        fragments[0] = fragment;
        fragmentManager.beginTransaction().add(R.id.content_frame, fragment, "legislators").commit();

        Fragment fragment2 = ItemFragment.newInstance(1);
        fragments[1] = fragment2;
        */
        /*
        fragmentManager.beginTransaction().add(R.id.content_frame, fragment2, "bills").hide(fragment2).commit();
        */


//        Fragment fragment2 = ItemFragment.newInstance(1);
        /*
        Fragment fragment2 = BlankFragment.newInstance("","");
        fragments[1] = fragment2;
        fragmentManager.beginTransaction().add(R.id.content_frame, fragment2, "bills").commit();
        Fragment billFrag = getSupportFragmentManager().findFragmentByTag("bills");
        getSupportFragmentManager().beginTransaction().hide(billFrag).commit();
        */
        getSupportActionBar().setTitle("Legislators");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment legFrag = getSupportFragmentManager().findFragmentByTag("l");
        Fragment billFrag = getSupportFragmentManager().findFragmentByTag("b");
        Fragment comFrag = getSupportFragmentManager().findFragmentByTag("c");
        Fragment favFrag = getSupportFragmentManager().findFragmentByTag("f");
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        ft.hide(legFrag).hide(billFrag).hide(comFrag).hide(favFrag);
        // BlankFragment.newInstance("","")
        /*
        Fragment billFrag = getSupportFragmentManager().findFragmentByTag("bills");
        getSupportFragmentManager().beginTransaction().hide(billFrag).commit();
        Fragment legFrag = getSupportFragmentManager().findFragmentByTag("legislators");
        getSupportFragmentManager().beginTransaction().hide(legFrag).commit();
        */
//        Fragment comFrag = getSupportFragmentManager().findFragmentByTag("committees");
//        getSupportFragmentManager().beginTransaction().hide(comFrag).commit();
//        Fragment favFrag = getSupportFragmentManager().findFragmentByTag("favorites");
//        getSupportFragmentManager().beginTransaction().hide(favFrag).commit();


        if (id == R.id.nav_legislators) {
//            getSupportFragmentManager().beginTransaction().show(legFrag).commit();
            ft.hide(currentFragment);
            ft.show(legFrag);
            currentFragment = legFrag;
//            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, BlankFragment.newInstance("","")).commit();
//            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragments[0]).commit();
            getSupportActionBar().setTitle("Legislators");
        } else if (id == R.id.nav_bills) {
            ft.hide(currentFragment);
            ft.show(billFrag);
            currentFragment = billFrag;
//            getSupportFragmentManager().beginTransaction().show(billFrag).commit();
//            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, BlankFragment.newInstance("","")).commit();
//            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragments[1]).commit();
            getSupportActionBar().setTitle("Bills");
        } else if (id == R.id.nav_committees) {
            ft.hide(currentFragment);
            ft.show(comFrag);
            currentFragment = comFrag;
//            getSupportFragmentManager().beginTransaction().show(comFrag).commit();
            getSupportActionBar().setTitle("Committees");
        } else if (id == R.id.nav_favorites) {
            ft.hide(currentFragment);
            ft.show(favFrag);
            currentFragment = favFrag;
//            getSupportFragmentManager().beginTransaction().show(favFrag).commit();
            getSupportActionBar().setTitle("Favorites");
        } else if (id == R.id.nav_aboutme) {
            Intent intent = new Intent(this, AboutMe.class);
            startActivity(intent);
        }
        ft.commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
