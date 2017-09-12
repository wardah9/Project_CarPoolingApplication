package carpoolingapplication.carpooling.com.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import carpoolingapplication.carpooling.com.R;

import static carpoolingapplication.carpooling.com.R.drawable.user_identity;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserAccountFragment extends Fragment {


    private Button update_bt;
    private ImageView img_profile;

    public UserAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_account, container, false);
        update_bt = (Button) rootView.findViewById(R.id.update_bt);
        img_profile= (ImageView) rootView.findViewById(R.id.img_profile);

        img_profile.setImageResource(R.drawable.user_identity);

        update_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return rootView;
    }

}
