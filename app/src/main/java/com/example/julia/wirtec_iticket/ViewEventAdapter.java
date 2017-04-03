package com.example.julia.wirtec_iticket;

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

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Julia on 3/10/2017.
 */

public class ViewEventAdapter extends RecyclerView.Adapter<ViewEventAdapter.ViewEventViewHolder>{

    private ArrayList<String> data;
    private DatabaseReference ref;
    private ChildEventListener cel;

    private ArrayList<Event> e = new ArrayList<>();
    private ArrayList<String> eid = new ArrayList<>();

    public ViewEventAdapter(ArrayList <String> data, DatabaseReference ref){
        this.data = data;
        this.ref = ref;

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
        cel = cel2;

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
        Event te = e.get(position);
        //set image
        holder.event.setText(te.getEventname());
        holder.event.setTypeface(null, Typeface.BOLD);
        holder.place.setText(te.getPlace());
        Date n = new Date(te.getDate());
        Time n2 = new Time(te.getTime());


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, MMMMMMMM dd, yyyy ' at '", Locale.ENGLISH);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(" HH:mm", Locale.ENGLISH);
        holder.v.setTag(position);
        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnItemEventClickListener != null){
                    int position = (int) v.getTag();
                    mOnItemEventClickListener.onItemClick(e.get(position),eid.get(position));
                }
            }
        });

        holder.datetime.setText(simpleDateFormat.format(n) + simpleDateFormat2.format(n2));
        holder.attendees.setText(te.getChecker());
    }

    @Override
    //
    public int getItemCount() {
        return e.size();
    }


    private OnItemEventClickListener mOnItemEventClickListener;

    public void setmOnItemEventClickListener(OnItemEventClickListener onItemEventClickListener){
        mOnItemEventClickListener = onItemEventClickListener;
    }

    public interface OnItemEventClickListener{
        public void onItemClick(Event event, String eventId);
    }


    public class ViewEventViewHolder extends RecyclerView.ViewHolder{
        dev.dworks.libs.astickyheader.ui.SquareImageView eventimage;
        TextView event;
        TextView place;
        TextView datetime;
        TextView attendees;
        View v;
        public ViewEventViewHolder (View itemView){
            super(itemView);
            eventimage = (dev.dworks.libs.astickyheader.ui.SquareImageView) itemView.findViewById(R.id.eventimage);
            event = (TextView) itemView.findViewById(R.id.eventname);
            place = (TextView) itemView.findViewById(R.id.eventplace);
            datetime = (TextView) itemView.findViewById(R.id.eventdatetime);
            attendees = (TextView) itemView.findViewById(R.id.attendees);
            v = itemView.findViewById(R.id.ticket);

        }


    }
}
