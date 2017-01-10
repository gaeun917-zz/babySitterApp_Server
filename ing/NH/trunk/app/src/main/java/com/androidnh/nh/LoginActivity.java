package com.androidnh.nh;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by stu27 on 2016-07-18.
 */
public class LoginActivity extends AppCompatActivity {

    Button loginBtn;
    Button registerBtn;
    EditText getID;
    EditText getPasswd;
    String failed = "로그인 실패";
    private Context context;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = this;
        loginBtn = (Button)findViewById(R.id.btnLogin);
        getID = (EditText)findViewById(R.id.memberid);
        getPasswd = (EditText)findViewById(R.id.memberpassword);
        registerBtn = (Button)findViewById(R.id.btnLinkToRegisterScreen);
        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
        Intent intent = getIntent();
        final String token = intent.getStringExtra("token");
                (new Thread() {
                    public void run() {
                        try {
                            String qString = "memberId="+getID.getText().toString()+"&passwd="+getPasswd.getText().toString();
                            URL url =
                                    new URL("http://192.168.13.27:8088/nhserver/nhmember/login.action?" + qString);
                            HttpURLConnection conn = (HttpURLConnection)url.openConnection();

                            InputStream istream = conn.getInputStream();
                            InputStreamReader r = new InputStreamReader(istream);
                            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

                            final Member member = gson.fromJson(r, Member.class);//JSON 문자열로부터 객체 생성
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if(member == null || member.getName() == null){
                                        Toast.makeText(context, failed, Toast.LENGTH_SHORT).show();
                                    }else {
                                        Toast.makeText(getApplicationContext(), member.getName() + "님 반갑습니다.", Toast.LENGTH_SHORT).show();
                                        Gson gson = new Gson();
                                        final String json = gson.toJson(token);
                                        (new Thread() {
                                            public void run() {
                                                try {
                                                    URL url =
                                                            new URL("http://192.168.13.27:8088/nhserver/nhmember/update.action");
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
                                                    conn.getResponseCode();

                                                } catch (Exception ex) {throw new RuntimeException(ex);}
                                            }
                                        }).start();

                                        SharedPreferences loginuser = getSharedPreferences("loginuser", MODE_PRIVATE);
                                        SharedPreferences.Editor editor = loginuser.edit();
                                        editor.putString("memberId", member.getMemberId());
                                        editor.putString("passwd", member.getPasswd());
                                        editor.putString("name", member.getName());
                                        editor.commit();
                                        Intent intent = new Intent(LoginActivity.this, ExistActivity.class);
                                        startActivity(intent);
                                        // 뒤로가기 했을경우 안나오도록 없애주기 >> finish!!
                                        finish();
                                    }
                                }
                            });
                        } catch (Exception ex) {
                            //throw new RuntimeException(ex);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(context, failed, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }).start();
            }
        });
        registerBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                //intent.putExtra(“text”,String.valueOf(editText.getText()));
                startActivity(intent);
            }
        });
    }
}
