package com.example.julia.wirtec_iticket;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.security.SecureRandom;
import java.util.ArrayList;

/**
 * Created by Julia on 3/10/2017.
 */

public class ViewRequestsAdapter extends TicketAdapter<ViewRequestsAdapter.ViewRequestsViewHolder> implements StickyRecyclerHeadersAdapter<RecyclerView.ViewHolder>{

    private DatabaseReference ref;
    private ChildEventListener cel;

    private ArrayList<Request> e = new ArrayList<>();
    private ArrayList<String> eid = new ArrayList<>();

    private ArrayList<String> data = new ArrayList<>();
    ViewGroup parent;
    DataSnapshot ds;
    String key,us;

    public ViewRequestsAdapter(final ArrayList <String> data, DatabaseReference ref){
        this.ref = ref;



        ChildEventListener cel2 = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
//                    for(DataSnapshot snapshot2:snapshot.getChildren()){
                        Request eve = snapshot.getValue(Request.class);
                        ds = snapshot;

                        eid.add(dataSnapshot.getKey());
                        e.add(eve);
                    add(snapshot.getKey());


//                data.add(dataSnapshot.getKey());
                        notifyItemInserted(e.size() - 1);
//                    }
                }
//                String eventCode = dataSnapshot.getKey();
//                String userUid = dataSnapshot.child(eventCode).getKey();
//                key = eventCode;
//                us = userUid;
//                Request eve = dataSnapshot.getValue(Request.class);
//                ds = dataSnapshot;
//                eid.add(dataSnapshot.getKey());
//                e.add(eve);
////                data.add(dataSnapshot.getKey());
//                notifyItemInserted(e.size() - 1);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
//                    for(DataSnapshot snapshot2:snapshot.getChildren()){
                        Request eve = snapshot.getValue(Request.class);
                        ds = snapshot;

                        eid.add(dataSnapshot.getKey());
                    add(snapshot.getKey());
                        e.add(eve);

                        notifyItemInserted(e.size() - 1);
//                    }
                }
//                Request ne = dataSnapshot.getValue(Request.class);
//                String neid = dataSnapshot.getKey();
//
//                int idx = eid.indexOf(neid);
//                if(idx > -1 ){
//                    e.set(idx,ne);
//                    notifyItemChanged(idx);
//                }

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
//                    for(DataSnapshot snapshot2:dataSnapshot.getChildren()){
                        Request eve = snapshot.getValue(Request.class);
                        ds = snapshot;

                        eid.add(dataSnapshot.getKey());
                    add(snapshot.getKey());
                        e.add(eve);
//                data.add(dataSnapshot.getKey());
                        notifyItemInserted(e.size() - 1);
//                    }
                }
//                String eke = dataSnapshot.getKey();
//                int index = eid.indexOf(eke);
//                if(index > -1){
//                    eid.remove(index);
//                    e.remove(index);
//                    notifyItemRemoved(index);
//                }
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        this.data = eid;
        ref.addChildEventListener(cel2);
//        addAll(eid);
        cel = cel2;
//        this.data = eid;
    }

    @Override
    //inflate XML (ticket)
    public ViewRequestsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.parent = parent;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.request, parent, false);
        //parent = RecyclerView is the ViewGroup
        //false = but will not be attached to it
        //v is now inflated ticket.xml
        return new ViewRequestsViewHolder(v);
    }

    @Override
    //change content of TextView to current data
    public void onBindViewHolder(ViewRequestsViewHolder holder, int position) {
        String curr = data.get(position);
        Request req = e.get(position);
        //set image
        
        holder.name.setText(req.getName());
        holder.name.setTypeface(null, Typeface.BOLD);
        holder.event.setText(req.getEvent().toString());
        holder.email.setText(req.getEmail());
        holder.numtickets.setText(req.getNumberOfTicketRequested());


        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContextThemeWrapper ctw = new ContextThemeWrapper(parent.getContext(), R.style.AlertDialogCustom);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctw);

                // set dialog message
                alertDialogBuilder
                        .setMessage("Are you sure you want to accept this request?")
                        .setCancelable(true)
                        .setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // get user input and set it to result
                                        // edit text
                                        Toast.makeText(parent.getContext(), "Request Accepted!", Toast.LENGTH_LONG).show();
                                    }
                                })
                        .setNegativeButton("No",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        });

        holder.reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContextThemeWrapper ctw = new ContextThemeWrapper(parent.getContext(), R.style.AlertDialogCustom);
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ctw);

                // set dialog message
                alertDialogBuilder
                        .setMessage("Are you sure you want to reject this request?")
                        .setCancelable(true)
                        .setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // get user input and set it to result
                                        // edit text
                                        Toast.makeText(parent.getContext(), "Request Denied!", Toast.LENGTH_LONG).show();
                                    }
                                })
                        .setNegativeButton("No",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog

                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        });
    }
    public ArrayList<String> geteid(){
        return eid;
    }

    @Override
    public long getHeaderId(int position) {
        return eid.get(position).hashCode();
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.header_test, parent, false);
        return new RecyclerView.ViewHolder(view) {
        };
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        TextView textView = (TextView) holder.itemView;
        Request req = e.get(position);
        textView.setText(req.getEvent());
//        holder.itemView.setBackgroundColor(parent.getResources().getColor(R.color.headers)); //nagerror
    }

//    private int getRandomColor() {
//        SecureRandom rgen = new SecureRandom();
//        return Color.HSVToColor(150, new float[]{
//                rgen.nextInt(359), 1, 1
//        });
//    }


    @Override
    //
    public int getItemCount() {
        return e.size();
    }

    public class ViewRequestsViewHolder extends RecyclerView.ViewHolder{
        dev.dworks.libs.astickyheader.ui.SquareImageView requestimage;
        TextView event;
        TextView name;
        TextView numtickets;
        TextView email;
        ImageButton accept;
        ImageButton reject;

        public ViewRequestsViewHolder(View itemView){
            super(itemView);

            requestimage = (dev.dworks.libs.astickyheader.ui.SquareImageView) itemView.findViewById(R.id.requestimage);
            event = (TextView) itemView.findViewById(R.id.requestevent);
            name = (TextView) itemView.findViewById(R.id.name);
            numtickets = (TextView) itemView.findViewById(R.id.numtickets);
            email = (TextView) itemView.findViewById(R.id.email);
            accept = (ImageButton) itemView.findViewById(R.id.accept);
            reject = (ImageButton) itemView.findViewById(R.id.reject);
        }



    }
}