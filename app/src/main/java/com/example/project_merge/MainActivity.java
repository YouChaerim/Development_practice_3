package com.example.project_merge;

import static android.app.PendingIntent.getActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    // creating object of ViewPager
    ViewPager mViewPager;
    SQLiteHelper sqLiteHelper;

    // images array
    int[] images = {R.drawable.banner001, R.drawable.banner002, R.drawable.banner003};

    // Creating Object of ViewPagerAdapter
    ViewPagerAdapter mViewPagerAdapter;
    private static final String TAG = "MainActivity";
    private View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sqLiteHelper = new SQLiteHelper(this);
        ArrayList<String> nameList = sqLiteHelper.getMemberNames();

        // Initializing the ViewPager Object
        mViewPager = (ViewPager)findViewById(R.id.viewPagerMain);

        // Initializing the ViewPagerAdapter
        mViewPagerAdapter = new ViewPagerAdapter(MainActivity.this, images);

        // Adding the Adapter to the ViewPager
        mViewPager.setAdapter(mViewPagerAdapter);

        ImageButton imageBtn1 = findViewById(R.id.imageBtn1);

        imageBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Announcement.class);
                startActivity(intent);
            }
        });


        Interface apiService = RetrofitClient.getClient("https://apis.data.go.kr/").create(Interface.class);

        Call<Response> call = apiService.getData("이강훈", 1, 50, "x/6LtYyI17RshlR6eaQ9X1ZFuQYqEzuUTHg9Z5AkIs12bI+QHlJxTw0/sX4Wp6K7LQ4Kg059Yl/UZM6dpQzI3w==");

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.isSuccessful()) {
                    Response data = response.body();


                    Log.d("here", data.getHeader().getResultMsg());
                    Log.d("!!!", data.getBody().get(0).getName());
                    // 여기서 데이터 처리
                } else {
                    // 에러 처리
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.d("herehere", t.toString());
                // 네트워크 에러 등 실패 처리
            }
        });

    }

}