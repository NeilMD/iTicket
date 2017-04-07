package com.example.julia.wirtec_iticket;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewRequests extends Fragment {


    public ViewRequests() {
        // Required empty public constructor
    }

    ViewRequestsAdapter viewRequestsAdapter;
    ArrayList<String> data = new ArrayList<>();
    RecyclerView rvRequests;
    SwipeRefreshLayout swipeContainer;
    NavDrawer navDrawer;
    StickyRecyclerHeadersDecoration headersDecor;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("event-request").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        viewRequestsAdapter = new ViewRequestsAdapter(data,ref);
        viewRequestsAdapter.setmOnItemClickListener(new ViewRequestsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final Request request, String code) {
//                final View v2 = v;
                Log.i("REJECT",code);
                  final String code2 = code;
//                final Request req = (Request) v.getTag();
//                Log.i("On Click Ng Reject",position+"asd");
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("event-request").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(code).child(request.getUid());
                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

//                                for(int x = first; x < last + 1; x++){
//                                    Log.i("adapter", "x is " + x + " eve uid is " + eve.getUid() + " uid x is " + uid.get(x) );
//                                    if(uid.get(x).equals(eve.getUid())){
//                                        key = x;
//                                    }
//                                }
//                                notifyItemRemoved();
                                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("user-tickets").child(request.getUid()).child(code2);
                                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        dataSnapshot.getRef().removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(getContext(),"Success!",Toast.LENGTH_LONG).show();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(getContext(),"Failed!",Toast.LENGTH_LONG).show();
                                            }
                                        });
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {
                                        Toast.makeText(getContext(),"Failed!",Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getContext(),"Failed!",Toast.LENGTH_LONG).show();
                            }
                        });
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getContext(),"Failed!",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        viewRequestsAdapter.setmOnLongItemClickListener(new ViewRequestsAdapter.OnLongItemClickListener() {
            @Override
            public void onLongItemClick(final Request request, String code) {
                final String code2 = code;
//                final Request req = (Request) v.getTag();
//                Log.i("On Click Ng Reject",position+"asd");
                final Request request1 =request;
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("event-request").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(code).child(request.getUid());
                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

//                                for(int x = first; x < last + 1; x++){
//                                    Log.i("adapter", "x is " + x + " eve uid is " + eve.getUid() + " uid x is " + uid.get(x) );
//                                    if(uid.get(x).equals(eve.getUid())){
//                                        key = x;
//                                    }
//                                }
//                                notifyItemRemoved();
                                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("user-tickets").child(request1.getUid()).child(code2);
                                ref.child("status").setValue("unused").addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(getContext(),"Success!",Toast.LENGTH_LONG).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(getContext(),"Failed!",Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getContext(),"Failed!",Toast.LENGTH_LONG).show();
                            }
                        });
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getContext(),"Failed!",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_requests, container, false);

        rvRequests = (RecyclerView) view.findViewById(R.id.rv_requests);

        navDrawer = (NavDrawer) getActivity();
        data = new ArrayList<String>();
//        data.add("Kimmy");
//        data.add("Kranku");
//        data.add("Keil");
//        data.add("Charlie");
//        data.add("Delta");
//        data.add("Echo");
//        data.add("Foxtrot");
//        data.add("Gamma");
//        data.add("Hotel");
//        data.add("India");
//        data.add("Juliet");
//        data.add("Kilo");
//        data.add("Lima");
//        data.add("Mama");
        if(rvRequests != null) {
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("event-request").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
//            viewRequestsAdapter = new ViewRequestsAdapter(data,ref);
//            Toast.makeText(getContext(),viewRequestsAdapter.geteid().size()+"",Toast.LENGTH_LONG).show();
            viewRequestsAdapter.addAll(viewRequestsAdapter.geteid());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
            rvRequests.setLayoutManager(layoutManager);
            /*DividerItemDecorationCustom dividerItemDecoration = new DividerItemDecorationCustom(rvRequests.getContext());
            rvRequests.addItemDecoration(dividerItemDecoration);*/
            headersDecor = new StickyRecyclerHeadersDecoration(viewRequestsAdapter);
            rvRequests.addItemDecoration(headersDecor);
            rvRequests.setAdapter(viewRequestsAdapter);

            // Add decoration for dividers between list items
            rvRequests.addItemDecoration(new DividerDecoration(getContext()));

            // Add touch listeners
            StickyRecyclerHeadersTouchListener touchListener =
                    new StickyRecyclerHeadersTouchListener(rvRequests, headersDecor);

            touchListener.setOnHeaderClickListener(
                    new StickyRecyclerHeadersTouchListener.OnHeaderClickListener() {
                        @Override
                        public void onHeaderClick(View header, int position, long headerId) {
                            Toast.makeText(getActivity(), "Header position: " + position + ", id: " + headerId,
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
            rvRequests.addOnItemTouchListener(touchListener);
            rvRequests.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    viewRequestsAdapter.notifyDataSetChanged();
                }
            }));
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    viewRequestsAdapter.notifyDataSetChanged();
                    headersDecor.invalidateHeaders();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            viewRequestsAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                @Override
                public void onChanged() {
                    headersDecor.invalidateHeaders();
                }
            });
        }

        final LinearLayoutManager mLinearLayoutManager = (LinearLayoutManager) rvRequests.getLayoutManager();
        rvRequests.addOnScrollListener(new RecyclerView.OnScrollListener()
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



        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.requests_swipe_refresh_layout);
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

    @Override
    public void onResume() {

        super.onResume();
    }

    private void refreshContent(){ new Handler().postDelayed(new Runnable(){
        @Override
        public void run() {
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("event-request").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
            viewRequestsAdapter = new ViewRequestsAdapter(data,ref);
            viewRequestsAdapter.setmOnItemClickListener(new ViewRequestsAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(final Request request, String code) {
//                final View v2 = v;
                    final String code2 = code;
                final Request req = request;
                Log.i("REJECT",code);
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("event-request").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(code).child(request.getUid());
                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            dataSnapshot.getRef().removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {

//                                for(int x = first; x < last + 1; x++){
//                                    Log.i("adapter", "x is " + x + " eve uid is " + eve.getUid() + " uid x is " + uid.get(x) );
//                                    if(uid.get(x).equals(eve.getUid())){
//                                        key = x;
//                                    }
//                                }
//                                notifyItemRemoved();
                                    Log.i("REJECT1",code2);
                                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("user-tickets").child(req.getUid()).child(code2);
                                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            dataSnapshot.getRef().removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Toast.makeText(getContext(),"Success!",Toast.LENGTH_LONG).show();
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(getContext(),"Failed!",Toast.LENGTH_LONG).show();
                                                }
                                            });
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {
                                            Toast.makeText(getContext(),"Failed!",Toast.LENGTH_LONG).show();
                                        }
                                    });
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getContext(),"Failed!",Toast.LENGTH_LONG).show();
                                }
                            });
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Toast.makeText(getContext(),"Failed!",Toast.LENGTH_LONG).show();
                        }
                    });
                }
            });
            viewRequestsAdapter.addAll(data);
            rvRequests.removeItemDecoration(headersDecor);
            headersDecor = new StickyRecyclerHeadersDecoration(viewRequestsAdapter);

            rvRequests.addItemDecoration(headersDecor);
//            rvRequests.setAdapter(viewRequestsAdapter);
            rvRequests.setAdapter(viewRequestsAdapter);

//            viewRequestsAdapter.notifyDataSetChanged();
            viewRequestsAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                @Override
                public void onChanged() {
                    headersDecor.invalidateHeaders();
                    super.onChanged();
                }
            });
            swipeContainer.setRefreshing(false);
        }
    }, 1000);
    }

}
