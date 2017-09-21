package carpoolingapplication.carpooling.com.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import carpoolingapplication.carpooling.com.R;
import carpoolingapplication.carpooling.com.map.MapsActivity;


public class ServicesFragment extends Fragment {


    private Button shareCar;
    private Button findCar;

    public ServicesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_services, container, false);
        shareCar = (Button) rootView.findViewById(R.id.share_my_Car);
        findCar = (Button) rootView.findViewById(R.id.find_Car);


        shareCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MapsActivity.class));
            }
        });



        findCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RideOfferFragment Ride_Frag = new RideOfferFragment();
                getFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, Ride_Frag)
                        .addToBackStack("RideFragment")
                        .commit();
            }
        });

        return rootView;


    }

}
