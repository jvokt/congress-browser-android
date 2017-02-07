package com.jvokt.csci571.congressapi;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by jvokt on 11/27/16.
 */
public class BillTabFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private String mParam1;
    private List<Bill> billList = new ArrayList<>();
    public static final String ENDPOINT =
            "http://congress-149403.appspot.com";
    private View mView;
    public BillTabFragment() {
        // Required empty public constructor
    }
    public static BillTabFragment newInstance(String param1) {
        BillTabFragment fragment = new BillTabFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            if (mParam1.equals("favorite")) {
                SharedPreference<Bill> sharedPreference = new SharedPreference(new Bill());
                billList = sharedPreference.getFavorites(getActivity());
                Collections.sort(billList, new Comparator<Bill>() {
                    @Override
                    public int compare(Bill a, Bill b) {
                        return -1*a.introduced_on.compareTo(b.introduced_on);
                    }
                });
            } else {
                RestAdapter adapter = new RestAdapter.Builder()
                        .setEndpoint(ENDPOINT)
                        .build();
                BillAPI api = adapter.create(BillAPI.class);
                api.getBills(new Callback<BillResults>() {

                    @Override
                    public void success(BillResults billResults, Response response) {
                        List<Bill> rawList = billResults.getResults();
                        Log.i("BillTabFragment","Number of bills: " + rawList.size());
                        if (mParam1.equals("active")) {
                            for (Bill bill : rawList)
                                if (bill.history.active)
                                    billList.add(bill);
                        } else {
                            for (Bill bill : rawList)
                                if (!bill.history.active)
                                    billList.add(bill);
                        }
                        Collections.sort(billList, new Comparator<Bill>() {
                            @Override
                            public int compare(Bill a, Bill b) {
                                return -1*a.introduced_on.compareTo(b.introduced_on);
                            }
                        });
                        BillRowAdapter adapter = new BillRowAdapter(getActivity(), billList);
                        updateRecyclerView(adapter);

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.i("BillTabFragment", error.toString());
                    }
                });
            }
        }
    }
    private void updateRecyclerView(BillRowAdapter adapter) {
        RecyclerView recyclerView = (RecyclerView) mView.findViewById(R.id.rvItems);
        recyclerView.setAdapter(adapter);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_bill_tab, container, false);
        if (mParam1.equals("favorite")) {
            SharedPreference<Bill> sharedPreference = new SharedPreference(new Bill());
            billList = sharedPreference.getFavorites(getActivity());
            Collections.sort(billList, new Comparator<Bill>() {
                @Override
                public int compare(Bill a, Bill b) {
                    return -1*a.introduced_on.compareTo(b.introduced_on);
                }
            });
        }
        BillRowAdapter adapter = new BillRowAdapter(getActivity(), billList);
        updateRecyclerView(adapter);
        return mView;
    }
    @Override
    public void onResume() {
        if (mParam1.equals("favorite")) {
            SharedPreference<Bill> sharedPreference = new SharedPreference(new Bill());
            billList = sharedPreference.getFavorites(getActivity());
            Collections.sort(billList, new Comparator<Bill>() {
                @Override
                public int compare(Bill a, Bill b) {
                    return -1*a.introduced_on.compareTo(b.introduced_on);
                }
            });
            BillRowAdapter adapter = new BillRowAdapter(getActivity(), billList);
            updateRecyclerView(adapter);
        }
        super.onResume();
    }
}
