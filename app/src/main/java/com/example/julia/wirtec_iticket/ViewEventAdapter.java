package com.example.julia.wirtec_iticket;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Julia on 3/10/2017.
 */

public class ViewEventAdapter extends RecyclerView.Adapter<ViewEventAdapter.ViewEventViewHolder>{

    private ArrayList<String> data;

    public ViewEventAdapter(ArrayList <String> data){
        this.data = data;
    }

    @Override
    //inflate XML (ticket)
    public ViewEventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.event, parent, false);
        //parent = RecyclerView is the ViewGroup
        //false = but will not be attached to it
        //v is now inflated ticket.xml
        return new ViewEventViewHolder(v);
    }

    @Override
    //change content of TextView to current data
    public void onBindViewHolder(ViewEventViewHolder holder, int position) {
        String curr = data.get(position);
        //set image
        holder.event.setText(curr);
        holder.event.setTypeface(null, Typeface.BOLD);
        holder.place.setText(curr);
        holder.datetime.setText(curr);
        holder.attendees.setText(curr);
    }

    @Override
    //
    public int getItemCount() {
        return data.size();
    }

    public class ViewEventViewHolder extends RecyclerView.ViewHolder{
        dev.dworks.libs.astickyheader.ui.SquareImageView eventimage;
        TextView event;
        TextView place;
        TextView datetime;
        TextView attendees;

        public ViewEventViewHolder (View itemView){
            super(itemView);
            eventimage = (dev.dworks.libs.astickyheader.ui.SquareImageView) itemView.findViewById(R.id.eventimage);
            event = (TextView) itemView.findViewById(R.id.eventname);
            place = (TextView) itemView.findViewById(R.id.eventplace);
            datetime = (TextView) itemView.findViewById(R.id.eventdatetime);
            attendees = (TextView) itemView.findViewById(R.id.attendees);

        }


    }
}
