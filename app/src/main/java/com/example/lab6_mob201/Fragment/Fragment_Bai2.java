package com.example.lab6_mob201.Fragment;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import com.example.lab6_mob201.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;


public class Fragment_Bai2 extends Fragment implements OnMapReadyCallback {
    private SearchView searchView;
    private GoogleMap mMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__bai2, container, false);
        this.initViewById(view);
        this.initFragmentMap();
        return view;
    }


    private void initViewById(View view) {
        this.searchView = view.findViewById(R.id.sv_searchMap);
    }

    private void initFragmentMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.fragment_map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {

                Double latitude = 21.0381328;
                Double longitude = 105.7445984;
                LatLng latLng = new LatLng(latitude, longitude);
                googleMap.addMarker(new MarkerOptions().position(latLng).title("Fpoly Ha Noi"));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
            }
        });

        this.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String locationString = searchView.getQuery().toString();
                List<Address> addressList = null;
                if (locationString != null || locationString.equals("")) {
                    Geocoder geocoder = new Geocoder(getActivity());
                    try {
                        addressList = geocoder.getFromLocationName(locationString, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Address address = addressList.get(0);

                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(latLng).title(locationString));
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.mMap = googleMap;
    }
}