package carpoolingapplication.carpooling.com.fragment;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import carpoolingapplication.carpooling.com.R;
import yalantis.com.sidemenu.interfaces.ScreenShotable;


public class AboutFragment extends Fragment implements ScreenShotable {
//    public static final String CLOSE = "Close";
//    public static final String SERVICES = "Services";
//    public static final String SHARE_MY_CAR = "Share my Car";
//    public static final String FIND_A_CAR = "Find a Ride";
//    public static final String HISTORY = "History";
//    public static final String MY_ACCOUNT = "My Account";
//    public static final String COMMUNICATE = "Communicate";
//    public static final String SETTINGS = "Settings";
//    public static final String SHARING = "Sharing";

    private View containerView;
    protected ImageView mImageView;
    protected int res;
    private Bitmap bitmap;

    public static AboutFragment newInstance(int resId) {
        AboutFragment contentFragment = new AboutFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Integer.class.getName(), resId);
        contentFragment.setArguments(bundle);
        return contentFragment;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.containerView = view.findViewById(R.id.container2);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        res = getArguments().getInt(Integer.class.getName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_register, container, false);
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
                Bitmap bitmap = Bitmap.createBitmap(containerView.getWidth(), containerView.getHeight(), Bitmap.Config.ARGB_4444);  //ARGB_4444
                Canvas canvas = new Canvas(bitmap);
                containerView.draw(canvas);
               AboutFragment.this.bitmap = bitmap;

            }
        };

        thread.start();

    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }
}

