package com.example.project_merge;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Interface {
    // 사용할 메소드 선언

    @GET("1180000/IcheonNationalCemetery/Burialinfo2000")
    // EndPoint-자원위치(URL)
    // @GET -> Http Method 'GET/POST/PUT/DELETE/HEAD 중에서 무슨 작업인지
    Call<Response> getData(
            @Query("name") String name,
            @Query("pageNo") int pageNo,
            @Query("numOfRows") int numOfRows,
            @Query("serviceKey") String serviceKey
    );
    // 사용할 메소드 선언
    // @Query - URI에 쿼리스트링을 추가해서 보내줄 수 있도록 해주는 Annotation
}