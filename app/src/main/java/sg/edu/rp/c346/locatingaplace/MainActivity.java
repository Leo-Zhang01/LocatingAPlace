package sg.edu.rp.c346.locatingaplace;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btn1, btn2, btn3;
    private GoogleMap map;
    Marker north, east, central;
    ArrayList<String> list;
    ArrayAdapter adapter;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        list = new ArrayList<>();
        list.add("North");
        list.add("Central");
        list.add("East");
        spinner = findViewById(R.id.spinner);
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, list);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    btn1.performClick();
                }else if(position == 1){
                    btn2.performClick();
                }else{
                    btn3.performClick();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)
                fm.findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback(){
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
            }
        });
        mapFragment.getMapAsync(new OnMapReadyCallback(){
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
                UiSettings ui = map.getUiSettings();
                ui.setCompassEnabled(true);
                ui.setZoomControlsEnabled(true);

                LatLng poi_CausewayPoint = new LatLng(1.436065, 103.786263);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_CausewayPoint,
                        15));
                LatLng poi_north = new LatLng(1.315157, 103.767432);
                north = map.addMarker(new
                        MarkerOptions()
                        .position(poi_north)
                        .title("North - HQ")
                        .snippet("Block 333, Admiralty Ave 3, 765654 Operating hours: 10am-5pm " +
                                "Tel:65433456")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher)));
                LatLng poi_central = new LatLng(1.352083, 103.819836);
                central = map.addMarker(new
                        MarkerOptions()
                        .position(poi_central)
                        .title("Central")
                        .snippet("Block 3A, Orchard Ave 3, 134542 " +
                                "Operating hours: 11am-8pm " +
                                "Tel:67788652")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                LatLng poi_east = new LatLng(1.332920, 103.799601);
                east = map.addMarker(new
                        MarkerOptions()
                        .position(poi_east)
                        .title("East")
                        .snippet("Block 555, Tampines Ave 3, 287788"+
                                "Operating hours: 9am-5pm"+
                                "Tel:66776677")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));


            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LatLng poi_north = new LatLng(1.315157, 103.767432);
                north.setIcon(BitmapDescriptorFactory.fromResource(android.R.drawable.star_on));
                east.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                central.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                if (map != null){
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_north,
                            13));
                }
                Toast.makeText(MainActivity.this,"North",Toast.LENGTH_SHORT).show();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LatLng poi_central = new LatLng(1.352083, 103.819836);
                north.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                east.setIcon(BitmapDescriptorFactory.fromResource(android.R.drawable.star_on));
                central.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                if (map != null){
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_central,
                            13));
                }
                Toast.makeText(MainActivity.this,"Central",Toast.LENGTH_SHORT).show();

            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LatLng poi_east = new LatLng(1.332920, 103.799601);
                north.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                east.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                central.setIcon(BitmapDescriptorFactory.fromResource(android.R.drawable.star_on));
                if (map != null){
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_east,
                            13));
                }
                Toast.makeText(MainActivity.this,"East",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
