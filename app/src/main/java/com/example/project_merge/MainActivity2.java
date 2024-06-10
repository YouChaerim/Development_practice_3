package com.example.project_merge;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class MainActivity2 extends AppCompatActivity {

    ViewPager mViewPager;
    int[] images = {R.drawable.banner001, R.drawable.banner002, R.drawable.banner003};
    ViewPagerAdapter mViewPagerAdapter;

    private SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        sqLiteHelper = new SQLiteHelper(this);

        //슬라이더
        mViewPager = (ViewPager)findViewById(R.id.viewPagerMain);
        mViewPagerAdapter = new ViewPagerAdapter(MainActivity2.this, images);
        mViewPager.setAdapter(mViewPagerAdapter);

        // 배너
        ImageButton button_home = findViewById(R.id.button_home);
        button_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //공지사항
        ImageButton imageBtn1 = findViewById(R.id.imageBtn1);
        imageBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this, Announcement.class);
                startActivity(intent);
            }
        });

        sqLiteHelper = new SQLiteHelper(this);

        int id = getIntent().getIntExtra("id", -1);
        if (id != -1){
            SQLiteHelper.MemberDetails memberDetails = sqLiteHelper.getMemberDetailsById(id);
            ImageButton dayImage = findViewById(R.id.dayImage);
            dayImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity2.this, DetailPage.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                }
            });
            if (memberDetails != null) {
                String name = memberDetails.getName();
                String birthDayStr = memberDetails.getBirthDay().toString();
                String burrDayStr = memberDetails.getBurrDay().toString();
                Integer burrDay = memberDetails.getBurrDay();

                LocalDate birthDate = LocalDate.of(
                        Integer.parseInt(birthDayStr.substring(0, 4)),
                        Integer.parseInt(birthDayStr.substring(4, 6)),
                        Integer.parseInt(birthDayStr.substring(6, 8))
                );

                LocalDate burrDate = LocalDate.of(
                        Integer.parseInt(burrDayStr.substring(0, 4)),
                        Integer.parseInt(burrDayStr.substring(4, 6)),
                        Integer.parseInt(burrDayStr.substring(6, 8))
                );

                // burrDay에 600000을 더한 후의 날짜를 계산
                LocalDate targetDate = burrDate.plusDays(600000);
                LocalDate today = LocalDate.now();
                long dday = ChronoUnit.DAYS.between(today, targetDate);


                TextView expirationTextView = findViewById(R.id.expiration);
                String expirationText = name + "님 만기일";
                expirationTextView.setText(expirationText);

                TextView birthdayTextView = findViewById(R.id.birthday);
                String birthdayText = birthDayStr.substring(0, 4) + "년 " + birthDayStr.substring(4, 6) + "월 " + birthDayStr.substring(6, 8) + "일";
                birthdayTextView.setText(birthdayText);

                TextView nameoffTextView = findViewById(R.id.nameoff);
                String nameoffText = "故 " + name + "님";
                nameoffTextView.setText(nameoffText);

                TextView DdayTextView = findViewById(R.id.Dday);
                String DdayText = "D-" + dday;
                DdayTextView.setText(DdayText);

            }
        } else {
            Toast.makeText(this, "Invalid Member ID", Toast.LENGTH_SHORT).show();
        }
    }
}
