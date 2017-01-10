package com.androidnh.nh;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static final String TAG = "MainActivity";
    private BroadcastReceiver mRegistrationBroadcastReceiver;

    String token;
    String a,b,c,d,e,f,g;
    public void getInstanceIdToken() {
        if (checkPlayServices()) {
            // Start IntentService to register this application with GCM.
            Intent intent = new Intent(this, RegistrationIntentService.class);
            startService(intent);
        }
    }

    public void registBroadcastReceiver(){
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                //ProgressDialog mProgressDialog = new ProgressDialog(MainActivity.this);
                if(action.equals(QuickstartPreferences.REGISTRATION_READY)){
                    // 액션이 READY일 경우
                } else if(action.equals(QuickstartPreferences.REGISTRATION_GENERATING)){
                    // 액션이 GENERATING일 경우
                    //mProgressDialog = ProgressDialog.show(MainActivity.this,"",
                    //      "잠시만 기다려 주세요.",true);
                } else if(action.equals(QuickstartPreferences.REGISTRATION_COMPLETE)){
                    // 액션이 COMPLETE일 경우
//                    mRegistrationProgressBar.setVisibility(ProgressBar.GONE);
                    // mProgressDialog.dismiss();
                    token = intent.getStringExtra("token");
                    Member member = new Member();
                    member.setMemberId(a);
                    member.setEmail(b);
                    member.setName(c);
                    member.setPasswd(d);
                    member.setLocation(e);
                    member.setPhone(f);
                    member.setGender(g);
                    member.setInstanceId(token);
                    Gson gson = new Gson();
                    final String json = gson.toJson(member);

                    (new Thread() {
                        public void run() {
                            try {
                                URL url =
                                        new URL("http://192.168.13.27:8088/nhserver/nhmember/register.action");
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
                            } catch (Exception ex) {throw new RuntimeException(ex);}
                        }
                    }).start();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "회원가입이 완료 되었습니다.", Toast.LENGTH_SHORT);
                        }
                    });
                    finish();
                }
            }
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText memberId = (EditText)findViewById(R.id.editText1);
        final EditText name = (EditText)findViewById(R.id.editText2);
        final EditText passwd = (EditText)findViewById(R.id.editText3);
        EditText passwd2 = (EditText)findViewById(R.id.editText4);
        final EditText email = (EditText)findViewById(R.id.editText5);
        final EditText location = (EditText)findViewById(R.id.editText6);
        final EditText phone = (EditText)findViewById(R.id.editText7);
        final RadioGroup gGender = (RadioGroup)findViewById(R.id.gender);

        Button submit = (Button)findViewById(R.id.button3);
        registBroadcastReceiver();

        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                a = memberId.getText().toString();
                b = email.getText().toString();
                c = name.getText().toString();
                d = passwd.getText().toString();
                e = location.getText().toString();
                f = phone.getText().toString();
                int id = gGender.getCheckedRadioButtonId();
                RadioButton gender = (RadioButton)findViewById(id);
                g = gender.getText().toString();
                getInstanceIdToken();
            }
        });
    }
    /**
     * 앱이 실행되어 화면에 나타날때 LocalBoardcastManager에 액션을 정의하여 등록한다.
     */
    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(QuickstartPreferences.REGISTRATION_READY));
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(QuickstartPreferences.REGISTRATION_GENERATING));
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(QuickstartPreferences.REGISTRATION_COMPLETE));
    }
    /**
     * 앱이 화면에서 사라지면 등록된 LocalBoardcast를 모두 삭제한다.
     */
    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }
    /**
     * Google Play Service를 사용할 수 있는 환경이지를 체크한다.
     */
    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.i(TAG, "This device is not supported.");
                finish();
            }
            return false;
        }
        return true;
    }
}
