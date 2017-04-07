package com.example.julia.wirtec_iticket;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
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

    private ArrayList<String> uid = new ArrayList<>();
    ViewGroup parent;
    DataSnapshot ds;
    String us;
    int key;
    public ViewRequestsAdapter(final ArrayList <String> data, DatabaseReference ref){
        this.ref = ref;



        ChildEventListener cel2 = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ds = dataSnapshot;
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
//                    for(DataSnapshot snapshot2:snapshot.getChildren()){
                        Request eve = snapshot.getValue(Request.class);


                        eid.add(dataSnapshot.getKey());
                        e.add(eve);
                        add(snapshot.getKey());
                        uid.add(eve.getUid());
                        int ccc = eid.indexOf(dataSnapshot.getKey());
                    key = ccc;
                    us = snapshot.getKey();
                    notifyItemInserted(ccc);
//                data.add(dataSnapshot.getKey());
//                        notifyItemInserted(e.size() - 1);
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
                Request rTemp;
                int ctr=0;


//
                for(DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    // snapshot, event level

                    Request eve = snapshot.getValue(Request.class);
                    ds = snapshot;
                    // find out the range of headers that will be affected given this event id
                    int first = eid.indexOf(dataSnapshot.getKey());
                    int last = eid.lastIndexOf(dataSnapshot.getKey());
                    int key = -1;
                    for(int x = first; x < last + 1; x++){
                        Log.i("adapter", "x is " + x + " eve uid is " + eve.getUid() + " uid x is " + uid.get(x) );
                        if(uid.get(x).equals(eve.getUid())){
                            key = x;
                        }
                    }
                    if(key == -1){
                        e.add(first,eve);
                        eid.add(first,dataSnapshot.getKey());
                        uid.add(first,eve.getUid());
                        notifyItemInserted(first);
                    }else{
                        e.set(key,eve);
//                            eid.set(key,dataSnapshot.getKey());
                        notifyItemChanged(key);
                    }



//                        eid.add(dataSnapshot.getKey());
//                        add(snapshot.getKey());
//                        e.add(eve);
//                    if (!eve.equals(e.get(ctr))) {
//                        e.set(ctr + 1, eve);
////                        eid.set(ctr,dataSnapshot.getKey());
//                        notifyItemChanged(ctr + 1);
//
//                    }
//                    ctr++;
                }

////                    for(DataSnapshot snapshot2:snapshot.getChildren()){
//                        Request eve = snapshot.getValue(Request.class);
//                        ds = snapshot;
//
//                        eid.add(dataSnapshot.getKey());
//                    add(snapshot.getKey());
//                        e.add(eve);
//
//                        notifyItemInserted(e.size() - 1);
////                    }

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
                int ctr = 0;
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){

////                    for(DataSnapshot snapshot2:dataSnapshot.getChildren()){
                    Request eve = snapshot.getValue(Request.class);
                    ds = snapshot;
                    // find out the range of headers that will be affected given this event id
                    int first = eid.indexOf(dataSnapshot.getKey());
                    int last = eid.lastIndexOf(dataSnapshot.getKey());
                    int key = -1;

                    for(int x = first; x < last + 1; x++){
                        Log.i("adapter", "x is " + x + " eve uid is " + eve.getUid() + " uid x is " + uid.get(x) );
                        if(uid.get(x).equals(eve.getUid())){
                            key = x;
                        }
                    }

                    uid.remove(key);
                    e.remove(key);
                    eid.remove(key);
//                            eid.set(key,dataSnapshot.getKey());
                    Log.i("Pumunta dito",key+"");
                    notifyItemRemoved(key);

//                    int c3 = eid.indexOf(ccc);
//                    int c4 = uid.indexOf(eve.getUid());
//                    uid.remove(c4);
//                    eid.remove(c4);
//                    e.remove(c4);
//                    notifyItemRemoved(c4);

//                        eid.add(dataSnapshot.getKey());
//                    add(snapshot.getKey());
//                        e.add(eve);
//
//                        if(!eve.equals(e.get(ctr))){
//                            e.remove(ctr-1);
//                            eid.remove(ctr-1);
//                            notifyItemRemoved(ctr);
//                        }
//                    ctr++;
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

//        this.data = eid;
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
    public void onBindViewHolder(ViewRequestsViewHolder holder,final int position) {
//        String curr = data.get(position);
        final Request req = e.get(position);
        //set image
        
        holder.name.setText(req.getName());
//        holder.name.setText(req.getName());
        holder.name.setTypeface(null, Typeface.BOLD);
        /*holder.event.setText(req.getEvent().toString());*/
        holder.email.setText(req.getEmail());
        holder.numtickets.setText(req.getNumberOfTicketRequested());

        holder.accept.setTag(req);
        holder.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnLongItemClickListener != null){
                    mOnLongItemClickListener.onLongItemClick(req,eid.get(position));
                }
            }
        });
        holder.reject.setTag(req);


        holder.reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mOnItemClickListener != null){
                    mOnItemClickListener.onItemClick(req,eid.get(position));
                }

            }
        });
    }
    public ArrayList<String> geteid(){
        return eid;
    }

    @Override
    public long getHeaderId(int position) {
        //Log.i("header", "position " + position +  "\t" +   eid.get(position).hashCode() + " for header: " + eid.get(position));
        // Log.i("position", position + "");
        return eid.get(position).hashCode();
        //return position;
    }

    @Override
    public long getItemId(int position) {
        //return super.getItemId(position);
        Log.i("asdas", " " + eid.get(position) + "" +  e.get(position).getUid());
        return (eid.get(position) +  e.get(position).getUid()).hashCode();
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


    private OnItemClickListener mOnItemClickListener;

    public void setmOnItemClickListener(OnItemClickListener onItemClickListener){
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        public void onItemClick(Request request, String code);
    }

    private OnLongItemClickListener mOnLongItemClickListener;

    public void setmOnLongItemClickListener(OnLongItemClickListener onLongItemClickListener){
        mOnLongItemClickListener = onLongItemClickListener;
    }

    public interface OnLongItemClickListener{
        public void onLongItemClick(Request request, String code);
    }


    @Override

    public int getItemCount() {
        return e.size();
    }

    public class ViewRequestsViewHolder extends RecyclerView.ViewHolder{
        dev.dworks.libs.astickyheader.ui.SquareImageView requestimage;
        /*TextView event;*/
        TextView name;
        TextView numtickets;
        TextView email;
        Button accept;
        Button reject;

        public ViewRequestsViewHolder(View itemView){
            super(itemView);

            requestimage = (dev.dworks.libs.astickyheader.ui.SquareImageView) itemView.findViewById(R.id.requestimage);
            /*event = (TextView) itemView.findViewById(R.id.requestevent);*/
            name = (TextView) itemView.findViewById(R.id.name);
            numtickets = (TextView) itemView.findViewById(R.id.numtickets);
            email = (TextView) itemView.findViewById(R.id.email);
            accept = (Button) itemView.findViewById(R.id.accept);
            reject = (Button) itemView.findViewById(R.id.reject);
        }



    }
}