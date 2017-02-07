package com.jvokt.csci571.congressapi;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

// Side index based on: http://www.brightec.co.uk/ideas/android-listview-alphabet-scroller
public class LegislatorTabFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;

    private List<Legislator> legislatorList = new ArrayList<>();
    private List<String> alphabet = new ArrayList<>();
    private HashMap<String,Integer> sections = new HashMap<>();

    public static final String ENDPOINT =
            "http://congress-149403.appspot.com";
    public LegislatorTabFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment LegislatorTabFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LegislatorTabFragment newInstance(String param1) {
        LegislatorTabFragment fragment = new LegislatorTabFragment();
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
                SharedPreference<Legislator> sharedPreference = new SharedPreference(new Legislator());
                legislatorList = sharedPreference.getFavorites(getActivity());
                Collections.sort(legislatorList, new Comparator<Legislator>() {
                    @Override
                    public int compare(Legislator a, Legislator b) {
                        return a.getLastName().compareTo(b.getLastName());
                    }
                });
                // Populate alphabet and sections
                alphabet = new ArrayList<>();
                sections = new HashMap<>();
                String previousLetter = null;
                int idx = 0;
                for (Legislator legislator : legislatorList) {
                    String firstLetter = legislator.getLastName().substring(0, 1);
                    if (previousLetter != null && !firstLetter.equals(previousLetter)) {
                        alphabet.add(previousLetter);
                    }
                    if (!firstLetter.equals(previousLetter)) {
                        sections.put(firstLetter, idx);
                    }
                    previousLetter = firstLetter;
                    idx++;
                }
                if (previousLetter != null) {
                    alphabet.add(previousLetter);
                }
            } else {
                RestAdapter adapter = new RestAdapter.Builder()
                        .setEndpoint(ENDPOINT)
                        .build();
                LegislatorAPI api = adapter.create(LegislatorAPI.class);
                api.getLegislators(new Callback<LegislatorResults>() {

                    @Override
                    public void success(LegislatorResults legislatorResults, Response response) {
                        List<Legislator> rawList = legislatorResults.getResults();
//                    for (Legislator legislator : rawList)
//                    Log.i("LegislatorTabFragment","Last name of first legislator: " + rawList.get(0).toString());
//                        Log.i("LegislatorTabFragment", "Tab: " + mParam1 + "; Number of legislators: " + rawList.size());
                        alphabet = new ArrayList<>();
                        sections = new HashMap<>();
                        if (mParam1.equals("state")) {
                            legislatorList = rawList;
                            // Sort by state and then by last name
                            Collections.sort(legislatorList, new Comparator<Legislator>() {
                                @Override
                                public int compare(Legislator a, Legislator b) {
                                    int cmp = a.getStateName().compareTo(b.getStateName());
                                    if (cmp == 0) {
                                        return a.getLastName().compareTo(b.getLastName());
                                    } else
                                        return cmp;
                                }
                            });
                            // Populate alphabet and sections
                            String previousLetter = null;
                            int idx = 0;
                            for (Legislator legislator : legislatorList) {
                                String firstLetter = legislator.getStateName().substring(0, 1);
                                if (previousLetter != null && !firstLetter.equals(previousLetter)) {
                                    alphabet.add(previousLetter);
                                }
                                if (!firstLetter.equals(previousLetter)) {
                                    sections.put(firstLetter, idx);
                                }
                                previousLetter = firstLetter;
                                idx++;
                            }
                            if (previousLetter != null) {
                                alphabet.add(previousLetter);
                            }
                        } else if (mParam1.equals("house")) {
                            // Filter keep house
                            for (Legislator legislator : rawList) {
                                if (legislator.getChamber().equals("house"))
                                    legislatorList.add(legislator);
                            }
                            // Sort by last name
                            Collections.sort(legislatorList, new Comparator<Legislator>() {
                                @Override
                                public int compare(Legislator a, Legislator b) {
                                    return a.getLastName().compareTo(b.getLastName());
                                }
                            });
                            // Populate alphabet and sections
                            String previousLetter = null;
                            int idx = 0;
                            for (Legislator legislator : legislatorList) {
                                String firstLetter = legislator.getLastName().substring(0, 1);
                                if (previousLetter != null && !firstLetter.equals(previousLetter)) {
                                    alphabet.add(previousLetter);
                                }
                                if (!firstLetter.equals(previousLetter)) {
                                    sections.put(firstLetter, idx);
                                }
                                previousLetter = firstLetter;
                                idx++;
                            }
                            if (previousLetter != null) {
                                alphabet.add(previousLetter);
                            }
                        } else {
                            // Filter keep senate
                            for (Legislator legislator : rawList) {
                                if (legislator.getChamber().equals("senate"))
                                    legislatorList.add(legislator);
                            }
                            // Sort by last name
                            Collections.sort(legislatorList, new Comparator<Legislator>() {
                                @Override
                                public int compare(Legislator a, Legislator b) {
                                    return a.getLastName().compareTo(b.getLastName());
                                }
                            });
                            // Populate alphabet and sections
                            String previousLetter = null;
                            int idx = 0;
                            for (Legislator legislator : legislatorList) {
                                String firstLetter = legislator.getLastName().substring(0, 1);
                                if (previousLetter != null && !firstLetter.equals(previousLetter)) {
                                    alphabet.add(previousLetter);
                                }
                                if (!firstLetter.equals(previousLetter)) {
                                    sections.put(firstLetter, idx);
                                }
                                previousLetter = firstLetter;
                                idx++;
                            }
                            if (previousLetter != null) {
                                alphabet.add(previousLetter);
                            }
                        }
                        LegislatorRowAdapter adapter = new LegislatorRowAdapter(getActivity(), legislatorList);
                        updateRecyclerView(adapter);
                        updateList();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.i("LegislatorTabFragment", error.toString());
                    }
                });
            }
        }
    }

    private void updateRecyclerView(LegislatorRowAdapter adapter) {
        RecyclerView recyclerView = (RecyclerView) mView.findViewById(R.id.rvItems);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new SampleScrollListener(getActivity()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_legislator_tab, container, false);
        mGestureDetector = new GestureDetector(getContext(), new SideIndexGestureListener());
        if (mParam1.equals("favorite")) {
            SharedPreference<Legislator> sharedPreference = new SharedPreference(new Legislator());
            legislatorList = sharedPreference.getFavorites(getActivity());
            Collections.sort(legislatorList, new Comparator<Legislator>() {
                @Override
                public int compare(Legislator a, Legislator b) {
                    return a.getLastName().compareTo(b.getLastName());
                }
            });
            // Populate alphabet and sections
            alphabet = new ArrayList<>();
            sections = new HashMap<>();
            String previousLetter = null;
            int idx = 0;
            for (Legislator legislator : legislatorList) {
                String firstLetter = legislator.getLastName().substring(0, 1);
                if (previousLetter != null && !firstLetter.equals(previousLetter)) {
                    alphabet.add(previousLetter);
                }
                if (!firstLetter.equals(previousLetter)) {
                    sections.put(firstLetter, idx);
                }
                previousLetter = firstLetter;
                idx++;
            }
            if (previousLetter != null) {
                alphabet.add(previousLetter);
            }
        }
        LegislatorRowAdapter adapter = new LegislatorRowAdapter(getActivity(), legislatorList);
        updateRecyclerView(adapter);
        updateList();
        return mView;
    }

    @Override
    public void onResume() {
        if (mParam1.equals("favorite")) {
            SharedPreference<Legislator> sharedPreference = new SharedPreference(new Legislator());
            legislatorList = sharedPreference.getFavorites(getActivity());
            Collections.sort(legislatorList, new Comparator<Legislator>() {
                @Override
                public int compare(Legislator a, Legislator b) {
                    return a.getLastName().compareTo(b.getLastName());
                }
            });
            // Populate alphabet and sections
            alphabet = new ArrayList<>();
            sections = new HashMap<>();
            String previousLetter = null;
            int idx = 0;
            for (Legislator legislator : legislatorList) {
                String firstLetter = legislator.getLastName().substring(0, 1);
                if (previousLetter != null && !firstLetter.equals(previousLetter)) {
                    alphabet.add(previousLetter);
                }
                if (!firstLetter.equals(previousLetter)) {
                    sections.put(firstLetter, idx);
                }
                previousLetter = firstLetter;
                idx++;
            }
            if (previousLetter != null) {
                alphabet.add(previousLetter);
            }
            LegislatorRowAdapter adapter = new LegislatorRowAdapter(getActivity(), legislatorList);
            updateRecyclerView(adapter);
            updateList();
        }
        super.onResume();
    }

    private GestureDetector mGestureDetector;
    private int sideIndexHeight;
    private static float sideIndexX;
    private static float sideIndexY;
    private int indexListSize;
    private View mView;
    class SideIndexGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            // we know already coordinates of first touch
            // we know as well a scroll distance
            sideIndexX = sideIndexX - distanceX;
            sideIndexY = sideIndexY - distanceY;

            // when the user scrolls within our side index
            // we can show for every position in it a proper
            // item in the country list
            if (sideIndexX >= 0 && sideIndexY >= 0) {
                displayListItem();
            }

            return super.onScroll(e1, e2, distanceX, distanceY);
        }
    }
    public void displayListItem() {
        LinearLayout sideIndex = (LinearLayout) mView.findViewById(R.id.sideIndex);
        sideIndexHeight = sideIndex.getHeight();
        // compute number of pixels for every side index item
        double pixelPerIndexItem = (double) sideIndexHeight / indexListSize;

        // compute the item index for given event position belongs to
        int itemPosition = (int) (sideIndexY / pixelPerIndexItem);

        // get the item (we can do it since we know item index)
        if (itemPosition < alphabet.size()) {
            String letter = alphabet.get(itemPosition);
            int subitemPosition = sections.get(letter);

            //ListView listView = (ListView) findViewById(android.R.id.list);
            RecyclerView recyclerView = (RecyclerView) mView.findViewById(R.id.rvItems);
//            recyclerView.scrollToPosition(subitemPosition);
//            recyclerView.getLayoutManager().scrollToPositionWithOffset(subitemPosition,0);
            ((LinearLayoutManager)recyclerView.getLayoutManager()).scrollToPositionWithOffset(subitemPosition,0);
            /*
            final Picasso picasso = Picasso.with(getActivity());
//            LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
            int start = subitemPosition - 2;
            int end = subitemPosition + 7 + 2;
            for (int i = 0; i < recyclerView.getAdapter().getItemCount(); i++) {
                picasso.pauseTag(i);
            }
            for (int i = start; i <= end; i++) {
                picasso.resumeTag(i);
            }
            */
//            loadImages(recyclerView);
        }
    }

    private void loadImages(RecyclerView recyclerView) {
        final Picasso picasso = Picasso.with(getActivity());
        LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
        int start = manager.findFirstVisibleItemPosition() - 2;
        int end = manager.findLastVisibleItemPosition() + 2;
        for (int i = 0; i < recyclerView.getAdapter().getItemCount(); i++) {
            picasso.pauseTag(i);
        }
        for (int i = start; i <= end; i++) {
            picasso.resumeTag(i);
        }
    }

    public void updateList() {
        LinearLayout sideIndex = (LinearLayout) mView.findViewById(R.id.sideIndex);
        sideIndex.removeAllViews();
        indexListSize = alphabet.size();
        if (indexListSize < 1) {
            return;
        }

        int indexMaxSize = (int) Math.floor(sideIndex.getHeight() / 20);
        int tmpIndexListSize = indexListSize;
        while (tmpIndexListSize > indexMaxSize) {
            tmpIndexListSize = tmpIndexListSize / 2;
        }
        double delta;
        if (tmpIndexListSize > 0) {
            delta = indexListSize / tmpIndexListSize;
        } else {
            delta = 1;
        }

        TextView tmpTV;
        for (double i = 1; i <= indexListSize; i = i + delta) {
            String tmpLetter = alphabet.get((int) i - 1);

            tmpTV = new TextView(getContext());
            tmpTV.setText(tmpLetter);
            tmpTV.setGravity(Gravity.CENTER);
            tmpTV.setTextSize(15);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
            tmpTV.setLayoutParams(params);
            sideIndex.addView(tmpTV);
        }

        sideIndexHeight = sideIndex.getHeight();

        sideIndex.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // now you know coordinates of touch
                sideIndexX = event.getX();
                sideIndexY = event.getY();

                // and can display a proper item it country list
                displayListItem();
                /*
                RecyclerView recyclerView = (RecyclerView) mView.findViewById(R.id.rvItems);
                final Picasso picasso = Picasso.with(getActivity());
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int start = manager.findFirstVisibleItemPosition() - 2;
                int end = manager.findLastVisibleItemPosition() + 2;
                for (int i = 0; i < recyclerView.getAdapter().getItemCount(); i++) {
                    picasso.pauseTag(i);
                }
                for (int i = start; i <= end; i++) {
                    picasso.resumeTag(i);
                }
                */
                /*
                Context context = getActivity();
                RecyclerView recyclerView = (RecyclerView) mView.findViewById(R.id.rvItems);
                final Picasso picasso = Picasso.with(context);
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int start = manager.findFirstVisibleItemPosition() - 2;
                int end = manager.findLastVisibleItemPosition() + 2;
                for (int i = 0; i < recyclerView.getAdapter().getItemCount(); i++) {
                    picasso.pauseTag(i);
                }
                for (int i = start; i <= end; i++) {
                    picasso.resumeTag(i);
                }
                */
                return false;
            }
        });
    }
    public class SampleScrollListener extends RecyclerView.OnScrollListener {
        private final Context context;
        private ArrayList<Integer> resumedIds = new ArrayList<>();
        public SampleScrollListener(Context context) {
            this.context = context;
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int scrollState) {
            /*
            final Picasso picasso = Picasso.with(context);
            LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
            int start = manager.findFirstVisibleItemPosition() - 2;
            int end = manager.findLastVisibleItemPosition() + 2;
            for (int i = 0; i < recyclerView.getAdapter().getItemCount(); i++) {
                picasso.pauseTag(i);
            }
            for (int i = start; i <= end; i++) {
                picasso.resumeTag(i);
            }
            */
            // loadImages(recyclerView);
            /*
            final Picasso picasso = Picasso.with(context);
            LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
            int start = manager.findFirstVisibleItemPosition() - 2;
            int end = manager.findLastVisibleItemPosition() + 2;
            if (scrollState == RecyclerView.SCROLL_STATE_IDLE || scrollState == RecyclerView.SCROLL_STATE_DRAGGING) {
                picasso.resumeTag(context);
            } else {
                picasso.pauseTag(context);
            }
            */
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            // Do nothing.
            final Picasso picasso = Picasso.with(context);
            LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
            int start = manager.findFirstVisibleItemPosition() - 2;
            int end = manager.findLastVisibleItemPosition() + 2;
//            for (int i = 0; i < recyclerView.getAdapter().getItemCount(); i++) {
            for (int i : resumedIds) {
                picasso.pauseTag(i);
            }
            resumedIds.clear();
            for (int i = start; i <= end; i++) {
                picasso.resumeTag(i);
                resumedIds.add(i);
            }
//            super.onScrolled(recyclerView, dx, dy);
            /*
            final Picasso picasso = Picasso.with(context);
            LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
            int start = manager.findFirstVisibleItemPosition() - 2;
            int end = manager.findLastVisibleItemPosition() + 2;
            for (int i = start; i <= end; i++) {
                picasso.resumeTag("" + i);
            }
            */
        }

        // onLoadMore
    }
}
