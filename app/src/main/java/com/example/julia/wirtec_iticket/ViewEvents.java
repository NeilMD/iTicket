package com.example.julia.wirtec_iticket;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewEvents extends Fragment {


    public ViewEvents() {
        // Required empty public constructor
    }

    ViewEventAdapter viewEventAdapter;
    ArrayList<String> data;
    RecyclerView rvEvents;
    SwipeRefreshLayout swipeContainer;
    RecyclerView.LayoutManager layoutManager;
    NavDrawer navDrawer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_events, container, false);

        rvEvents = (RecyclerView) view.findViewById(R.id.rv_events);
        navDrawer  = (NavDrawer)getActivity();

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


        if(rvEvents != null) {
            viewEventAdapter = new ViewEventAdapter(data);
           layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
            rvEvents.setLayoutManager(layoutManager);
            DividerItemDecorationCustom dividerItemDecoration = new DividerItemDecorationCustom(rvEvents.getContext());
            rvEvents.addItemDecoration(dividerItemDecoration);
            rvEvents.setAdapter(viewEventAdapter);
        }

        final LinearLayoutManager mLinearLayoutManager = (LinearLayoutManager) rvEvents.getLayoutManager();
        rvEvents.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                if(mLinearLayoutManager.findLastCompletelyVisibleItemPosition() == data.size()-1){
                    navDrawer.materialDesignFAM.setVisibility(View.INVISIBLE);
                }
                else{
                    navDrawer.materialDesignFAM.setVisibility(View.VISIBLE);
                }
            }

        });

        rvEvents.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        //depending on position of Child, populate ViewEventDetails, refer to RecyclerItemClickListener class for methods
                        Intent i = new Intent(navDrawer, ViewEventDetails.class);
                        startActivity(i);
                    }
                })
        );


        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.events_swipe_refresh_layout);
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
            viewEventAdapter = new ViewEventAdapter(data);
            rvEvents.setAdapter(viewEventAdapter);
            swipeContainer.setRefreshing(false);
        }
    }, 1000);
    }

}
