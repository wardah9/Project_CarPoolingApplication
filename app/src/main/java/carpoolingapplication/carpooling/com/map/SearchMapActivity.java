package carpoolingapplication.carpooling.com.map;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import carpoolingapplication.carpooling.com.R;

public class SearchMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        LatLng m1 = new LatLng(23.566393, 58.175441);
        LatLng m2 = new LatLng(23.6002642656, 58.1681359941);
        LatLng m3 = new LatLng(23.614228, 58.192399);
        LatLng m4 = new LatLng(23.622189, 58.203419);
        LatLng m5 = new LatLng(23.566393, 58.175441);


        mMap.addMarker(new MarkerOptions().position(m1).title("Marker"));
        mMap.addMarker(new MarkerOptions().position(m2).title("Marker"));
        mMap.addMarker(new MarkerOptions().position(m3).title("Marker"));
        mMap.addMarker(new MarkerOptions().position(m4).title("Marker"));
        mMap.addMarker(new MarkerOptions().position(m5).title("Marker"));


        mMap.moveCamera(CameraUpdateFactory.newLatLng(m2));
    }
}
