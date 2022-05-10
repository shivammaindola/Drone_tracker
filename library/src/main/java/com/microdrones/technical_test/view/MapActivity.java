package com.microdrones.technical_test.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.microdrones.test.example.view.R;

import java.util.ArrayList;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    // creating array list for adding all our locations.
    private ArrayList<LatLng> locationArrayList;
    private String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Bundle b = getIntent().getExtras();
        //getting the arraylist from the key
        ArrayList<LatLng> locations = (ArrayList<LatLng>) b.getSerializable("locations");

        status = (String) b.getSerializable("status");

        if(status.equals("Successful")){
            Toast.makeText(this, "Mission Successful", Toast.LENGTH_LONG).show();
        }
        else{
                Toast.makeText(this, "Mission UnSuccessful", Toast.LENGTH_LONG).show();

        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.active_map);
        mapFragment.getMapAsync(this);

        // in below line we are initializing our array list.
        locationArrayList = locations;

        // on below line we are adding our
        // locations in our array list.

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // inside on map ready method
        // we will be displaying all our markers.
        // for adding markers we are running for loop and
        // inside that we are drawing marker on our map.

        if(status.equals("Successful")) {
            mMap.addMarker(new MarkerOptions().position(locationArrayList.get(0)).title("Drone").icon(BitmapFromVector(getApplicationContext(), R.drawable.ic_location_green)).snippet("Start Point"));
            mMap.addMarker(new MarkerOptions().position(locationArrayList.get(locationArrayList.size()-1)).title("Drone").icon(BitmapFromVector(getApplicationContext(), R.drawable.ic_location_red)).snippet("Drone Reached")).showInfoWindow();
        }
        else{
            mMap.addMarker(new MarkerOptions().position(locationArrayList.get(0)).title("Drone").icon(BitmapFromVector(getApplicationContext(), R.drawable.ic_location_green)).snippet("Start Point"));
            mMap.addMarker(new MarkerOptions().position(locationArrayList.get(locationArrayList.size()-1)).title("Drone").icon(BitmapFromVector(getApplicationContext(), R.drawable.ic_location_red)).snippet("Failed to reach")).showInfoWindow();
        }



        for (int i = 0; i < locationArrayList.size(); i++) {
            // below lin is use to zoom our camera on map.
            mMap.animateCamera(CameraUpdateFactory.zoomTo(18.0f));

            // below line is use to move our camera to the specific location.
            mMap.moveCamera(CameraUpdateFactory.newLatLng(locationArrayList.get(i)));

            if(i<locationArrayList.size()-1)
            mMap.addPolyline(new PolylineOptions()
                    .add(new LatLng(locationArrayList.get(i).latitude, locationArrayList.get(i).longitude), new LatLng(locationArrayList.get(i+1).latitude, locationArrayList.get(i+1).longitude))
                    .width(5)
                    .color(Color.RED));


        }
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(locationArrayList.get(0).latitude, locationArrayList.get(0).longitude),15));

    }

    private BitmapDescriptor BitmapFromVector(Context context, int vectorResId) {
        // below line is use to generate a drawable.
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);

        // below line is use to set bounds to our vector drawable.
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());

        // below line is use to create a bitmap for our
        // drawable which we have added.
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);

        // below line is use to add bitmap in our canvas.
        Canvas canvas = new Canvas(bitmap);

        // below line is use to draw our
        // vector drawable in canvas.
        vectorDrawable.draw(canvas);

        // after generating our bitmap we are returning our bitmap.
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
}
