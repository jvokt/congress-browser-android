package com.jvokt.csci571.congressapi;

import android.content.Context;
import android.net.Uri;
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
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LegislatorFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LegislatorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LegislatorFragment extends Fragment {

    ViewPager viewPager;

    public LegislatorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LegislatorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LegislatorFragment newInstance() {
        LegislatorFragment fragment = new LegislatorFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.tab_layout_view_pager, container, false);
//        View v = inflater.inflate(R.layout.fragment_blank, container, false);
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
        private String fragmentNames [] = {"By States", "House", "Senate"};
        private Fragment mFragments [] = {
                LegislatorTabFragment.newInstance("state"),
                LegislatorTabFragment.newInstance("house"),
                LegislatorTabFragment.newInstance("senate")
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
                case 2:
                    return mFragments[2];
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
