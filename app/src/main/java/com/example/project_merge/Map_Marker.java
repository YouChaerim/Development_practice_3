package com.example.project_merge;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.CameraUpdateParams;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.NaverMapSdk;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.UiSettings;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.PathOverlay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Map_Marker extends AppCompatActivity implements OnMapReadyCallback{

    private MapView mapView;
    private static final String CLIENT_ID = "bgn4v95lbl";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_marker);

        ImageButton button_back = findViewById(R.id.button_back);

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Map_Marker.this, DetailPage.class);
                startActivity(intent);
            }
        });

        // 네이버 클라우드 플랫폼 클라이언트 ID 설정
        NaverMapSdk.getInstance(this).setClient(new NaverMapSdk.NaverCloudPlatformClient(CLIENT_ID));


        // MapView 초기화
        mapView = findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);

        // 지도가 준비되면 콜백 호출
        mapView.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        // 지도 초기 설정
        LatLng initialPosition = new LatLng(37.1274545917684, 127.488801969756);
        CameraUpdateParams params = new CameraUpdateParams();
        params.scrollTo(initialPosition);
        params.zoomTo(15);
        CameraUpdate cameraUpdate = CameraUpdate.withParams(params);
        naverMap.moveCamera(cameraUpdate);

        // 지도 UI 설정
        UiSettings uiSettings = naverMap.getUiSettings();
        uiSettings.setZoomControlEnabled(true); // 줌 컨트롤 표시

        naverMap.setMinZoom(7.0); // 최소 줌 레벨 설정

        LatLng markerPosition = new LatLng(37.12534249298, 127.492461511984);
        Marker marker = new Marker();
        marker.setPosition(markerPosition);
        marker.setMap(naverMap);

        double[] latitudes = {
                37.126376, 37.126356, 37.126499, 37.126644, 37.126757,
                37.126793, 37.126691, 37.126331, 37.125625, 37.125629,
                37.125777, 37.125729, 37.125602, 37.125123, 37.125079,
                37.125214
        };

        double[] longitudes = {
                127.487225, 127.487454, 127.487495, 127.487773, 127.488129,
                127.488685, 127.489324, 127.490368, 127.491092, 127.491248,
                127.491622, 127.491822, 127.491900, 127.492139, 127.492365,
                127.492544
        };

        PathOverlay path = new PathOverlay();
        List<LatLng> coords = new ArrayList<>();

        for (int i = 0; i < latitudes.length; i++) {
            coords.add(new LatLng(latitudes[i], longitudes[i]));
        }

// Add the last position (markerPosition)
        coords.add(markerPosition);

// Set the coordinates on the PathOverlay object
        path.setCoords(coords);

        path.setMap(naverMap);
        path.setColor(Color.GREEN);
    }

    // MapView 생명주기 메서드
    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
