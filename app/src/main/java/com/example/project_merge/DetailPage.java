package com.example.project_merge;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.Period;

public class DetailPage extends AppCompatActivity{

    private SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailpage);

        sqLiteHelper = new SQLiteHelper(this);

        int memberId = getIntent().getIntExtra("memberId", -1);

        if (memberId != -1) {
            SQLiteHelper.MemberDetails memberDetails = sqLiteHelper.getMemberDetailsById(memberId);
            if (memberDetails != null) {
                String name = memberDetails.getName();
                String pname1 = memberDetails.getPname1();
                String pname2 = memberDetails.getPname2();
                String qualification = memberDetails.getQualification();

                String birthDayStr = memberDetails.getBirthDay().toString();
                String deathDayStr = memberDetails.getDeathDay().toString();

                LocalDate birthDate = LocalDate.of(
                        Integer.parseInt(birthDayStr.substring(0, 4)),
                        Integer.parseInt(birthDayStr.substring(4, 6)),
                        Integer.parseInt(birthDayStr.substring(6, 8))
                );

                LocalDate deathDate = LocalDate.of(
                        Integer.parseInt(deathDayStr.substring(0, 4)),
                        Integer.parseInt(deathDayStr.substring(4, 6)),
                        Integer.parseInt(deathDayStr.substring(6, 8))
                );

                Period agePeriod = Period.between(birthDate, deathDate);
                int age = agePeriod.getYears();

                if (pname1 == null) {
                    pname1 = "없음";
                }
                if (pname2 == null) {
                    pname2 = "없음";
                }


                TextView textView = findViewById(R.id.mainInformation);
                String inputText = "故 " + name + "\"님\n" + age + "세 남\n" + deathDayStr.substring(0, 4) + "-" + deathDayStr.substring(4, 6) + "-" + deathDayStr.substring(6, 8) + "별세";
                textView.setText(inputText);

                TextView parentMatchTextView = findViewById(R.id.parent_match);
                String parentMatchText = "배우자: " + pname1 + "\n자녀: " + pname2;
                parentMatchTextView.setText(parentMatchText);

                TextView detailIformationTextView = findViewById(R.id.detailIformation);
                String detailIformationText = "생년월일 " + birthDayStr.substring(0, 4) + "-" + birthDayStr.substring(4, 6) + "-" + birthDayStr.substring(6, 8) +
                        "\n사망일 " + deathDayStr.substring(0, 4) + "-" + deathDayStr.substring(4, 6) + "-" + deathDayStr.substring(6, 8) +
                        "\n장지 이천호국원 \n묘지번호 " + qualification +
                        "\n안장자격 " + qualification;
                detailIformationTextView.setText(detailIformationText);
            }

        }

        ImageButton button_back = findViewById(R.id.button_back);

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailPage.this, MainActivity.class);
                startActivity(intent);
            }
        });

        ImageButton map_img = findViewById(R.id.map_img);

        map_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailPage.this, Map_Marker.class);
                startActivity(intent);
            }
        });
    }

}
