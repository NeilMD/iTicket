package com.example.julia.wirtec_iticket;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.SecureRandom;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewTickets extends Fragment {


    public ViewTickets() {
        // Required empty public constructor
    }

    ViewTicketsAdapter viewTicketsAdapter;
    ArrayList<String> data;
    RecyclerView rvTickets;
    SwipeRefreshLayout swipeContainer;
    NavDrawer navDrawer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_tickets, container, false);
        rvTickets = (RecyclerView) view.findViewById(R.id.rv_tickets);

        navDrawer = (NavDrawer) getActivity();

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

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("user-tickets").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        if(rvTickets != null) {
            viewTicketsAdapter = new ViewTicketsAdapter(data,ref);

            viewTicketsAdapter.setmOnItemClickListener(new ViewTicketsAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(Ticket ticket, String ticketId) {
                    Intent i = new Intent(navDrawer, ViewTicketDetails.class);
                    i.putExtra("ticket", new TicketParcelable(ticket));
                    startActivity(i);
                }
            });
            viewTicketsAdapter.setmOnLongItemClickListener(new ViewTicketsAdapter.OnLongItemClickListener() {
                @Override
                public void onLongItemClick(Ticket ticket, String ticketId) {
                    if (ticket.getStatus().equals("pending")){
                        Toast.makeText(getContext(),"Ticket is not yet accepted!", Toast.LENGTH_LONG).show();
                    }else {
                        Intent i = new Intent(getContext(), FullscreenTicketCheck.class);
                        i.putExtra("ticket", new TicketParcelable(ticket));
                        startActivity(i);
                    }
                }
            });
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
            rvTickets.setLayoutManager(layoutManager);
            /*DividerItemDecorationCustom dividerItemDecoration = new DividerItemDecorationCustom(rvTickets.getContext());
            rvTickets.addItemDecoration(dividerItemDecoration);*/
            rvTickets.setAdapter(viewTicketsAdapter);
        }

        final LinearLayoutManager mLinearLayoutManager = (LinearLayoutManager) rvTickets.getLayoutManager();
        rvTickets.addOnScrollListener(new RecyclerView.OnScrollListener()
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


//        rvTickets.addOnItemTouchListener(
//                new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
//                    @Override public void onItemClick(View view, int position) {
//                        //depending on position of Child, populate ViewEventDetails, refer to RecyclerItemClickListener class for methods
//                        Intent i = new Intent(navDrawer, ViewTicketDetails.class);
//                        startActivity(i);
//                    }
//                })
//        );



        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.tickets_swipe_refresh_layout);
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


            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("user-tickets").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
            viewTicketsAdapter = new ViewTicketsAdapter(data,ref);

            viewTicketsAdapter.setmOnItemClickListener(new ViewTicketsAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(Ticket ticket, String ticketId) {
                    Intent i = new Intent(navDrawer, ViewTicketDetails.class);
                    i.putExtra("ticket", new TicketParcelable(ticket));
                    startActivity(i);
                }
            });
            viewTicketsAdapter.setmOnLongItemClickListener(new ViewTicketsAdapter.OnLongItemClickListener() {
                @Override
                public void onLongItemClick(Ticket ticket, String ticketId) {
                    if (ticket.getStatus().equals("pending")){
                        Toast.makeText(getContext(),"Ticket is not yet accepted!", Toast.LENGTH_LONG).show();
                    }else {
                        Intent i = new Intent(getContext(), FullscreenTicketCheck.class);
                        i.putExtra("ticket", new TicketParcelable(ticket));
                        startActivity(i);
                    }
                }
            });



            rvTickets.setAdapter(viewTicketsAdapter);
            swipeContainer.setRefreshing(false);


        }
    }, 1000);
    }




}
