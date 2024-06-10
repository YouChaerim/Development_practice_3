package com.example.project_merge;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Announcement extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.announcement);

        ImageButton button_back = findViewById(R.id.button_back);
        ImageButton Announcements = findViewById(R.id.Announcements);
        ImageButton Announcements2 = findViewById(R.id.Announcements2);
        ImageButton Announcements3 = findViewById(R.id.Announcements3);
        ImageButton Announcements4 = findViewById(R.id.Announcements4);
        ImageButton Announcements5 = findViewById(R.id.Announcements5);
        ImageButton Announcements6 = findViewById(R.id.Announcements6);
        ImageButton Announcements7 = findViewById(R.id.Announcements7);
        ImageButton Announcements8 = findViewById(R.id.Announcements8);

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Announcement.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Announcements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Announcement.this, notice1.class);
                startActivity(intent);
            }
        });

        Announcements2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Announcement.this, notice2.class);
                startActivity(intent);
            }
        });

        Announcements3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Announcement.this, notice4.class);
                startActivity(intent);
            }
        });

        Announcements4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Announcement.this, notice5.class);
                startActivity(intent);
            }
        });

        Announcements5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Announcement.this, notice6.class);
                startActivity(intent);
            }
        });

        Announcements6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Announcement.this, notice6.class);
                startActivity(intent);
            }
        });

        Announcements7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Announcement.this, notice8.class);
                startActivity(intent);
            }
        });

        Announcements8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Announcement.this, notice7.class);
                startActivity(intent);
            }
        });
    }
}
