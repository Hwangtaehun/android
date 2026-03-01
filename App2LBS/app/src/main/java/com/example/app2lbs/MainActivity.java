package com.example.app2lbs;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.splashscreen.SplashScreen;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

import com.example.app2lbs.databinding.ActivityMainBinding;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;

    // 구글 맵 객체
    GoogleMap mainGoogleMap;

    // 확인할 권한 목록
    String [] permissionList = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
    };

    // 위치 정보 관리 객체
    LocationManager locationManager;

    // 위치 측정을 하면 반응하는 리스너
    MyLocationListener myLocationListener;

    // 사용자 위치를 표시하는 마커 객체
    Marker myMarker;

    String [] dialogData = {
            "accounting", "airport", "amusement_park",
            "aquarium", "art_gallery", "atm", "bakery",
            "bank", "bar", "beauty_salon", "bicycle_store",
            "book_store", "bowling_alley", "bus_station",
            "cafe", "campground", "car_dealer", "car_rental",
            "car_repair", "car_wash", "casino", "cemetery",
            "church", "city_hall", "clothing_store", "convenience_store",
            "courthouse", "dentist", "department_store", "doctor",
            "drugstore", "electrician", "electronics_store", "embassy",
            "fire_station", "florist", "funeral_home", "furniture_store",
            "gas_station", "gym", "hair_care", "hardware_store", "hindu_temple",
            "home_goods_store", "hospital", "insurance_agency",
            "jewelry_store", "laundry", "lawyer", "library", "light_rail_station",
            "liquor_store", "local_government_office", "locksmith", "lodging",
            "meal_delivery", "meal_takeaway", "mosque", "movie_rental", "movie_theater",
            "moving_company", "museum", "night_club", "painter", "park", "parking",
            "pet_store", "pharmacy", "physiotherapist", "plumber", "police", "post_office",
            "primary_school", "real_estate_agency", "restaurant", "roofing_contractor",
            "rv_park", "school", "secondary_school", "shoe_store", "shopping_mall",
            "spa", "stadium", "storage", "store", "subway_station", "supermarket",
            "synagogue", "taxi_stand", "tourist_attraction", "train_station",
            "transit_station", "travel_agency", "university", "eterinary_care","zoo"
    };

    // 현재 위치
    Location myLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        SplashScreen.installSplashScreen(this);

        // SystemClock.sleep(5000);

        MapsInitializer.initialize(this, MapsInitializer.Renderer.LATEST, null);

        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        setSupportActionBar(activityMainBinding.mainToolbar);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 권한을 확인한다.
        requestPermissions(permissionList, 0);

        // 구글맵 사용 준비가 완료되면 반응하는 콜백을 등록한다.
        GoogleMapListener googleMapListener = new GoogleMapListener();
        FragmentManager fragmentManager = getSupportFragmentManager();
        SupportMapFragment supportMapFragment = (SupportMapFragment) fragmentManager.findFragmentById(R.id.map_fragment);
        supportMapFragment.getMapAsync(googleMapListener);
    }

    // 구글 지도가 준비 완료되면 반응하는 리스너
    class GoogleMapListener implements OnMapReadyCallback{

        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {
            mainGoogleMap = googleMap;

            // 위치 정보를 관리하는 객체를 가지고 온다.
            locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

            // 새로운 위치가 측정되면 반응할 리스너
            myLocationListener = new MyLocationListener();

            // 단말기에 저장되어 있는 위치 정보를 가져온다.
            boolean a1 = ActivityCompat.checkSelfPermission(
                    MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
            boolean a2 = ActivityCompat.checkSelfPermission(
                    MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;

            if(a1 && a2) {
                // 저장되어 있는 위치값을 가져온다.
                Location location1 = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                Location location2 = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                // 현재 위치를 지도에 표시한다.
                if(location1 != null) {
                    setMyLocation(location1);
                } else if(location2 != null){
                    setMyLocation(location2);
                }

                // 구글 지도 화면 설정을 할 수 있는 객체를 추출한다.
                UiSettings uiSettings = mainGoogleMap.getUiSettings();

                // 확대 축소 버튼
                uiSettings.setZoomControlsEnabled(true);

                // 현재 위치를 표시한다.
                //mainGoogleMap.setMyLocationEnabled(true);

                // 현재 위치 아이콘을 제거한다.
                //uiSettings.setMyLocationButtonEnabled(false);

                // 맵 타입
                // mainGoogleMap.setMapType(GoogleMap.MAP_TYPE_NONE);
                // mainGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                // mainGoogleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                // mainGoogleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                // mainGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

                // 현재 위치를 측정한다.
                getMyLocation();
            }
        }
    }

    // 현재 위치를 측정하는 메서드
    public void getMyLocation(){
        // 권한 확인
        boolean a1 = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_DENIED;
        boolean a2 = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_DENIED;

        if(a1 || a2) {
            return;
        }

        // GPS 프로바이더가 사용이 가능하다면
        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) == true) {
            locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 0, 0,
                    myLocationListener);
        } else if(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) == true){ // 네트워크 프로바이더 사용이 가능하다면
            locationManager.requestLocationUpdates(locationManager.NETWORK_PROVIDER, 0, 0,
                    myLocationListener);
        }
    }

    // 위치 값을 받아 지도를 이동시킨다.
    public void setMyLocation(Location location){
        // 현재 위치 측정을 중단한다.
        locationManager.removeUpdates(myLocationListener);

        // 현재 위치를 담아둔다.
        myLocation = location;

        // 위도와 경도를 가지고 온다.
        double lat = location.getLatitude();
        double lng = location.getLongitude();

        // Log.d("map app", lat + ", " + lng);

        // 위도와 경도를 관리하는 객체를 생성한다.
        LatLng loc1 = new LatLng(lat, lng);

        // 지도를 이동시키기 위한 객체를 생성한다.
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(loc1, 15f);

        // 카메라를 이동시킨다.
        mainGoogleMap.animateCamera(cameraUpdate);

        // 현재 위치로 마커를 표시한다.
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(loc1);

        // 표시되어 있는 마커가 있다면 제거한다.
        if(myMarker != null){
            myMarker.remove();
            myMarker = null;
        }

        // 마커의 이미지를 변경한다.
        BitmapDescriptor markerBitmap = BitmapDescriptorFactory.fromResource(R.drawable.my_location);
        markerOptions.icon(markerBitmap);

        myMarker = mainGoogleMap.addMarker(markerOptions);

        // 마커가 보이지 않도록 설정한다.
        // myMarker.setVisible(false);
    }

    // 위치측정이 성공하면 반응하는 리스너
    class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(@NonNull Location location) {
            setMyLocation(location);
        }
    }

    // 메뉴를 띄운다.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    // 메뉴를 누르면 동작하는 메서드
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if(itemId == R.id.main_menu_location){
            getMyLocation();
        } else if(itemId == R.id.main_menu_place) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("장소 종류 선택");
            builder.setNegativeButton("취소", null);
            builder.setNeutralButton("초기화", null);
            builder.setItems(dialogData, null);
            builder.show();
        }

        return super.onOptionsItemSelected(item);
    }
}