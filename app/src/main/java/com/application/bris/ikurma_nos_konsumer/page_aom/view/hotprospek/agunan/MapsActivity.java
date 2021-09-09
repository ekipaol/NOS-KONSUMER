package com.application.bris.ikurma_nos_konsumer.page_aom.view.hotprospek.agunan;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.core.content.ContextCompat;
import android.widget.SearchView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.application.bris.ikurma_nos_konsumer.R;
import com.application.bris.ikurma_nos_konsumer.api.config.UriApi;
import com.application.bris.ikurma_nos_konsumer.api.model.ParseResponseGmapsV3;
import com.application.bris.ikurma_nos_konsumer.api.service.ApiClientAdapter;
import com.application.bris.ikurma_nos_konsumer.util.AppUtil;
import com.application.bris.ikurma_nos_konsumer.util.GPSTracker;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener, GoogleMap.OnMarkerDragListener,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener, View.OnClickListener, SearchView.OnQueryTextListener {

    private GoogleMap mMap;
    private Button konfirmasi;
    LatLng loc = null;
    LatLng currentLoc = null;
    Location currentLocation = null;
    Marker marker;
    RelativeLayout mapView;
    private RelativeLayout loading;
    private FloatingActionButton fab_recenter, fab_changetype;
    private SearchView searchView;
    private ApiClientAdapter apiClientAdapter;
    private LocationRequest mLocationRequest;
    private GoogleApiClient mGoogleApiClient;
    private LocationManager locateManager;
    private GPSTracker gpsTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        apiClientAdapter = new ApiClientAdapter(this, UriApi.Baseurl.URL_MAPS);
        gpsTracker = new GPSTracker(this);

        konfirmasi=findViewById(R.id.bt_konfirmasi_map);
        loading=findViewById(R.id.progressbar_loading);
        mapView=findViewById(R.id.rl_maps);
        fab_recenter = (FloatingActionButton) findViewById(R.id.fab_recenter);
        fab_changetype = (FloatingActionButton) findViewById(R.id.fab_changetype);
        searchView = (SearchView) findViewById(R.id.searchView);
        fab_recenter.setOnClickListener(this);
        fab_changetype.setOnClickListener(this);
        konfirmasi.setOnClickListener(this);
        searchView.setOnQueryTextListener(this);
        loading.setVisibility(View.VISIBLE);
        initializeMap();
    }

    public void initializeMap()
    {
        try {
            if (mGoogleApiClient == null) {
                buildGoogleApiClient();
            }

            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }
        catch (Exception e)
        {
            AppUtil.showToastShort(this, e.getMessage());
        }
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    public void searchGeocoding(String location)
    {
        loading.setVisibility(View.VISIBLE);
        Call<ParseResponseGmapsV3> call = apiClientAdapter.getApiInterface().geocoding(location, AppUtil.getGeocodingKey());
        call.enqueue(new Callback<ParseResponseGmapsV3>() {
            @Override
            public void onResponse(Call<ParseResponseGmapsV3> call, Response<ParseResponseGmapsV3> response) {
                try {
                    loading.setVisibility(View.GONE);
                    if (response.isSuccessful())
                    {
                        if (response.body().getStatus().equalsIgnoreCase("OK"))
                        {
                            if (response.body().getResults().size() > 0)
                            {
                                JsonObject job = response.body().getResults().get(0).getAsJsonObject();
                                String locationAddress = job.get("formatted_address").getAsString();
                                String lat = job.get("geometry").getAsJsonObject().get("location").getAsJsonObject().get("lat").getAsString();
                                String lng = job.get("geometry").getAsJsonObject().get("location").getAsJsonObject().get("lng").getAsString();
                                loc = new LatLng(AppUtil.parseDoubleWithDefault(lat, 0.0), AppUtil.parseDoubleWithDefault(lng, 0.0));
                                addMarker(loc, 15);
                            }
                            else{
                                AppUtil.showToastShort(MapsActivity.this, "Lokasi tidak ditemukan");
                            }
                        }
                        else{
                            AppUtil.showToastShort(MapsActivity.this, "Lokasi tidak ditemukan");
                        }
                    }
                    else {
                        AppUtil.showToastShort(MapsActivity.this, "Service mengalami kendala, coba cara manual dengan klik pada Maps atau ulangi pencarian");
                    }
                }
                catch (Exception e)
                {
                    AppUtil.showToastShort(MapsActivity.this, e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ParseResponseGmapsV3> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.showToastShort(MapsActivity.this, getString(R.string.txt_connection_failure));
            }
        });
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
        mapView.setVisibility(View.VISIBLE);
        // Add a marker in Sydney and move the camera
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        }
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(false);
        konfirmasi.setVisibility(View.VISIBLE);
        loading.setVisibility(View.GONE);
        mMap.setOnMapClickListener(this);
        mMap.setOnMarkerDragListener(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(Activity.RESULT_OK,
                new Intent().putExtra("latitude", "0").putExtra("longitude", "0"));
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.fab_recenter :
                addMarker(currentLoc, 17);
                break;

            case R.id.fab_changetype:
                if (mMap.getMapType() == GoogleMap.MAP_TYPE_NORMAL)
                {
                    mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                }
                else if (mMap.getMapType() == GoogleMap.MAP_TYPE_SATELLITE)
                {
                    mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                }
                else if (mMap.getMapType() == GoogleMap.MAP_TYPE_HYBRID)
                {
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                }
                else {
                    mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                }
                break;

            case R.id.bt_konfirmasi_map:
                try {
                    if (marker != null )
                    {
                        setResult(Activity.RESULT_OK, new Intent().putExtra("latitude", String.valueOf(String.format("%.8f", marker.getPosition().latitude).replace(",", ".")))
                                .putExtra("longitude", String.valueOf(String.format("%.8f", marker.getPosition().longitude).replace(",", "."))));
                        finish();
                    }
                    else {
                        AppUtil.showToastShort(this, "Lokasi tidak ditemukan");
                    }
                }
                catch (Exception e)
                {
                    AppUtil.showToastShort(this, e.getMessage());
                }
                break;
        }
    }

    public void showMyLocation(LatLng loc, int zoom)
    {
        try {
            // animate to my location
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(loc)      // Sets the center of the map to Mountain View
                    .zoom(zoom)                   // Sets the zoom
                    .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
        catch (Exception e)
        {
            AppUtil.showToastShort(this, e.getMessage());
        }
    }

    public void addMarker(LatLng loc, int zoom)
    {
        if (marker != null)
        {
            marker.remove();
        }
        marker=mMap.addMarker(new MarkerOptions().position(loc).title(String.valueOf(loc.latitude)+", "+String.valueOf(loc.longitude)));
        marker.showInfoWindow();
        marker.setDraggable(true);
        mMap.setOnMarkerDragListener(this);
        showMyLocation(loc, zoom);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        String location = searchView.getQuery().toString();
        if (location != null || !location.isEmpty())
        {
            searchGeocoding(location);
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        try {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, (LocationListener) this);
                currentLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        if (currentLocation != null){
            currentLoc = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        }
        else{
            currentLoc = new LatLng(gpsTracker.getLatitude(), gpsTracker.getLongitude());
            gpsTracker.stopUsingGPS();
        }
        addMarker(currentLoc, 15);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null)
        {
            currentLoc = new LatLng(location.getLatitude(), location.getLongitude());
        }
        else{
            currentLoc = new LatLng(gpsTracker.getLatitude(), gpsTracker.getLongitude());
            gpsTracker.stopUsingGPS();
        }
        addMarker(currentLoc, 15);
        //stop location updates
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        mMap.animateCamera(CameraUpdateFactory.newLatLng(marker.getPosition()));
        marker.setTitle(String.valueOf(marker.getPosition().latitude)+", "+String.valueOf(marker.getPosition().longitude));
        marker.showInfoWindow();
        loc = new LatLng(marker.getPosition().latitude, marker.getPosition().longitude);
        showMyLocation(loc, 15);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        addMarker(latLng, 15);
        loc = latLng;
        Toast.makeText(MapsActivity.this, "Klik tahan pada marker untuk menggeser marker", Toast.LENGTH_SHORT).show();
    }
}
