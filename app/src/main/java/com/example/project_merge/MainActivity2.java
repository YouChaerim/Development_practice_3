package com.example.project_merge;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class MainActivity2 extends AppCompatActivity {

    ViewPager mViewPager;
    int[] images = {R.drawable.banner001, R.drawable.banner002, R.drawable.banner003};
    ViewPagerAdapter mViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mViewPager = (ViewPager)findViewById(R.id.viewPagerMain);
        mViewPagerAdapter = new ViewPagerAdapter(MainActivity2.this, images);
        mViewPager.setAdapter(mViewPagerAdapter);

        ImageButton button_home = findViewById(R.id.button_home);
        button_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });

        ImageButton imageBtn1 = findViewById(R.id.imageBtn1);
        imageBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, Announcement.class);
                startActivity(intent);
            }
        });
        // 인텐트에서 사용자 이름 받아오기

        int memberId = getIntent().getIntExtra("memberId", -1);
        if (memberId != -1){
            ImageButton dayImage = findViewById(R.id.dayImage);
            dayImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity2.this, DetailPage.class);
                    intent.putExtra("memberId", memberId);
                    startActivity(intent);
                }
            });
        } else {
            Toast.makeText(this, "Invalid Member ID", Toast.LENGTH_SHORT).show();
        }
    }
}
