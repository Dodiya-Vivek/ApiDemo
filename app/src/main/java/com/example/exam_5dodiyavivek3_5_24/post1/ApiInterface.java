package com.example.exam_5dodiyavivek3_5_24.post1;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("user")
    Call<PostData> createPostData(@Body PostData dataModal);

}
