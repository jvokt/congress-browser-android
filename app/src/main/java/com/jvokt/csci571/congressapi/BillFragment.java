package com.jvokt.csci571.congressapi;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jvokt on 11/27/16.
 */
public class BillFragment extends Fragment {
    ViewPager viewPager;

    public BillFragment() {
        // Required empty public constructor
    }

    public static BillFragment newInstance() {
        BillFragment fragment = new BillFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_layout_view_pager, container, false);
        viewPager = (ViewPager) v.findViewById(R.id.viewPager);
        viewPager.setAdapter(new CustomAdapter(getChildFragmentManager()));

        TabLayout tabLayout = (TabLayout) v.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
        });
        return v;

    }
    private class CustomAdapter extends FragmentPagerAdapter {
        private String fragmentNames [] = {"Active Bills", "New Bills"};
        private Fragment mFragments [] = {
                BillTabFragment.newInstance("active"),
                BillTabFragment.newInstance("new")
        };
        public CustomAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return mFragments[0];
                case 1:
                    return mFragments[1];
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return fragmentNames.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentNames[position];
        }
    }
}
