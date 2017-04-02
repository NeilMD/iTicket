package com.example.julia.wirtec_iticket;

/**
 * Created by Julia on 2/21/2017.
 */

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.widget.*;

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

    public ViewTicketsAdapter(ArrayList <String> data, DatabaseReference ref){
        this.ref = ref;
        this.data = data;
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
        return data.size();
    }


    @Override
    //change content of TextView to current data
    public void onBindViewHolder(ViewTicketsViewHolder holder, int position) {
        String curr = data.get(position);
        //set image here
        holder.event.setText(curr);
        holder.event.setTypeface(null, Typeface.BOLD);
        holder.place.setText(curr);
        holder.datetime.setText(curr);
        holder.numtickets.setText(curr);
        holder.status.setText("STATUS");
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

