package carpoolingapplication.carpooling.com;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import carpoolingapplication.carpooling.com.fragment.InsertDataFragment;
import carpoolingapplication.carpooling.com.fragment.RideOfferFragment;

public class user_registration extends AppCompatActivity {



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                     setFragment(new InsertDataFragment());
                    return true;
                case R.id.navigation_dashboard:
                    //setFragment(new RideOfferFragment());
                    startActivity(new Intent(user_registration.this,InsertCarData.class));
                    return true;
                case R.id.navigation_notifications:
                    startActivity(new Intent(user_registration.this,BarcodeScanner.class));
                    return true;
            }
            return false;
        }

    };

    private Fragment currentFragment;
    void setFragment(Fragment fragment) {

        if (fragment == null)
            return;
        FragmentManager fragmentManager = getFragmentManager();

        if (fragmentManager != null) {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            if (ft != null) {
                ft.replace(R.id.content, fragment);
                ft.commit();

            }
            currentFragment = fragment;
            //  Log.i("test", currentFragment!!.id.toString());
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.content,new InsertDataFragment());
        transaction.addToBackStack(null);
        transaction.commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
