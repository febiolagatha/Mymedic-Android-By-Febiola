package com.example.tenagamedis;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Button btn_pick;
    private List<Place.Field> fields;
    final int place_piker_reg_code = 1;
    String name;
    LatLng latLng;
    ArrayList<LatLng>arrayList = new ArrayList<LatLng>();
    LatLng bdl = new LatLng(-5.422264, 105.258170);
    LatLng panjang = new LatLng(-5.471085, 105.322616);
    LatLng teluk= new LatLng(-5.444411, 105.277589);
    LatLng amanah = new LatLng(-5.452411, 105.261666);
    LatLng rawatinap = new LatLng(-5.440629, 105.270325);
    LatLng tugu = new LatLng(-5.410102, 105.267751);
    LatLng sehat = new LatLng(-5.404654, 105.317360);
    LatLng tirtayasa = new LatLng(-5.394954, 105.295964);
    LatLng kemiling = new LatLng(-5.399786, 105.211168);
    LatLng palapa = new LatLng(-5.416622, 105.247110);
    LatLng urip = new LatLng(-5.387314, 105.263529);
    LatLng rajabasa = new LatLng(-5.364001, 105.235025);
    ArrayList<String> title= new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //add items
        arrayList.add(bdl);
        arrayList.add(panjang);
        arrayList.add(teluk);
        arrayList.add(amanah);
        arrayList.add(rawatinap);
        arrayList.add(tugu);
        arrayList.add(sehat);
        arrayList.add(tirtayasa);
        arrayList.add(kemiling);
        arrayList.add(palapa);
        arrayList.add(urip);
        arrayList.add(rajabasa);
        //add title for marker
        title.add("Bandar Lampung");
        title.add("Klinik Pratama Kosasih Panjang");
        title.add("Klinik Pratama Kosasih Teluk Betung");
        title.add("Klinik Pratama Kosasih Amanah");
        title.add("Klinik Rawat Inap Dokter Spesialis Mitra Kosasih");
        title.add("KLINIK KOSASIH PASAR TUGU");
        title.add("Klinik Sehat Kosasih Lampung");
        title.add("Klinik Kosasih Tirtayasa");
        title.add("Klinik Kosasih Kemiling");
        title.add("Klinik Pratama Kosasih Palapa");
        title.add("Klinik Kosasih Urip");
        title.add("Klinik Rawat Inap Kosasih Rajabasa");

        btn_pick = findViewById(R.id.btn_pick);
        fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG);

        //initialize Places.
        Places.initialize(getApplicationContext(), "AIzaSyBBFq_CyLIZJTOodJlcsUycDL-EoN8IME8");

        //create a new Places client instance.
        PlacesClient placesClient = Places.createClient( this);

        btn_pick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN,fields).build(MapsActivity.this);
                startActivityForResult(intent,place_piker_reg_code);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case place_piker_reg_code:
                Place place = Autocomplete.getPlaceFromIntent(data);
                name = place.getName();
                latLng = place.getLatLng();
                mMap.addMarker(new MarkerOptions().position(latLng).title("name"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                CameraUpdate update = CameraUpdateFactory.newLatLngZoom(latLng, -5);
                mMap.animateCamera(update);
                break;
        }
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
        // add markers to location
        for (int i=0; i<arrayList.size();i++){
            //loop is for adding markers
            for (int j=0; j<title.size();j++){
                //setting title of marker
                mMap.addMarker(new MarkerOptions().position(arrayList.get(j)).title(String.valueOf(title.get(j))));
            }
            mMap.moveCamera(CameraUpdateFactory.newLatLng(arrayList.get(i)));
        }

        // click listner for marker on maps
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                String markertitle=marker.getTitle();

                Intent i= new Intent(MapsActivity.this,DetailsActivity.class);
                //passing title to new activity detailsactivity
                i.putExtra("title",markertitle);
                startActivity(i);
                return false;
            }
        });
}
}
