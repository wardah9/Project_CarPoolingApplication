package carpoolingapplication.carpooling.com.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import carpoolingapplication.carpooling.com.R;
import carpoolingapplication.carpooling.com.map.MapsActivity;
import yalantis.com.sidemenu.interfaces.ScreenShotable;

/**
 * Created by Konstantin on 22.12.2014.
 */
public class ContentFragment extends Fragment implements ScreenShotable {
    public static final String CLOSE = "Close";
    public static final String SERVICES = "Services";
    public static final String HISTORY = "History";
    public static final String MY_ACCOUNT = "My Account";
    public static final String SETTINGS = "Settings";
    public static final String SHARING = "Sharing";
    public static final String ABOUT = "About";

    private View containerView;
//    protected ImageView mImageView;
    protected int res;
    private Bitmap bitmap;
    private Button shareCar;
    private Button findCar;

    public static ContentFragment newInstance(int resId) {
        ContentFragment contentFragment = new ContentFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Integer.class.getName(), resId);
        contentFragment.setArguments(bundle);
        return contentFragment;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.containerView = view.findViewById(R.id.container);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        res = getArguments().getInt(Integer.class.getName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
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
//        mImageView = (ImageView) rootView.findViewById(R.id.image_content);
//        mImageView.setClickable(true);
//        mImageView.setFocusable(true);
//        mImageView.setImageResource(res);
        return rootView;
    }

    @Override
    public void takeScreenShot() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                Bitmap bitmap = Bitmap.createBitmap(containerView.getWidth(), containerView.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                containerView.draw(canvas);
                ContentFragment.this.bitmap = bitmap;
            }
        };

        thread.start();

    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }
}