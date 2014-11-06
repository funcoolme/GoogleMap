package com.example.l33534.google;

import android.app.Dialog;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Criteria;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by L33534 on 11/4/2014.
 */
public class Location extends FragmentActivity implements LocationListener {

   // static final LatLng HAMBURG = new LatLng(53.558, 9.927);
   // static final LatLng KIEL = new LatLng(53.551, 9.993);
    private GoogleMap map;
    SensorManager sensorManager;
    int orientationSensor;
    float headingAngle;
    float pitchAngle;
    float rollAngle;
    final static String TAG = "PAAR";
    //private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());

        if(status!= ConnectionResult.SUCCESS){

            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
            dialog.show();

        }else {
            sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
            orientationSensor = Sensor.TYPE_ORIENTATION;
            sensorManager.registerListener(sensorEventListener, sensorManager
                    .getDefaultSensor(orientationSensor), SensorManager.SENSOR_DELAY_NORMAL);
            map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                    .getMap();

            map.setMyLocationEnabled(true);
            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            Criteria criteria = new Criteria();
            String provider = locationManager.getBestProvider(criteria, true);
            android.location.Location location = locationManager.getLastKnownLocation(provider);
            if(location != null){
                onLocationChanged(location);
            }
            locationManager.requestLocationUpdates(provider, 20000, 0, this);
        }
    }
    @Override
    public void onLocationChanged(android.location.Location location) {

  TextView tvLocation = (TextView) findViewById(R.id.tv_location);

                // Getting latitude of the current location
                double latitude = location.getLatitude();

                // Getting longitude of the current location
                double longitude = location.getLongitude();

                // Creating a LatLng object for the current location
                LatLng latLng = new LatLng(latitude, longitude);
                map.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title("ME"))
                        .setIcon(BitmapDescriptorFactory
                                .fromResource(R.drawable.boy));
                // Showing the current location in Google Map
                map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                // Zoom in the Google Map
                map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
                // Setting latitude and longitude in the TextView tv_location
                tvLocation.setText("Latitude:" +  latitude  + ", Longitude:"+ longitude );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.map_toogle, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
// Handle item selection
        switch (item.getItemId()) {
            case R.id.map:
                map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.sat:
                map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                break;
            case R.id.exit:
                map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
            default:
                break;
        }return super.onOptionsItemSelected(item);
    }

    final SensorEventListener sensorEventListener = new SensorEventListener() {
        public void onSensorChanged(SensorEvent sensorEvent) {
            if (sensorEvent.sensor.getType() == Sensor.TYPE_ORIENTATION) {
                headingAngle = sensorEvent.values[0];
                pitchAngle = sensorEvent.values[1];
                rollAngle = sensorEvent.values[2];

                Log.d(TAG, "Heading: " + String.valueOf(headingAngle));
                Log.d(TAG, "Pitch: " + String.valueOf(pitchAngle));
                Log.d(TAG, "Roll: " + String.valueOf(rollAngle));
                if (pitchAngle > 7 || pitchAngle < -7 || rollAngle > 7
                        || rollAngle < -7)
                {
                    launchCameraView();
                }
            }
        }

        public void launchCameraView() {
            finish();
       //Intent cameraView = new Intent(this, ASimpleAppUsingARActivity.class);
        //startActivity(cameraView);
        }

        @Override
        public void onAccuracyChanged(Sensor arg0, int arg1) {
            // TODO Auto-generated method stub
        }
    };

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onProviderDisabled(String s) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub
    }




}