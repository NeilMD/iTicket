package layout;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.julia.wirtec_iticket.DividerItemDecorationCustom;
import com.example.julia.wirtec_iticket.NavDrawer;
import com.example.julia.wirtec_iticket.R;
import com.example.julia.wirtec_iticket.ViewAttendeesAdapter;
import com.example.julia.wirtec_iticket.ViewEventDetails;
import com.example.julia.wirtec_iticket.ViewTicketsAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewAttendees extends Fragment {


    public ViewAttendees() {
        // Required empty public constructor
    }

    ViewAttendeesAdapter viewAttendeesAdapter;
    ArrayList<String> data;
    RecyclerView rvAttendees;
    SwipeRefreshLayout swipeContainer;
    ViewEventDetails viewEventDetails;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_attendees, container, false);

        rvAttendees = (RecyclerView) view.findViewById(R.id.rv_attendees);

        viewEventDetails = (ViewEventDetails) getActivity();

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

        if(rvAttendees != null) {
            viewAttendeesAdapter = new ViewAttendeesAdapter(data);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
            rvAttendees.setLayoutManager(layoutManager);
            DividerItemDecorationCustom dividerItemDecoration = new DividerItemDecorationCustom(rvAttendees.getContext());
            rvAttendees.addItemDecoration(dividerItemDecoration);
            rvAttendees.setAdapter(viewAttendeesAdapter);
        }

        final LinearLayoutManager mLinearLayoutManager = (LinearLayoutManager) rvAttendees.getLayoutManager();
        rvAttendees.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {

                if(mLinearLayoutManager.findLastCompletelyVisibleItemPosition() == data.size()-1){
                    viewEventDetails.materialDesignFAM.setVisibility(View.INVISIBLE);
                }
                else{
                    viewEventDetails.materialDesignFAM.setVisibility(View.VISIBLE);
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
            viewAttendeesAdapter = new ViewAttendeesAdapter(data);
            rvAttendees.setAdapter(viewAttendeesAdapter);
            swipeContainer.setRefreshing(false);
        }
    }, 1000);
    }

}
