package com.jvokt.csci571.congressapi;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by jvokt on 11/27/16.
 */
public class CommitteeFragment extends Fragment {
    ViewPager viewPager;
    public CommitteeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CommitteeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CommitteeFragment newInstance() {
        CommitteeFragment fragment = new CommitteeFragment();
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
        private String fragmentNames [] = {"House", "Senate", "Joint"};
        private Fragment mFragments [] = {
                CommitteeTabFragment.newInstance("house"),
                CommitteeTabFragment.newInstance("senate"),
                CommitteeTabFragment.newInstance("joint")
        };
        public CustomAdapter(FragmentManager childFragmentManager) {
            super(childFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            if (position < getCount())
                return mFragments[position];
            else
                return null;
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
