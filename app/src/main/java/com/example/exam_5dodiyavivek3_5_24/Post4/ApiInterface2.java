package com.example.exam_5dodiyavivek3_5_24.Post4;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface2 {

    @POST("user")
    Call<PostData2> post2(@Body PostData2 dataModal2);

}
