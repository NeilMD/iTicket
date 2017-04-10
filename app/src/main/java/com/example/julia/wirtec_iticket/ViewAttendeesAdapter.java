package com.example.julia.wirtec_iticket;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

import layout.ViewAttendees;

/**
 * Created by Julia on 3/11/2017.
 */

public class ViewAttendeesAdapter extends RecyclerView.Adapter<ViewAttendeesAdapter.ViewAttendeesViewHolder>{

    private ArrayList<String> data;
    private DatabaseReference ref;
    private ChildEventListener cel;

    private ArrayList<Account> e = new ArrayList<>();
    private ArrayList<String> eid = new ArrayList<>();

    public ViewAttendeesAdapter(ArrayList <String> data, DatabaseReference ref){

        this.data = data;

        this.ref = ref;

        ChildEventListener cel2 = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Account eve = dataSnapshot.getValue(Account.class);

                eid.add(dataSnapshot.getKey());
                e.add(eve);

                notifyItemInserted(e.size() - 1);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Account ne = dataSnapshot.getValue(Account.class);
                String neid = dataSnapshot.getKey();

                int idx = eid.indexOf(neid);
                if(idx > -1 ){
                    e.set(idx,ne);
                    notifyItemChanged(idx);
                }

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String eke = dataSnapshot.getKey();
                int index = eid.indexOf(eke);
                if(index > -1){
                    eid.remove(index);
                    e.remove(index);
                    notifyItemRemoved(index);
                }
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        ref.addChildEventListener(cel2);
        cel = cel2;
    }

    @Override
    //inflate XML (ticket)
    public ViewAttendeesAdapter.ViewAttendeesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.attendees, parent, false);
        //parent = RecyclerView is the ViewGroup
        //false = but will not be attached to it
        //v is now inflated ticket.xml
        return new ViewAttendeesAdapter.ViewAttendeesViewHolder(v);
    }

    @Override
    //change content of TextView to current data
    public void onBindViewHolder(ViewAttendeesAdapter.ViewAttendeesViewHolder holder, int position) {
        String curr = data.get(position);
        Account acc = e.get(position);
        //set image here
        holder.name.setText(acc.getName());
        holder.name.setTypeface(null, Typeface.BOLD);
        holder.email.setText(acc.getEmail());
//        holder.numTickets.setText(curr);

    }

    @Override
    //
    public int getItemCount() {
        return e.size();
    }

    public class ViewAttendeesViewHolder extends RecyclerView.ViewHolder{
        dev.dworks.libs.astickyheader.ui.SquareImageView image;
        TextView name;
        TextView email;
        TextView numTickets;


        public ViewAttendeesViewHolder (View itemView){
            super(itemView);
            image = (dev.dworks.libs.astickyheader.ui.SquareImageView) itemView.findViewById(R.id.attendeesimage);
            name = (TextView) itemView.findViewById(R.id.attendeesname);
            email = (TextView) itemView.findViewById(R.id.attendeesemail);
            numTickets = (TextView) itemView.findViewById(R.id.attnumtickets);

        }


    }
}

