package com.example.user.ch_mapver;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewConfiguration;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class Recode extends AppCompatActivity implements LocationListener, GoogleMap.OnMapClickListener, GoogleMap.OnMapLongClickListener {
    private GoogleMap googleMap;
    private PolylineOptions polylineOptions;
    private ArrayList<LatLng> arrayPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recode);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(0xFF116911));


        try{
            ViewConfiguration config=ViewConfiguration.get(this);
            Field menuKeyField=ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            if(menuKeyField!=null){
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }

        } catch(Exception ex){

        }
        arrayPoints = new ArrayList<LatLng>();

        MapsInitializer.initialize(getApplicationContext());
        SupportMapFragment supportMapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map2);
        googleMap = supportMapFragment.getMap();
        googleMap.setMyLocationEnabled(true);
        googleMap.setOnMapClickListener(this);
        googleMap.setOnMapLongClickListener(this);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setCompassEnabled(true);
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String bestProvider = locationManager.getBestProvider(criteria, true);
        Location location = locationManager.getLastKnownLocation(bestProvider);
        if (location != null) {
            onLocationChanged(location);
        }
        locationManager.requestLocationUpdates(bestProvider, 20000, 0, this);

    }

    @Override
    public void onLocationChanged(Location location) {

        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        LatLng latLng = new LatLng(latitude, longitude);
        //googleMap.addMarker(new MarkerOptions().position(latLng));
        //googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17.0f));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
        //googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        //googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));



    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub
    }

    private boolean isGooglePlayServicesAvailable() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == status) {
            return true;
        } else {
            GooglePlayServicesUtil.getErrorDialog(status, this, 0).show();
            return false;
        }
    }


    @Override
    public void onMapClick(LatLng latLng) {
        MarkerOptions markerOptions = new MarkerOptions();
        double lati=0;
        double longi=0;
        lati=latLng.latitude;
        longi=latLng.longitude;
        // Setting latitude and longitude of the marker position
        markerOptions.position(latLng);

        // Setting titile of the infowindow of the marker
        markerOptions.title("위치");

        // Setting the content of the infowindow of the marker
        markerOptions.snippet("위도:" + lati + "," + "경도:" + longi);

        // Instantiating the class PolylineOptions to plot polyline in the map
        PolylineOptions polylineOptions = new PolylineOptions();
        // Setting the color of the polyline
        polylineOptions.color(Color.RED);
        // Setting the width of the polyline
        polylineOptions.width(3);
        // Adding the taped point to the ArrayList
        arrayPoints.add(latLng);
        // Setting points of polyline
        polylineOptions.addAll(arrayPoints);
        // Adding the polyline to the map
        googleMap.addPolyline(polylineOptions);

        // Adding the marker to the map
        googleMap.addMarker(markerOptions);
        //googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
    }

    @Override
    public void onMapLongClick(LatLng arg0) {
        googleMap.clear();
        arrayPoints.clear();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml

        //noinspection SimplifiableIfStatement
        switch(item.getItemId()){
            case R.id.info:
                Intent intent1=new Intent(this, Search.class);
                startActivity(intent1);
                return true;

            case R.id.login:
                Intent intent2=new Intent(this,Login.class);
                startActivity(intent2);
                return true;

            case R.id.recode:
                Intent intent3=new Intent(this,Recode.class);
                startActivity(intent3);
                return true;

            case R.id.group:
                Intent intent4=new Intent(this, Group.class);
                startActivity(intent4);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
