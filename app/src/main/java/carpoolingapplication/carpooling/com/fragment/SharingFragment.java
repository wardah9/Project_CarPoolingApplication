package carpoolingapplication.carpooling.com.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import carpoolingapplication.carpooling.com.R;
import carpoolingapplication.carpooling.com.map.MapsActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class SharingFragment extends Fragment {


    private Button shareGoogle;
    private Button shareFacebook;

    public SharingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_sharing, container, false);
        shareGoogle = (Button) rootView.findViewById(R.id.googleID);
        shareFacebook = (Button) rootView.findViewById(R.id.facebookID);


        shareGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://plus.google.com")));
            }
        });



        shareFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com")));
            }
        });

        return rootView;


    }

}
