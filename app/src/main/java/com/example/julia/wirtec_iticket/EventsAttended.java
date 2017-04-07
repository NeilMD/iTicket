package com.example.julia.wirtec_iticket;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventsAttended extends Fragment {

    EventParcelable  ep;

    public EventsAttended() {
        // Required empty public constructor
    }

    public void setEvent(EventParcelable ep){
        this.ep = ep;
    }

    ViewProfileAdapter viewProfileAdapter;
    ArrayList<String> data;
    RecyclerView rvAttended;
    SwipeRefreshLayout swipeContainer;
    Profile profile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_attendees, container, false);
        rvAttended = (RecyclerView) view.findViewById(R.id.rv_attendees);

        profile = (Profile) getActivity();
//        viewEventDetails.setTitle();
        data = new ArrayList<String>();
        data.add("Alpha");
        data.add("Beta");
        data.add("Charlie");
        data.add("Delta");
        data.add("Echo");
        data.add("Foxtrot");
        data.add("Gamma");
        data.add("Hotel");
        data.add("India");
        data.add("Juliet");
        data.add("Kilo");
        data.add("Lima");
        data.add("Mama");

        if(rvAttended != null) {
            //DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("events-attendees").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(ep.getCode());
            viewProfileAdapter = new ViewProfileAdapter(data);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
            rvAttended.setLayoutManager(layoutManager);
            DividerItemDecorationCustom dividerItemDecoration = new DividerItemDecorationCustom(rvAttended.getContext());
            rvAttended.addItemDecoration(dividerItemDecoration);
            rvAttended.setAdapter(viewProfileAdapter);
        }

        final LinearLayoutManager mLinearLayoutManager = (LinearLayoutManager) rvAttended.getLayoutManager();
        rvAttended.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {

                if(mLinearLayoutManager.findLastCompletelyVisibleItemPosition() == data.size()-1){
                    profile.materialDesignFAM.setVisibility(View.INVISIBLE);
                }
                else{
                    profile.materialDesignFAM.setVisibility(View.VISIBLE);
                }
            }

        });
        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.attendees_swipe_refresh_layout);
        if (swipeContainer != null){
            swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    refreshContent();

                }});
            swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light);
        }

        return view;
    }

    private void refreshContent(){ new Handler().postDelayed(new Runnable(){
        @Override
        public void run() {

            //DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("events-attendees").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(ep.getCode());
            viewProfileAdapter = new ViewProfileAdapter(data);
            rvAttended.setAdapter(viewProfileAdapter);
            swipeContainer.setRefreshing(false);
        }
    }, 1000);
    }
}
