package layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.julia.wirtec_iticket.EventParcelable;
import com.example.julia.wirtec_iticket.R;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventDetails extends Fragment {
    EventParcelable ep;
    TextView code, loc, datetime, about;
    public EventDetails() {
        // Required empty public constructor
    }
    public void setEvent(EventParcelable ep){
        this.ep = ep;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_event_details, container, false);
//        TextView = (TextView) v.findViewById(R.id.)
        code = (TextView) v.findViewById(R.id.ed_requestcode_value);
        loc = (TextView) v.findViewById(R.id.ed_place_value);
        datetime = (TextView) v.findViewById(R.id.ed_datetime_value);
        about = (TextView) v.findViewById(R.id.ed_about_value);
        code.setText(ep.getCode());
        loc.setText(ep.getPlace());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, MMM dd, yyyy ' at '", Locale.ENGLISH);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(" HH:mm", Locale.ENGLISH);
        Date n = new Date(ep.getDate());
        Time n2 = new Time(ep.getTime());
        datetime.setText(simpleDateFormat.format(n) + simpleDateFormat2.format(n2));
        about.setText(ep.getEventdesc());
        return v;

    }

}
