package layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.julia.wirtec_iticket.EventParcelable;
import com.example.julia.wirtec_iticket.R;
import com.example.julia.wirtec_iticket.TicketParcelable;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Glenn on 4/6/2017.
 */

public class TicketDetails extends Fragment {
    TicketParcelable tp;
    TextView place, datetime, about, price, numtickets;

    public TicketDetails() {}

    public void setTicket(TicketParcelable tp){
        this.tp = tp;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.content_view_ticket_details, container, false);

        /*code = (TextView) v.findViewById(R.id.ed_requestcode_value);
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
        about.setText(ep.getEventdesc());*/

        /*place = (TextView) v.findViewById(R.id.td_place_value);
        datetime = (TextView) v.findViewById(R.id.td_datetime_value);
        about = (TextView) v.findViewById(R.id.td_about_value);
        price = (TextView) v.findViewById(R.id.td_price_value);
        numtickets = (TextView) v.findViewById(R.id.td_numtickets_value);

        place.setText(tp.getPlace());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, MMM dd, yyyy ' at '", Locale.ENGLISH);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(" HH:mm", Locale.ENGLISH);
        Date n = new Date(tp.getDate());
        Time n2 = new Time(tp.getTime());
        datetime.setText(simpleDateFormat.format(n) + simpleDateFormat2.format(n2));
        about.setText(tp.getEventdesc());*/

        return v;
    }
}
