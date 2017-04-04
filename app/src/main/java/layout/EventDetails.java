package layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.julia.wirtec_iticket.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventDetails extends Fragment {


    public EventDetails() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_event_details, container, false);
//        TextView = (TextView) v.findViewById(R.id.)

        return v;

    }

}
