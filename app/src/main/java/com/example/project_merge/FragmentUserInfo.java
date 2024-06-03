package com.example.project_merge;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;

public class FragmentUserInfo extends Fragment {

    SQLiteHelper sqLiteHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sqLiteHelper = new SQLiteHelper(getContext()); // SQLiteHelper 초기화
    }

    @SuppressLint("ResourceAsColor")
    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_user_info, container, false);
        Button btnSave = rootView.findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edtName = rootView.findViewById(R.id.edtName);
                String name = edtName.getText().toString().trim();

                Log.d("FragmentUserInfo", "Name: " + name);

                if (name.isEmpty()) {
                    Toast.makeText(getActivity(), "Please enter a name.", Toast.LENGTH_SHORT).show();
                } else {
                    int memberId = sqLiteHelper.getMemberIdByName(name); // Get the member ID
                    if (memberId != -1) { // If the member ID is valid
                        Intent intent = new Intent(getActivity(), MainActivity2.class);
                        intent.putExtra("memberId", memberId);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getActivity(), "User does not exist.", Toast.LENGTH_SHORT).show();
                    }
                }

//                if (!name.isEmpty()) {
//                    callApiWithName(name);
//                } else {
//                    Toast.makeText(getActivity(), "이름을 입력하세요.", Toast.LENGTH_SHORT).show();
//                }

//                if (name.isEmpty()) {
//                    Toast.makeText(getActivity(), "이름을 입력하세요.", Toast.LENGTH_SHORT).show();
//                } else if (sqLiteHelper.checkMemberName(name)) {
//                    Log.d("FragmentUserInfo", "Name exists in SQLite");
//                    int id = sqLiteHelper.getMemberIdByName(name);
//                    Intent intent = new Intent(getActivity(), MainActivity2.class);
//                    intent.putExtra("memberId", id);
//                    startActivity(intent);
//                } else {
//                    Log.d("FragmentUserInfo", "Name is '강기동'");
//                    int id = sqLiteHelper.getMemberIdByName(name);
//                    Intent intent = new Intent(getActivity(), MainActivity2.class);
//                    intent.putExtra("name", name);
//                    intent.putExtra("id", id);
//                    startActivity(intent);
//                }
            }
        });

        return rootView;
    }

//    private void callApiWithName(String name) {
//        Interface apiService = RetrofitClient.getClient("https://apis.data.go.kr/").create(Interface.class);
//
//        Call<Response> call = apiService.getData(name, 1, 50, "API_KEY_HERE");
//
//        call.enqueue(new Callback<Response>() {
//            @Override
//            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
//                if (response.isSuccessful() && response.body() != null && !response.body().getBody().isEmpty()) {
//                    // 데이터가 존재하면 다른 화면으로 전환
//                    Intent intent = new Intent(getActivity(), MainActivity2.class);
//                    intent.putExtra("name", name);
//                    startActivity(intent);
//                } else {
//                    // 데이터가 존재하지 않을 경우
//                    Toast.makeText(getActivity(), "존재하지 않는 이용자 입니다.", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Response> call, Throwable t) {
//                Log.d("herehere", t.toString());
//                Toast.makeText(getActivity(), "네트워크 에러가 발생했습니다.", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}
