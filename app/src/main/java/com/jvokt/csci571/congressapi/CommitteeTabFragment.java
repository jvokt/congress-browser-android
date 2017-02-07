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
public class CommitteeTabFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private String mParam1;
    private List<Committee> committeeList = new ArrayList<>();
    public static final String ENDPOINT =
            "http://congress-149403.appspot.com";
    private View mView;
    public CommitteeTabFragment() {
        // Required empty public constructor
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment CommitteeTabFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CommitteeTabFragment newInstance(String param1) {
        CommitteeTabFragment fragment = new CommitteeTabFragment();
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
                SharedPreference<Committee> sharedPreference = new SharedPreference(new Committee());
                committeeList = sharedPreference.getFavorites(getActivity());
                Collections.sort(committeeList, new Comparator<Committee>() {
                    @Override
                    public int compare(Committee a, Committee b) {
                        return a.committee_id.compareTo(b.committee_id);
                    }
                });
            } else {
                RestAdapter adapter = new RestAdapter.Builder()
                        .setEndpoint(ENDPOINT)
                        .build();
                CommitteeAPI api = adapter.create(CommitteeAPI.class);
                api.getCommittees(new Callback<CommitteeResults>() {

                    @Override
                    public void success(CommitteeResults committeeResults, Response response) {
                        List<Committee> rawList = committeeResults.getResults();
                        Log.i("BillTabFragment","Number of bills: " + rawList.size());
                        if (mParam1.equals("house")) {
                            for (Committee committee : rawList)
                                if (committee.chamber.equals("house"))
                                    committeeList.add(committee);
                        } else if (mParam1.equals("senate")) {
                            for (Committee committee : rawList)
                                if (committee.chamber.equals("senate"))
                                    committeeList.add(committee);
                        } else {
                            for (Committee committee : rawList)
                                if (committee.chamber.equals("joint"))
                                    committeeList.add(committee);
                        }
                        Collections.sort(committeeList, new Comparator<Committee>() {
                            @Override
                            public int compare(Committee a, Committee b) {
                                return a.committee_id.compareTo(b.committee_id);
                            }
                        });
                        CommitteeRowAdapter adapter = new CommitteeRowAdapter(getActivity(), committeeList);
                        RecyclerView recyclerView = (RecyclerView) mView.findViewById(R.id.rvItems);
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.i("CommitteeTabFragment", error.toString());
                    }
                });
            }
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_bill_tab, container, false);
        if (mParam1.equals("favorite")) {
            SharedPreference<Committee> sharedPreference = new SharedPreference(new Committee());
            committeeList = sharedPreference.getFavorites(getActivity());
            Collections.sort(committeeList, new Comparator<Committee>() {
                @Override
                public int compare(Committee a, Committee b) {
                    return a.committee_id.compareTo(b.committee_id);
                }
            });
        }
        CommitteeRowAdapter adapter = new CommitteeRowAdapter(getActivity(), committeeList);
        RecyclerView recyclerView = (RecyclerView) mView.findViewById(R.id.rvItems);
        recyclerView.setAdapter(adapter);
        return mView;
    }
    @Override
    public void onResume() {
        if (mParam1.equals("favorite")) {
            SharedPreference<Committee> sharedPreference = new SharedPreference(new Committee());
            committeeList = sharedPreference.getFavorites(getActivity());
            Collections.sort(committeeList, new Comparator<Committee>() {
                @Override
                public int compare(Committee a, Committee b) {
                    return a.committee_id.compareTo(b.committee_id);
                }
            });
            CommitteeRowAdapter adapter = new CommitteeRowAdapter(getActivity(), committeeList);
            RecyclerView recyclerView = (RecyclerView) mView.findViewById(R.id.rvItems);
            recyclerView.setAdapter(adapter);
        }
        super.onResume();
    }
}
