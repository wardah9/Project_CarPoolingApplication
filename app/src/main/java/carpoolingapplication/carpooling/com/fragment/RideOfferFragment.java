package carpoolingapplication.carpooling.com.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import carpoolingapplication.carpooling.com.R;
import carpoolingapplication.carpooling.com.RegistrationActivity;
import carpoolingapplication.carpooling.com.map.MapsActivity;


public class RideOfferFragment extends Fragment {


    private Button searchCar;

    public RideOfferFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.activity_ride_offer, container, false);
        searchCar = (Button) rootView.findViewById(R.id.search);



        searchCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              startActivity(new Intent(getActivity(), MapsActivity.class));
            }
        });



        return rootView;

    }

}
