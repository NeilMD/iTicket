package com.example.julia.wirtec_iticket;

/**
 * Created by Julia on 2/21/2017.
 */

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.widget.*;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.security.SecureRandom;
import java.util.ArrayList;

/**
 * Created by Julia on 1/26/2017.
 */

public class ViewTicketsAdapter extends RecyclerView.Adapter<ViewTicketsAdapter.ViewTicketsViewHolder>{

    private ArrayList<String> data;
    private DatabaseReference ref;
    private LayoutInflater mInflater;
    private ChildEventListener cel;

    private ArrayList<String> eid = new ArrayList<>();
    private ArrayList<Event> e = new ArrayList<>();

    public ViewTicketsAdapter(ArrayList <String> data, DatabaseReference ref){
        this.ref = ref;
        this.data = data;

        ChildEventListener cel2 = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Event eve = dataSnapshot.getValue(Event.class);

                eid.add(dataSnapshot.getKey());
                e.add(eve);

                notifyItemInserted(e.size() - 1);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Event ne = dataSnapshot.getValue(Event.class);
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
        cel =cel2;


    }

    @Override
    //inflate XML (ticket)
    public ViewTicketsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mInflater = LayoutInflater.from(parent.getContext());
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket, parent, false);
        //parent = RecyclerView is the ViewGroup
        //false = but will not be attached to it
        //v is now inflated ticket.xml
        return new ViewTicketsAdapter.ViewTicketsViewHolder(v);
    }


    @Override
    public int getItemCount() {
        return e.size();
    }


    @Override
    //change content of TextView to current data
    public void onBindViewHolder(ViewTicketsViewHolder holder, int position) {
        Event currE = e.get(position);

        String curr = data.get(position);
        //set image here
        holder.event.setText(currE.getEventname());
        holder.event.setTypeface(null, Typeface.BOLD);
        holder.place.setText(currE.getPlace());
        holder.datetime.setText(currE.getPlace() + ", "+ currE.getTime() );
        holder.numtickets.setText(currE.getNumberOfTickets()+ "");
        holder.status.setText(currE.getStatus());
        holder.status.setTypeface(null, Typeface.BOLD);
        /**set text background accdg to status
         <WHITE = UNUSED>
         <GREEN = USED>
         <YELLOW = NO EXIT>
         <RED = EXPIRED/REQUEST DENIED>**/
        holder.status.setBackgroundColor(Color.WHITE);
        holder.status.setTextColor(Color.BLACK);

    }



    public class ViewTicketsViewHolder extends RecyclerView.ViewHolder {
        dev.dworks.libs.astickyheader.ui.SquareImageView image;
        TextView event;
        TextView place;
        TextView datetime;
        TextView numtickets;
        TextView status;

        public ViewTicketsViewHolder(View itemView) {
            super(itemView);
            image = (dev.dworks.libs.astickyheader.ui.SquareImageView) itemView.findViewById(R.id.ticketimage);
            event = (TextView) itemView.findViewById(R.id.event);
            place = (TextView) itemView.findViewById(R.id.place);
            datetime = (TextView) itemView.findViewById(R.id.datetime);
            numtickets = (TextView) itemView.findViewById(R.id.numtickets);
            status = (TextView) itemView.findViewById(R.id.status);
        }


    }


}

