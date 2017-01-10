package com.androidnh.nh;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;

/**
 * Created by stu27 on 2016-07-18.
 */
public class ExistActivity extends AppCompatActivity {

//    Button logoutBtn;
//    TextView eText;

    // **
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    // **

    // **

    // **


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exist);

        // **
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        // **

        // **

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



//        logoutBtn = (Button)findViewById(R.id.logoutBtn);
        // eText = (TextView)findViewById(R.id.existid);

        SharedPreferences loginuser = getSharedPreferences("loginuser", MODE_PRIVATE);

        // eText.setText(loginuser.getString("memberId",""));
//        logoutBtn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                SharedPreferences loginuser = getSharedPreferences("loginuser", MODE_PRIVATE);
//                SharedPreferences.Editor editor = loginuser.edit();
//                editor.remove("memberId");
//                editor.commit();
//
//                Intent intent = new Intent(ExistActivity.this, LoginActivity.class);
//                startActivity(intent);
//                // 뒤로가기 했을경우 안나오도록 없애주기 >> finish!!
//                finish();
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.menu_logout:
                SharedPreferences loginuser = getSharedPreferences("loginuser", MODE_PRIVATE);
                SharedPreferences.Editor editor = loginuser.edit();
                editor.remove("memberId");
                editor.commit();

                Intent intent = new Intent(ExistActivity.this, LoginActivity.class);
                startActivity(intent);
                // 뒤로가기 했을경우 안나오도록 없애주기 >> finish!!
                finish();
                Toast.makeText(this, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
                break;
        }
//        if (id == R.id.action_settings) {
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }

    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {

        }

        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            // textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) { super(fm); }

        @Override
        public Fragment getItem(int position) {

            Fragment fragment = null;
            Bundle args = null;
            switch (position) {
                case 0:
                    fragment = new SelectionsFragment1();
                    args = new Bundle();
                    break;
                case 1:
                    fragment = new SelectionsFragment2();
                    args = new Bundle();
                    break;
                case 2:
                    fragment = new SelectionsFragment3();
                    args = new Bundle();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }

    public static class SelectionsFragment1 extends Fragment implements SensorEventListener {
        public SelectionsFragment1() {
        }

        public static int cnt = 0;

        private TextView tView;
        private Button resetBtn;

        private long lastTime;
        private float speed;
        private float lastX;
        private float lastY;
        private float lastZ;
        private float x, y, z;

        private static final int SHAKE_THRESHOLD = 800;
        private static final int DATA_X = SensorManager.DATA_X;
        private static final int DATA_Y = SensorManager.DATA_Y;
        private static final int DATA_Z = SensorManager.DATA_Z;

        private SensorManager mSensorManager;
        private Sensor accelerormeterSensor;

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mSensorManager = (SensorManager) this.getActivity().getSystemService(Activity.SENSOR_SERVICE);
            accelerormeterSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_tab1, container, false);

            tView = (TextView) view.findViewById(R.id.cntView);
            resetBtn = (Button) view.findViewById(R.id.resetBtn);

            tView.setText("" + cnt);

            resetBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (view.getId()) {
                        case R.id.resetBtn :
                            long now = System.currentTimeMillis();
                            Timestamp date = new Timestamp(now);

                            Manbo manbo = new Manbo();
                            manbo.setCount(cnt);
                            manbo.setRegDate(date);
                            Gson gson = new Gson();
                            final String json = gson.toJson(manbo);
                            System.out.println(cnt);
                            System.out.println(date);
                            System.out.println(json);

                            (new Thread() {
                                public void run() {
                                    try {
                                        URL url =
                                                new URL("http://192.168.13.27:8088/nhserver/nhmanbo/register.action");
                                        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                                        conn.setRequestMethod("POST");
                                        conn.setDoOutput(true);//전송 메시지 본문에 데이터 쓰기 설정
                                        conn.setDoInput(true);//수신 메시지 읽기 설정
                                        conn.setRequestProperty(
                                                "Content-Type", "application/json");
                                        //전송 데이터 쓰기
                                        OutputStream ostream = conn.getOutputStream();
                                        ostream.write(json.getBytes("utf-8"));//한글일 경우 euc-kr
                                        ostream.flush();//미전송 데이터 강제 전송
                                        ostream.close();
                                        int code = conn.getResponseCode();
//                                      finish();
//                                      context.getActivity.finish();
//                                      getActivity().finish();
                                    } catch (Exception ex) {
                                        throw new RuntimeException(ex);
                                    }
                                }
                            }).start();

                            cnt = 0;
                            tView.setText("" + cnt);
                            break;
                    }
                }
            });

            return view;
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                long currentTime = System.currentTimeMillis();
                long gabOfTime = (currentTime - lastTime);
                if (gabOfTime > 100) {
                    lastTime = currentTime;
                    x = event.values[SensorManager.DATA_X];
                    y = event.values[SensorManager.DATA_Y];
                    z = event.values[SensorManager.DATA_Z];

                    speed = Math.abs(x + y + z - lastX - lastY - lastZ) / gabOfTime * 10000;

                    if (speed > SHAKE_THRESHOLD) {
                        tView.setText("" + (++cnt));
                    }

                    lastX = event.values[DATA_X];
                    lastY = event.values[DATA_Y];
                    lastZ = event.values[DATA_Z];
                }

            }

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

        @Override
        public void onStart() {
            super.onStart();
            if (accelerormeterSensor != null)
                mSensorManager.registerListener(this, accelerormeterSensor,
                        SensorManager.SENSOR_DELAY_GAME);
        }

        @Override
        public void onStop() {
            super.onStop();
            if (mSensorManager != null)
                mSensorManager.unregisterListener(this);
        }

//        public void mOnClick(View v) {
//            switch (v.getId()) {
//                case R.id.resetBtn :
//                    cnt = 0;
//                    tView.setText("" + cnt);
//                    break;
//            }
//        }

    }

    public static class SelectionsFragment2 extends Fragment {
        public SelectionsFragment2() {
        }
        private GoogleMap googleMap;
        private LocationManager manager;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_tab2, container, false);

            SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map);
            googleMap = mapFragment.getMap();

            manager = (LocationManager) this.getActivity().getSystemService(Context.LOCATION_SERVICE);

            // 위치 확인하여 위치 표시 시작
            startLocationService();

            checkDangerousPermissions();


            return view;
        }

        private void checkDangerousPermissions() {
            String[] permissions = {
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
            };

            int permissionCheck = PackageManager.PERMISSION_GRANTED;
            for (int i = 0; i < permissions.length; i++) {
                permissionCheck = ContextCompat.checkSelfPermission(getActivity(), permissions[i]);
                if (permissionCheck == PackageManager.PERMISSION_DENIED) {
                    break;
                }
            }

            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
//                Toast.makeText(this, "권한 있음", Toast.LENGTH_LONG).show();
            } else {
//                Toast.makeText(this, "권한 없음", Toast.LENGTH_LONG).show();

                if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), permissions[0])) {
//                    Toast.makeText(this, "권한 설명 필요함.", Toast.LENGTH_LONG).show();
                } else {
                    ActivityCompat.requestPermissions(getActivity(), permissions, 1);
                }
            }
        }
        @Override
        public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
            if (requestCode == 1) {
                for (int i = 0; i < permissions.length; i++) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
//                        Toast.makeText(this, permissions[i] + " 권한이 승인됨.", Toast.LENGTH_LONG).show();
                    } else {
//                        Toast.makeText(this, permissions[i] + " 권한이 승인되지 않음.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
        /**
         * 현재 위치 확인을 위해 정의한 메소드
         */
        private void startLocationService() {
//            // 위치 관리자 객체 참조
//            LocationManager manager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

            // 리스너 객체 생성
            GPSListener gpsListener = new GPSListener();
            long minTime = 10000;
            float minDistance = 0;

            try {
                // GPS 기반 위치 요청
                manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,minTime,minDistance,gpsListener);

                // 네트워크 기반 위치 요청
                manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,minTime,minDistance,gpsListener);
            } catch(SecurityException ex) {
                ex.printStackTrace();
            }

//            Toast.makeText(getApplicationContext(), "위치 확인 시작함. 로그를 확인하세요.", Toast.LENGTH_SHORT).show();
        }

        /**
         * 리스너 정의
         */
        private class GPSListener implements LocationListener {
            /**
             * 위치 정보가 확인되었을 때 호출되는 메소드
             */
            public void onLocationChanged(Location location) {
                Double latitude = location.getLatitude();
                Double longitude = location.getLongitude();

                String msg = "Latitude : "+ latitude + "\nLongitude:"+ longitude;
                Log.i("GPSLocationService", msg);

                // 현재 위치의 지도를 보여주기 위해 정의한 메소드 호출
                showCurrentLocation(latitude, longitude);
            }

            public void onProviderDisabled(String provider) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

        }

        /**
         * 현재 위치의 지도를 보여주기 위해 정의한 메소드
         *
         * @param latitude
         * @param longitude
         */
        private void showCurrentLocation(Double latitude, Double longitude) {
            // 현재 위치를 이용해 LatLon 객체 생성
            LatLng curPoint = new LatLng(latitude, longitude);

            System.out.println(curPoint);

            nhLocation loc = new nhLocation();
            loc.setLatitude(latitude);
            loc.setLongitude(longitude);

//                                        URL url = new URL("http://192.168.13.27:8088/nhserver/nhLocation/register.action");
//                                        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//                                        conn.setRequestMethod("POST");
//                                        conn.setDoOutput(true);//전송 메시지 본문에 데이터 쓰기 설정
//                                        conn.setDoInput(true);//수신 메시지 읽기 설정
//                                        conn.setRequestProperty(
//                                                "Content-Type", "application/json");
//                                        //전송 데이터 쓰기
//                                        OutputStream ostream = conn.getOutputStream();
//                                        ostream.write(json.getBytes("utf-8"));//한글일 경우 euc-kr
//                                        ostream.flush();//미전송 데이터 강제 전송
//                                        ostream.close();
//                                        int code = conn.getResponseCode();
////                                      finish();
////                                      context.getActivity.finish();
////                                      getActivity().finish();
//                                    } catch (Exception ex) {
//                                        throw new RuntimeException(ex);
//                                    }
//                                }
//                            }).start();
//
//                            cnt = 0;
//                            tView.setText("" + cnt);
//                            break;
//                    }
//                }
//            });
//            // 현재 위치를 지도의 중심으로 표시
//            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint, 15));
//
//            // 지도 유형 설정.
//            // 지형도인 경우에는 GoogleMap.MAP_TYPE_TERRAIN, 위성 지도인 경우에는 GoogleMap.MAP_TYPE_SATELLITE
//            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//
        }

        @Override
        public void onResume() {
            super.onResume();

            try {
                // 내 위치 자동 표시 enable
                googleMap.setMyLocationEnabled(true);
            } catch(SecurityException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onPause() {
            super.onPause();

            try {
                // 내 위치 자동 표시 disable
                googleMap.setMyLocationEnabled(false);
            } catch(SecurityException e) {
                e.printStackTrace();
            }
        }
    }

    public static class SelectionsFragment3 extends Fragment {
        public SelectionsFragment3() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_tab3, container, false);
            return view;
        }
    }

}
